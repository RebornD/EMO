package core;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import util.Permutation;


/*
 * addとreplace のみ深いコピーでその他は浅いコピーを行っている．
 * 一応確認してみて浅いコピーでもＯＫなら浅いコピーに変更を行う．
 * ポインタのみのコピーとなる分，浅いコピーの方が実行速度は速くなる．決定変数の多いNSGAIIのナップサックなら大体1回試行で10倍ぐらい変実行層度が変化する
 *
 * 計算速度　
 * 配列の要素アクセス O(1)
 * removeは　O(n)
 * add はO(1)
 * get はO(1)
 * size はO(1)
 * MergeはO(n)
 * Replace はO(1)
 * clearはO(1)
 * SortはO(n^2)  のバブルソート
 *
 */


public class Population {

	int nOfD_ = 0;

	int populationSize;
	int capacity;

	int getCapacity(){return capacity;}

	public int getNumberOfD(){
		return nOfD_;
	}

	Solution[] population_;

	public Population(){
		population_ = new Solution[0];
		capacity = 0;
		populationSize = 0;
	}

	public Population(int d){
		population_ = new Solution[d];
		capacity = d+1;
		populationSize =0;
	}

	public void remove(int key){
		assert populationSize > key : "the index is wrong:: population Size is "  + populationSize + "	and  key is " + key;
		Solution [] emp = new Solution[populationSize];
		int counter = 0;

		populationSize--;
		for(int i=key;i<populationSize;i++){
			population_[i] = population_[i + 1];
		}
	}
	public static void main(String args[]){
		Population d = new Population(10);
		Solution test = new Solution(2);

		test.setObjective(0, 10);
		test.setObjective(1, 11);
		d.add(test);
		test.setObjective(0, 3);
		test.setObjective(1, 15);
		d.add(test);
		test.setObjective(0, 14);
		test.setObjective(1, 11);
		d.add(test);
		test.setObjective(0, 1);
		test.setObjective(1, 5);
		d.add(test);

		System.out.println();
		d.subscriptObjective();
		d.remove(0);

		System.out.println();
		d.subscriptObjective();


	}


	public void add(Solution a){
		assert a.getNumberOfObjectives() >0 : "add in add(Solution)   :: Solution have  "  +  a.getNumberOfObjectives()+ " dimension";
		assert (a.getNumberOfObjectives() == nOfD_  ) || nOfD_ == 0  :" add in add(Solution)   ::  Solution's Objective dimenstion and Population dimension is diffrence  :  solution  dimension is " + a.getNumberOfObjectives()  + "	" + "Population Objective dimension  is " + nOfD_;
		assert populationSize <= capacity : "キャパを考えろ";
		population_[populationSize++] = a;//new Solution(a);
	}
	public void add_sharrow(Solution a){
		assert a.getNumberOfObjectives() >0 : "add in add(Solution)   :: Solution have  "  +  a.getNumberOfObjectives()+ " dimension";
		assert (a.getNumberOfObjectives() == nOfD_  ) || nOfD_ == 0  :" add in add(Solution)   ::  Solution's Objective dimenstion and Population dimension is diffrence  :  solution  dimension is " + a.getNumberOfObjectives()  + "	" + "Population Objective dimension  is " + nOfD_;
		assert populationSize <= capacity : "キャパを考えろ";
		population_[populationSize++] = a;
	}


	public int size(){
		return populationSize;
	}

	public Solution get(int key){
		assert populationSize >= key : "Solution get is wrong , size is " + populationSize + "	and key is" + key;
		return population_[key];
	}
	public Solution[] get(){
		return population_;
	}


	public void ResetDominatedSolution(){
		for(int i=0;i<populationSize;i++){
			population_[i].setNumberOfDominatedSolution_(0);
		}
	}


	public void AssignID(){
		for(int i=0;i<populationSize;i++){
			population_[i].setID(i);
		}
	}

	public void merge(Population a){
		for(int i =0;i<a.size();i++){
			add(a.get(i));
		}
	}


	public Population(Population a ){
		nOfD_ = a.getNumberOfD();
		population_ = new Solution[a.size()];
		for(int i = 0;i<a.size();i++){
			population_[i] = new Solution (a.get(i));
		}
		populationSize = a.size();
		capacity = a.getCapacity();

	}

	public void sortObjectiveusingID(int key ){
		for(int i=0;i<populationSize-1;i++){
			Solution me = population_[i];
			for(int j=1;j<populationSize;j++){
				Solution you = population_[j];
				if(me.getObjective(key) > you.getObjective(key)){
					Solution d = new Solution(me);
					replace(i,you);
					replace(j,d);
				} else if(   Math.abs(me.getObjective(key) - you.getObjective(key)) <1.0E-14  &&  me.getID() > you.getID()){
					Solution d = new Solution(me);
					replace(i,you);
					replace(j,d);
				}
			}
		}
	}

	public Solution[] getAllSolutions(){
		return population_;
	}

	//this method is for the check
	public void subscriptObjective(){
		for(int i=0;i<populationSize;i++){
			Solution me = population_[i];
			for(int j=0;j<me.getNumberOfObjectives();j++){
				System.out.print(me.getObjective(j) + "	");
			}
			System.out.println();
		}

	}

	public double[][] getAllObjectives(){
		assert populationSize != 0 : "Populaiton.getAllObjectives() : There are no cluss in this population";
		int numberOfObjective = population_[0].getNumberOfObjectives();
		double[][] ret = new double[populationSize][numberOfObjective];
		for (int i=0;i<populationSize;i++){
			double[] temp = population_[i].getObjectives();
			for (int j=0;j<numberOfObjective;j++){
				ret[i][j] = temp[j];
			}
		}
		return ret;
	}

	public int[] sortObjectivereturnperm(int key ){
		int [] perm = new int[populationSize];
		Permutation.setPermutation(perm);

		for(int i=0;i<populationSize-1;i++){
			for(int j=i+1;j<populationSize;j++){
				int p = perm[i];
				int q = perm[j];
				Solution me = population_[p];
				Solution you = population_[q];
				if(me.getObjective(key) > you.getObjective(key)){
					perm[i] = q;
					perm[j] = p;
				} else if(Math.abs(me.getObjective(key) - you.getObjective(key)) <1.0E-14  &&  me.getID() > you.getID()){
					perm[i] = q;
					perm[j] = p;
				}

			}
		}
		return perm;
	}
/*    static void sortObjectivereturnperm(int[] d, int left, int right) {
        if (left>=right) {
            return;
        }
        int p = d[(left+right)/2];
        int l = left, r = right, tmp;
        while(l<=r) {
            while(d[l] < p) { l++; }
            while(d[r] > p) { r--; }
            if (l<=r) {
                tmp = d[l]; d[l] = d[r]; d[r] = tmp;
                l++; r--;
            }
        }
        quick_sort(d, left, r);  // ピボットより左側をクイックソート
        quick_sort(d, l, right); // ピボットより右側をクイックソート
    }*/



	public void printVariablesToFile(String path) {
		try {
			FileOutputStream fos = new FileOutputStream(path);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);

			if (size() > 0) {
				int numberOfVariables = population_[0].getNumberOfVariables();

				for(int i = 0 ;i < populationSize;i++){
					for (int j = 0; j < numberOfVariables; j++){
						bw.write(population_[i].getValue(j) + "	");
					}


					bw.newLine();

				}
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void printObjectivesToFile(String path) {
		try {
			FileOutputStream fos = new FileOutputStream(path);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);

			if (size() > 0) {
				int NumberOfObjectives = population_[0].getNumberOfObjectives();
				for(int i = 0 ;i < populationSize;i++){
					for (int j = 0; j < NumberOfObjectives; j++){
							bw.write(population_[i].getObjective(j) + "	");
					}
					bw.newLine();

				}
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void printObjectivesAndTotalConstrainToFile(String path) {
		try {
			FileOutputStream fos = new FileOutputStream(path);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);

			if (size() > 0) {
				int NumberOfObjectives = population_[0].getNumberOfObjectives();
				for(int i = 0 ;i < populationSize;i++){
					for (int j = 0; j < NumberOfObjectives; j++){
							bw.write(population_[i].getObjective(j) + "	");
					}
					bw.write(population_[i].getViolation() + " ");
					bw.newLine();

				}
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void printFeasibleObjectivesToFile(String path) {
		try {
			FileOutputStream fos = new FileOutputStream(path);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);
			if (size() > 0) {
				int NumberOfObjectives = population_[0].getNumberOfObjectives();
				for(int i = 0 ;i < populationSize;i++){
				 if(population_[i].getFeasible()){
					for (int j = 0; j < NumberOfObjectives; j++){
							bw.write(population_[i].getObjective(j) + "	");
					}
					bw.write( population_[i].getViolation() + " ");

					bw.newLine();
					}
				}
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void printInfeasibleObjectivesToFile(String path) {
		try {
			FileOutputStream fos = new FileOutputStream(path);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);

			if (size() > 0) {
				int NumberOfObjectives = population_[0].getNumberOfObjectives();
				for(int i = 0 ;i < populationSize;i++){
				 if(!population_[i].getFeasible()){
					for (int j = 0; j < NumberOfObjectives; j++){
							bw.write(population_[i].getObjective(j) + "	");
					}
					bw.write(population_[i].getViolation() + " ");
 					bw.newLine();
					}
				}
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void replace(int key,Solution a){
		population_[key] =a;// new Solution(a);
	}

	public void inverseObjective() {
		for(int i=0;i<this.size();i++){
			population_[i].setInverseObjective();
		}
	}


	//データ自体は残るけどこれでaddやMergeを行うと，そのデータを上書きする
	//PCのゴミ箱みたいな原理
	public void clear() {
		populationSize = 0;
	}

	public void Normalization() {
		int size = population_[0].getNumberOfObjectives();
		double ideal[] = new double[population_[0].getNumberOfObjectives()];
		double Nadia[] = new double[population_[0].getNumberOfObjectives()];
		for(int i=0;i<size;i++){
			ideal[i] = Double.POSITIVE_INFINITY;
			Nadia[i] = Double.NEGATIVE_INFINITY;
			//nadia は初期化されているため必要なし．
		}


		for(int i=0;i<populationSize;i++){
			for(int j = 0;j< size;j++){
				ideal[j] = Math.min(ideal[j], population_[i].getObjective(j));
				Nadia[j] = Math.max(Nadia[j], population_[i].getObjective(j));
			}
		}
		for(int i=0;i<populationSize;i++){
			for(int j = 0;j< size;j++){
				double ret = Nadia[j] - ideal[j];
				if(ret < 1.0E-14) ret = 1;
				population_[i].setObjective(j,(population_[i].getObjective(j) - ideal[j])/(ret) );
			}
		}
	}


}
