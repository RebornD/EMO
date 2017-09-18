package util;

import core.Population;
import core.Solution;
import util.Comparator.Comparator;
import util.Comparator.NormalComparator;

/*
 *
 *
 *
 */


public class Sort {


	public static int[] random_sort(int[] Sort){
		int size = Sort.length;
		int[] list = new int[size];
		for(int i=0;i<size;i++){
			list[i] = Sort[i];
		}
		for(int i = size - 1;i > 0; i--){
			int j = Random.nextIntIE(i+1);
			if (i != j ){
				int t = list[i];
				list[i]  = list[j];
				list[j]  = t;
			}
		}
		return list;
	}

	public static void QuickSort(Object[] obj, Comparator comp,int start , int end) throws JMException{
		if(end - start <=0){
			return ;
		}
		int l = start; int r = end;
		int pipot = (end+start)/2;

		while(l<=r){
			while (comp.execute(obj[pipot],obj[l]) == 1){ l++;}
			while (comp.execute( obj[r],obj[pipot]) == 1){ r--;}


			if(l > r) break;
			Object d = obj[l];
			obj[l] = obj[r];
			obj[r] = d;
			l++; r--;

		}
		QuickSort(obj,comp,start,r);
		QuickSort(obj,comp,l,end);
	}


	public static void QuickSort(Object[] obj,int[] perm, Comparator comp,int start , int end) throws JMException{
		if(end <=start){
			return ;
		}
		int l = start; int r = end;
		int pipot = (l+r)/2;
		while(l<=r){
			while (comp.execute(obj[perm[pipot]], obj[perm[l]] ) == 1){ l++;}
			while (comp.execute(obj[perm[r]], obj[perm[pipot]]) == 1){ r--;}

			if(l <= r){
				int temp = perm[l];
				perm[l] = perm[r];
				perm[r] = temp;
				l++; r--;
			}
		}
		QuickSort(obj,perm,comp,start,r);
		QuickSort(obj,perm,comp,l,end);
	}

	//
	public static double[][] Decreasing_sort(double[][] asg, int key){
		assert asg.length >0 : "Decresing_sort in Decresingsort :: ret have no size";
		assert asg[0].length >key : "Decresing_sort in Decresingsort :: the double[] ret have no long array ";

		double[][] ret = asg.clone();

		for(int i=0;i<ret.length-1;i++){
			for(int j=i+1;j<ret.length;j++){
					if (ret [i][key] < ret [j][key]){

						Swap.Swap(ret[i],ret[j]);
					}
			}
		}
		return ret;
	}
/*
	public static void quick_sort(int[] perm,Object []  d,Comparator comparator ,int left, int right) throws JMException {
		 if (left>=right) {
	            return;
	        }

	        int p = (left+right)/2;
	        int l = left, r = right, tmp;

	        while(l<=r) {
	            while(comparator.execute( d[perm[l]] , d[perm[p]]) == 1 ) {  l++; }
	            while(comparator.execute( d[perm[r]] , d[perm[p]]) == -1 ) { r--; }
	            if (l<=r) {
	                tmp = perm[l]; perm[l] = perm[r]; perm[r] = tmp;
	                l++; r--;
	            }
	        }
	        quick_sort(perm,d,comparator ,left, r);  // ピボットより左側をクイックソート
	        quick_sort(perm,d,comparator ,l, right); // ピボットより右側をクイックソート
	 }
*/
/*
	 public static void main(String args[]){




	 }

*/



	public static void sort(Object[]  sort, Comparator d ) throws JMException{
		int size = sort.length;
		for(int i =0;i < size-	1 ;i ++){
			for(int j=1;j<size;j++){
				if(d.execute(sort[i] ,sort[j]) == 1){
					Object empty = sort[i];
					sort[i] = sort[j];
					sort[j] = empty;
				}
			}
		}
	}

	public static double[] random_sort(double[] Sort){
		int size = Sort.length;

		double[] list = new double[size];
		for(int i=0;i<size;i++){
			list[i] = Sort[i];
		}
		for(int i = size - 1;i > 0; i--){
			int j = Random.nextIntIE(i+1);
			if (i != j ){
				double t = list[i];
				list[i]  = list[j];
				list[j]  = t;
			}
		}
		return list;
	}
	private static boolean check(Integer[] a,Integer[]b){
		for(int i=0;i<a.length;i++){
			if( a[i] != b[i]){
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args){
		Integer[] ret =  {9,1,2,3,6,8,5,4,7,10};
//		Permutation.randomPermutation(ret);
		Integer[] answer = {1,2,3,4,5,6,7,8,9,10};
		int[] perm = new int[ret.length];

		Permutation.setPermutation(perm);
		Comparator comp = new NormalComparator(true);


		try {
	//		QuickSort(ret,comp,0 , ret.length-1) ;
			QuickSort(ret,perm,comp,0 , ret.length-1) ;

		} catch (JMException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		for(int i=0;i<ret.length;i++){
			System.out.println(ret[i]);
		}


		if(check(ret,answer)){
			System.out.println("OK");
		} else {
			System.out.println("落第");
		}

		/*



		for(int i=0;i<3;i++){
			System.out.print(a[i][0] + "  "+a[i][1] + "  "+a[i][2] + "\n");
		}


		for(int i=0;i<3;i++){
			System.out.print(ret[i][0] + "  "+ret[i][1] + "  "+ret[i][2] + "\n");
		}
	*/}


	public static void sortUsingObjevtiveForAscedingOrader(Population pop, int key){
		for(int i = 0;i<pop.size() - 1 ;i++){
			for(int j =i;j<pop.size();j++){
				if(pop.get(i).getObjective(key) >  pop.get(j).getObjective(key)){
					Solution d = new Solution(pop.get(i));
					pop.replace(i ,new Solution( pop.get(j)));
					pop.replace(j,d);
				}
			}
		}
	}

	public static void sortUsingObjevtiveForAscedingOrader(Front pop,int key){
		for(int i = 0;i<pop.size() - 1 ;i++){
			for(int j =i;j<pop.size();j++){
				if(pop.get(i).get(key) >  pop.get(j).get(key)){
					POINT d = new POINT(pop.get(i));
					pop.replace(i ,new POINT( pop.get(j)));
					pop.replace(j,d);
				}
			}
		}
	}
	public static int[] sortUsingObjevtiveForAscedingOraderperm(Front pop,int key){
		int[] perm = new int[pop.size()];
		Permutation.setPermutation(perm);


		for(int i = 0;i<pop.size() - 1 ;i++){
			for(int j =i;j<pop.size();j++){
				if(pop.get(perm[i]).get(key) >  pop.get(perm[j]).get(key)){
					int emp = perm[i];
					perm[i] = perm[j];
					perm[j] = emp;
				}
			}
		}
		return perm;
	}

	public static void sortUsingObjevtiveForAscedingOraderWithID(Population pop, int key){
		for(int i = 0;i<pop.size() - 1 ;i++){
			for(int j =i+1;j<pop.size();j++){
				if(pop.get(i).getObjective(key) >  pop.get(j).getObjective(key)){
					Solution d = new Solution(pop.get(i));
					pop.replace(i ,new Solution( pop.get(j)));
					pop.replace(j,d);
				} else if(  Math.abs( pop.get(i).getObjective(key)  -  pop.get(j).getObjective(key) )< 1.0E-14 &&  pop.get(i).getID() > pop.get(j).getID()) {
					Solution d = new Solution(pop.get(i));
					pop.replace(i ,new Solution( pop.get(j)));
					pop.replace(j,d);
				}
			}
		}
	}

/*
	public static Population nonDominatedPopulation(Population a){

		Population ret = new Population ();
		for(int i=0;i<a.size();i++){
			boolean judge = true;
			Solution p = a.get(i);
			for(int j=0;j<a.size();j++){
				Solution q = a.get(j);
				if(Dominance.isDominanced(q, p)){
					judge = false;
				}
			}
			if(judge){
				ret.add(p);
			}
		}
		return ret;
	}









	public static Population  nonDominatedSort(Population pop, boolean isMAX){
		Dominance.set(isMAX);

		Population ret = new Population();
		for(int i=0;i<pop.size();i++){
			boolean judge = true;
			Solution p = pop.get(i);
			for(int j=0;j<pop.size();j++){
				Solution q = pop.get(j);
				if(Dominance.isDominanced(q, p)){
					judge = false;
				}
			}
			if(judge){
				ret.add(p);
			}
		}
		return ret;
	}

*/
}
