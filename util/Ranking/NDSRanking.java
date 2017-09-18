package util.Ranking;

import java.util.ArrayList;
import java.util.List;

import core.Population;
import core.Solution;
import util.Permutation;
import util.Comparator.DominationComparator;


/*
 *
 *
 *
 *
 */

/***************************************************************************************************************
 * pleese read "An Efficient Approach to Non-Dominated Sorting for Evolutionary Multi-Objective-Optimization"
 * 																										   *
 ***************************************************************************************************************
 */
public class NDSRanking {
	String name;

	List<Population> object = new ArrayList<Population>();

	Population pop;

	boolean isMAX_;

	public NDSRanking(boolean s) {
		isMAX_ = s;
		comparator_ = new DominationComparator(s);
	}

	DominationComparator comparator_;

	public NDSRanking() {
		name = "ranking";
	}

	public NDSRanking(Population d) {
		name = "ranking";
		pop = d;
	}

	public int size() {
		return object.size();
	}

	public Population get(int key) {
		assert (object.size() > key) : "Population size is " + object.size() + "	key is " + key;
		return object.get(key);
	}

	public void setPop(Population pop_) {
		pop = pop_;
	}

	public String getName() {
		return name;
	}
	public int getworstrank(){
		return object.size();
	}

	private int RankingSolution(Solution sol) {
		int x = object.size() - 1;
		int k = 0;
		boolean flag = true;
		while (true) {
			Population nowpop = object.get(k);
			flag = true;
			for (int i = nowpop.size()-1; i >= 0; i--) {
				if (comparator_.isDominanced(nowpop.get(i), sol) == 1) {
					flag = false;
					break;
				}
			}

				if (flag) {
					return k;
				} else {
					k++;
					if (k > x) {
						return x + 1;
					}
				}
			}
	}

	private boolean compare(Solution one , Solution two){
		assert one.getNumberOfObjectives() == two.getNumberOfObjectives() : "two objective is wrong";
		int d = one.getNumberOfObjectives();
		for(int i=0;i<d;i++){
			if(Math.abs(one.getObjective(i)- two.getObjective(i)) > 1.0E-14 ){
				return ((isMAX_) != (one.getObjective(i) > two.getObjective(i)) );
			}
		}

		return true;
	}

	private  int[] sort(Population pop){
		int[] perm = new int[pop.size()];
		Permutation.setPermutation(perm);
		for(int i = 0;i<pop.size() - 1 ;i++){
			for(int j =i;j<pop.size();j++){
				if( compare(pop.get(perm[i]) , pop.get(perm[j])) ){
					int d = perm[i];
					perm[i] = perm[j];
					perm[j] = d;
				}
			}
		}
		return perm;
	}

	public void Ranking() {

		pop.AssignID();
		int perm[] = sort(pop);

		// pop.sortObjectiveusingID(0);
		object.clear();
		Population eee = new Population(pop.size());
		pop.get(0).setRank(1);
		eee.add(pop.get(perm[0]));

		object.add(eee);

		for (int ddd = 1; ddd < pop.size(); ddd++) {
			int i = perm[ddd];
			int k = RankingSolution(pop.get(i));

			if (object.size() == k) {
				object.add(new Population(pop.size() - ddd ));
				pop.get(i).setRank(k+1);
				object.get(k).add(pop.get(i));
			}else {
				pop.get(i).setRank(k+1);
				object.get(k).add(pop.get(i));
			}
		}

	};

	public static void main(String[] args) {
		test d = new test();
		d.setConfig();
	}

	public void subscriontPop(Population a) {
		for (int i = 0; i < a.size(); i++) {
			System.out.println(a.get(i).getRank() + "			" + a.get(i).getCrowdDistance_());
		}
	}

}
