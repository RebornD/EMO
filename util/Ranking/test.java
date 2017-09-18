package util.Ranking;

import core.Population;
import core.Solution;

public class test {

	public Population setConfig(){
		Population ret = new Population(100);

		Solution a = new Solution(2);
		System.out.println();

		a.setObjective(0, 18);
		a.setObjective(1, 18);
		ret.add(new Solution(a));
		a.setObjective(0, 18);
		a.setObjective(1, 18);
		ret.add(new Solution(a));
		a.setObjective(0, 12);
		a.setObjective(1, 18);
		ret.add(new Solution(a));
		a.setObjective(0, 16);
		a.setObjective(1, 16);
		ret.add(new Solution(a));
		a.setObjective(0, 18);
		a.setObjective(1, 12);
		ret.add(new Solution(a));
		a.setObjective(0,10);
		a.setObjective(1, 16);
		ret.add(new Solution(a));
		a.setObjective(0,14 );
		a.setObjective(1, 14);
		ret.add(new Solution(a));
		a.setObjective(0,15 );
		a.setObjective(1, 9);
		ret.add(new Solution(a));
		a.setObjective(0,18 );
		a.setObjective(1, 6);
		ret.add(new Solution(a));
		a.setObjective(0,4 );
		a.setObjective(1, 14);
		ret.add(new Solution(a));
		a.setObjective(0,9 );
		a.setObjective(1, 13);
		ret.add(new Solution(a));
		a.setObjective(0,12 );
		a.setObjective(1, 11);
		ret.add(new Solution(a));
		a.setObjective(0,16 );
		a.setObjective(1, 4);
		ret.add(new Solution(a));
		a.setObjective(0,2 );
		a.setObjective(1, 12);
		ret.add(new Solution(a));
		a.setObjective(0,8 );
		a.setObjective(1, 10);
		ret.add(new Solution(a));
		a.setObjective(0,12 );
		a.setObjective(1, 8);
		ret.add(new Solution(a));
		a.setObjective(0,14 );
		a.setObjective(1, 4);
		ret.add(new Solution(a));
		a.setObjective(0,16 );
		a.setObjective(1, 0);
		ret.add(new Solution(a));
		a.setObjective(0,2 );
		a.setObjective(1, 8);
		ret.add(new Solution(a));
		a.setObjective(0, 6 );
		a.setObjective(1, 6);
		ret.add(new Solution(a));
		a.setObjective(0,10 );
		a.setObjective(1, 2);
		ret.add(new Solution(a));



		NDSRanking ranking = new NDSRanking(true);

		ranking.setPop(ret);
		ranking.Ranking();
		int worstrank = ranking.getworstrank();


		int k = 0;
		Population F;
		while(k < worstrank){
			F = ranking.get(k);
			CrowdingDistance(F);
			k = k + 1;
			for(int i=0;i<F.size();i++){
				System.out.println(F.get(i).getObjective(0) + "	" + F.get(i).getObjective(1) +"		"+  F.get(i).getRank()+"	" + F.get(i).getCrowdDistance_());
			}
			System.out.println();;
		}

		for(int i=0;i<ret.size();i++){
	//		System.out.println(ret.get(i).getObjective(0) + "	" + ret.get(i).getObjective(1) +"		"+  ret.get(i).getRank()+"	" + ret.get(i).getCrowdDistance_());
		}
	return ret;
	}

	public void CrowdingDistance(Population a){
		for(int i = 0;i< a.size();i++){
				a.get(i).setCrowedDistance(0.0);
		}
		Solution dd = a.get(0);
		int Objectives = dd.getNumberOfObjectives();

		double max,min;
		double em;
		for(int key = 0;key< Objectives; key++){
			 int [] e = a.sortObjectivereturnperm(key);
				a.get(e[0]).setCrowedDistance(1.0E25);
				a.get(e[a.size() - 1]).setCrowedDistance(1.0E25);
				max = a.get(e[a.size() - 1]).getObjective(key);
				min = a.get(e[0]).getObjective(key);
				if (max - min < 1.0E-14){
					continue;
				}
			for(int  n = 1;n < a.size() -1 ;n++){
					Solution sp = a.get(e[n]);
					em = sp.getCrowdDistance_();

					em = em + (a.get(e[n + 1]).getObjective(key)  - a.get(e[n - 1]).getObjective(key))/(max - min);

					sp.setCrowedDistance(em);
				}
			}
		}
}
