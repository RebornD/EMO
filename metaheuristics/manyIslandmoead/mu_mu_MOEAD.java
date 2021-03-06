//  MOEAD.java
//
//  Author:
//       Antonio J. Nebro <antonio@lcc.uma.es>
//       Juan J. Durillo <durillo@lcc.uma.es>
//
//  Copyright (c) 2011 Antonio J. Nebro, Juan J. Durillo
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU Lesser General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU Lesser General Public License for more details.
//
//  You should have received a copy of the GNU Lesser General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>.

package metaheuristics.manyIslandmoead;

import java.util.HashMap;
import java.util.Vector;

import core.Algorithm;
import core.Operator;
import core.Population;
import core.Problem;
import core.Solution;
import experiments.SettingWriter;
import util.JMException;
import util.Neiborhood;
import util.Permutation;
import util.Random;
import util.WeightedVector;
import util.Comparator.MOEADComparator.ConstrainSumMOEADComparator;
import util.Comparator.MOEADComparator.MOEADComparator;
import util.Comparator.MultiIslandMOEADComparator.ConstrainMultiIslandMOEADComparator;
import util.Comparator.MultiIslandMOEADComparator.MultiIslandMOEADComparator;
import util.ScalarzingFunction.ScalarzingFunction;
import util.ScalarzingFunction.ScalarzingFunctionFactory;
public class mu_mu_MOEAD extends Algorithm {


	/**
	 * Stores the population size
	 */
	private MultiIslandMOEADComparator comparator;


	private double alpha = 1.1;

	private Population[] population_;

	/**
	 * Stores the population size
	 */
	private int populationSize_;

	private int totalpopulatifonSize_;

	/**
	 * Stores the population
	 */
	/**
	 * Z vector (ideal point)
	 */
	double[][] ReferencePoint_;
	/**
	 * Lambda vectors
	 */
	// Vector<Vector<Double>> lambda_ ;
	static double[][] WeightedVector_;

	private int numberofObjectives_;
	private int numberOfDivision_;


	static int[][] neighborhood_;

	int numberOftotalParents_;
	int numberOfeachParents_;

	Solution[][] indArray_;
	String functionTypes_;
	int evaluations_;

	ScalarFunctionSelection[] FunctionSelection_;

	MOEADComparator[] comparator_;

	Solution[] child_;

	/**
	 * Operators
	 */
	String[] outputDirectory_;

	Operator crossover_;
	Operator mutation_;

	int numberOfIsland_;
	int sizeOfNeiborhoodRepleaced_;
	int sizeOfMatingNeiborhood_;
	int numberOfMakeChildlen_;
	/**
	 * Constructor
	 *
	 * @param problem
	 *            Problem to solve
	 */
	public mu_mu_MOEAD(Problem problem) {
		super(problem);
	} // DMOEA

	boolean isInnerWeightVector_;
	int InnerWeightVectorDivision_;


	int[] permutation;
	ScalarzingFunction[] ScalarzingFunction_;

	public Population execute() throws JMException, ClassNotFoundException {


		numberOfIsland_ = ((Integer) this.getInputParameter("numberOfIsland")).intValue();

		numberOftotalParents_= 12;
		numberOfeachParents_ = 2;
		String   directorynamebase = ((String) this.getInputParameter("directoryname"));
		String[] ScalarzingFunctionName = (String [])(this.getInputParameter("ScalarzingFunctionName"));


		String[] directorynames = new String[numberOfIsland_];
		for(int i = 0 ;i <numberOfIsland_;i++){
			directorynames[i] = directorynamebase + "/Func" +String.valueOf(i+1);
		}


		int maxEvaluations = ((Integer) this.getInputParameter("maxEvaluations")).intValue();
		numberOfDivision_    = ((Integer)this.getInputParameter("numberOfDivision"));
		numberofObjectives_    = ((Integer)this.getInputParameter("numberOfObjectives"));
		sizeOfNeiborhoodRepleaced_ = ((Integer)this.getInputParameter("sizeOfNeiborhoodRepleaced_"));
		sizeOfMatingNeiborhood_    = ((Integer)this.getInputParameter("sizeOfMatingNeiborhood_"));
		InnerWeightVectorDivision_ = ((Integer)this.getInputParameter("sizeOfMatingNeiborhood_"));
		double[] para_ = (double[])this.getInputParameter("param");
		boolean ismax    = ((boolean)this.getInputParameter("ismax"));



		int time = ((Integer) this.getInputParameter("times")).intValue();

		evaluations_ = 0;

		crossover_ = operators_.get("crossover");
		mutation_ = operators_.get("mutation");
		alpha = ((double)this.getInputParameter("alphar"));
		int ma = Math.max(sizeOfMatingNeiborhood_, sizeOfMatingNeiborhood_);
		int generation;
		numberOfMakeChildlen_ = 6;


		ScalarzingFunction_ = new  ScalarzingFunction[numberOfIsland_];
		population_ = new Population[numberOfIsland_];
		comparator_ = new ConstrainSumMOEADComparator[numberOfIsland_];
		FunctionSelection_ = new ScalarFunctionSelection[numberOfIsland_];
	//	isInnerWeightVector_ = ;


		for(int i=0;i<numberOfIsland_;i++){
			ScalarzingFunction_[i] = ScalarzingFunctionFactory.getScalarzingFunctionOperator(ScalarzingFunctionName[i]);
			comparator_[i] = new ConstrainSumMOEADComparator(null, ScalarzingFunction_[i], para_[i]);
			FunctionSelection_[i] = new ScalarFunctionSelection(null,comparator_[i]);
		}

		comparator = new ConstrainMultiIslandMOEADComparator(null,ScalarzingFunction_);
		comparator.setIs(ismax);

		HashMap parameters = new HashMap(); // Operator parameters
		indArray_ = new Solution[numberOfIsland_][problem_.getNumberOfObjectives()];
		neighborhood_ = new int[populationSize_][];
		ReferencePoint_ = new double[numberOfIsland_][problem_.getNumberOfObjectives()];
		WeightedVector_ = new double[populationSize_][problem_.getNumberOfObjectives()];

		SettingWriter.clear();
		SettingWriter.merge(inputParameters_);
		SettingWriter.add("alpha",alpha);
		SettingWriter.merge(mutation_.getMap());
		SettingWriter.add("Problemname",problem_.getName());
		SettingWriter.merge(crossover_.getMap());



		// Create the initial solutionSet

		// STEP 1. Initialization
		boolean cont = true;

		setNeighborhood();

		populationSize_ = neighborhood_.length;
		evaluations_ = 0;

		// STEP 1.3. Initialize population
		initilaize();
		for(int i = 0;i<numberOfIsland_;i++){
			population_[i].printVariablesToFile(directorynames[i] + "/InitialVAR/InitialVAR" + time + ".dat");
			population_[i].printObjectivesAndTotalConstrainToFile(directorynames[i] + "/InitialFUN/ALL/InitialFUN" + time + ".dat");
			population_[i].printFeasibleObjectivesToFile(directorynames[i] + "/InitialFUN/Feasible/InitialFUN" + time + ".dat");
			population_[i].printInfeasibleObjectivesToFile(directorynames[i] + "/InitialFUN/Infeasible/InitialFUN" + time + ".dat");

		}

		permutation = new int[populationSize_];
		Permutation.randomPermutation(permutation,populationSize_);
		int generation_ = 1;


		// STEP 2. Update
		do {
			generation_++;
     		child_ = null;

     		if(generation_ %1000 == 0)
        		System.out.println(generation_ + "gen:");
     		makeChildlen();



    		updateReference(child_);


    		updataProblem(child_);

			if (evaluations_ >= maxEvaluations){
					cont = false;
					break;
			}

		}while (cont);
		System.out.print(evaluations_ +"	");
		// NormalizationWithNadia();
		//Normalization();

		for(int i = 0;i<numberOfIsland_;i++){
			population_[i].printVariablesToFile(directorynames[i] + "/FinalVAR/FinalVAR" + time + ".dat");
			population_[i].printObjectivesAndTotalConstrainToFile(directorynames[i] + "/FinalFUN/ALL/FinalFUN" + time + ".dat");
			population_[i].printFeasibleObjectivesToFile(directorynames[i] + "/FinalFUN/Feasible/FinalFUN" + time + ".dat");
			population_[i].printInfeasibleObjectivesToFile(directorynames[i] +  "/FinalFUN/Infeasible/FinalFUN" + time + ".dat");
		}

//		population_.printVariablesToFile(directoryname + "/FinalVAR/FinalVAR" + time + ".dat");
//		population_.printObjectivesToFile(directoryname +  "/FinalFUN/FinalFUN" + time + ".dat");

		return null;
	}

	private void updataProblem(Solution[] child) throws JMException {
		int size;

		size = sizeOfNeiborhoodRepleaced_;

		for(int i=0;i<populationSize_;i++){
			int n = permutation[i];
			for(int islandNumber = 0; islandNumber < numberOfIsland_;islandNumber++)
				for(int j=0;j< numberOfMakeChildlen_;j++){
					Solution newSol = child_[i*numberOfMakeChildlen_ + j];

					for(int q = 0 ; q < size;q++){
						comparator_[islandNumber].setWeightedVector(WeightedVector_[n]);
						comparator_[islandNumber].setRefernecePoint(ReferencePoint_[islandNumber]);
					if (comparator_[islandNumber].execute(newSol, population_[islandNumber].get(n)) == 1) {
							population_[islandNumber].replace(n, new Solution(newSol));
						}
					}
				}
			}
		}

	public void subscriptZ(){
		for(int i=0;i<problem_.getNumberOfObjectives();i++){
			System.out.print(ReferencePoint_[i] +" ");
		}
		System.out.println(" ");
	}

	public double[]  getNadia(Population pop){
		int size = pop.get(0).getNumberOfObjectives();
		double[]  nadirPoint = new double[size];
		Solution empty = pop.get(0);
		for(int k=0;k<size;k++){
			nadirPoint[k] = empty.getObjective(k);
		}
		for(int  i =1;i< pop.size();i++){
			empty = pop.get(i);
			for(int key =0 ; key < size;key++){
				if( nadirPoint[key] < empty.getObjective(key)){
						nadirPoint[key] = empty.getObjective(key);
					}
			}
		}
		return nadirPoint;
	};

	public double[]  getIdeal(Population pop){
		int size = pop.get(0).getNumberOfObjectives();
		double[]  nadirPoint = new double[size];
		Solution empty = pop.get(0);
		for(int k=0;k<size;k++){
			nadirPoint[k] = empty.getObjective(k);
		}
		for(int  i =1;i< pop.size();i++){
			empty = pop.get(i);
			for(int key =0 ; key < size;key++){
				if(nadirPoint[key] > empty.getObjective(key)){
						nadirPoint[key] = empty.getObjective(key);
					}
			}
		}
		return nadirPoint;
	};

	public int[] patternselect(int key){
		int[] ret = new int[2];
		switch(key){
			case 0: ret[0] = 0; ret[1] = 0;	 break;
			case 1: ret[0] = 0; ret[1] = 1;	 break;
			case 2: ret[0] = 0; ret[1] = 2;	 break;
			case 3: ret[0] = 1; ret[1] = 1;	 break;
			case 4: ret[0] = 1; ret[1] = 2;	 break;
			case 5: ret[0] = 0; ret[1] = 2;	 break;
			default:
				assert true : "patternselect is wrong, the key is " + key;
		}

		return ret;
	}

	public void makeChildlen() throws JMException{
		Solution[] child = new Solution[numberOfMakeChildlen_];
		child_ = new Solution[numberOfMakeChildlen_*populationSize_];
		Vector<Integer> parentsNumber = new Vector<Integer>();
		Solution[] parents = new Solution[numberOfeachParents_ ];
		Solution[] OffSpring;
		int[] angel;

		for(int j=0;j< populationSize_;j++){
			int n =permutation[j];
			matingSelection( parentsNumber,n, numberOftotalParents_ );
			for(int i=0;i<numberOfMakeChildlen_;i++){
				angel = patternselect(i);
				for(int k=0;k<2;k++){
					parents[k] = population_[angel[k]].get(parentsNumber.get( k + numberOfeachParents_*i) );
				}
				OffSpring = (Solution[]) crossover_.execute(parents);
				child_[i] = Random.nextDoubleIE() > 0.5 ? OffSpring[0] : OffSpring[1];
				child_[i] = (Solution) mutation_.execute(child_[i]);
				problem_.repair( child_[i] , null);
				problem_.evaluate(child_[i]);
				evaluations_++;
				assert  child_[i] != null : "two Solution have no compara";
				child_[j* numberOfMakeChildlen_ + i] = child_[i];
			}
		}
	}
/*
	public void Normalization(){
		double [] ideal = getIdeal(population_);

		for(int i=0;i<population_.size();i++){
			Solution sol = population_.get(i);
			for(int k =0;k<sol.getNumberOfObjectives();k++){
				double a = sol.getObjective(k);
				a  = a - ideal[k];
				sol.setObjective(k, a);
			}
		}
		NormalizationWithNadia();

	}

	public void NormalizationWithNadia(){
		double[] nadia = getNadia(population_);

		for(int i = 0;i < population_.size();i++){
			Solution sol = population_.get(i);
			for(int key = 0;key< sol.getNumberOfObjectives(); key++){
				double a = sol.getObjective(key);
				a = a /( nadia[key] );
				sol.setObjective(key, a);
			}

		}

	}
*/
	public void setNeighborhood() throws JMException{

		Neiborhood a = new Neiborhood();
		double[][] weight,weight_one;

		weight_one = WeightedVector.getWeightedVector(numberofObjectives_,numberOfDivision_);

		if (isInnerWeightVector_){
			double[][] weightinner = WeightedVector.getWeightedVector(numberofObjectives_,InnerWeightVectorDivision_);
			WeightedVector.getinnerWeightVector(weightinner);
			weight = 	WeightedVector.conbine(weight_one, weightinner);
		} else {
			weight = weight_one;
		}

		a.setWeightedVector(weight);
		a.setNeiborhood(sizeOfNeiborhoodRepleaced_);
		neighborhood_ = a.getNeiborhood();
		WeightedVector_       = a.getWeight();
	}
	public void subscript() {
		for (int i = 0; i < populationSize_; i++) {
			for (int j = 0; j < WeightedVector_[i].length; j++) {
					System.out.print(WeightedVector_[i][j] + "	");
			}
			System.out.println("	");

		}
	}



	//今回，参照点の初期化の間にあれを挟む必要が
	public void initilaize() throws ClassNotFoundException, JMException{
		Solution[] newSolution = new Solution[numberOfMakeChildlen_];
		population_ = new Population[numberOfIsland_];

		//スカラー関数によっては初期個体の割り当てには参照点が必要なので参照点のとりあえずの初期化
		for(int k=0;k<numberOfIsland_;k++){
			population_[k] = new Population();
			Solution a = new Solution(problem_);
			for (int n = 0; n < problem_.getNumberOfObjectives(); n++) {
				ReferencePoint_[k][n] = alpha * a.getObjective(n) ;
				indArray_[k][n] = new Solution(a);
			}
		}

		//初期個体群の生成
        for(int i=0;i<populationSize_;i++){
			for(int c=0;c<numberOfMakeChildlen_;c++){
				 newSolution[c] = new Solution(problem_);
	      		problem_.repair(newSolution[c],null);
	      		 problem_.evaluate(newSolution[c]);
				 evaluations_++;
			}
			int[] number = new int[numberOfIsland_];
			for(int k=0;k<numberOfIsland_;k++){
			    FunctionSelection_[k].setRefernecePoint(ReferencePoint_[k]);
				FunctionSelection_[k].setWeightedVector(WeightedVector_[i]);
				number[k] = (int)FunctionSelection_[k].execute(newSolution);
				population_[k].add(newSolution[number[k]]);
				updateReference(newSolution[number[k]]);
			}
         }



	}


	public void matingSelection(Vector<Integer> list,int cid, int size){
		int ss;
		int r;
		int p;
		boolean flag;
		ss = sizeOfMatingNeiborhood_;
		int store  = -1;
		int counter_ = 0;
		list.clear();
		while (list.size() < size) {
		 if(store ==  - 1){
    		r = Random.nextIntIE(ss);
	    	p = neighborhood_[cid][r];
		 } else {
			 do {
	    		r = Random.nextIntIE(ss);
		    	p = neighborhood_[cid][r];
			} while(store == p);
		 }
		 list.addElement(p);
		 if(list.size() % 2 == 0){
				store = - 1;
		 } else {
			store = p;
		 }
		}
	}

	public void matingSelection_without_replacement(Vector<Integer> list, int cid, int size) {
		int ss;
		int r;
		int p;
		boolean flag;
		ss = sizeOfMatingNeiborhood_;
		while (list.size() < size) {
			r = Random.nextIntIE(ss);
			p = neighborhood_[cid][r];
			// p = population[cid].table[r];
			list.addElement(p);
		}
	}





	private void updateReference(Solution[] temp) throws JMException {
		for(int i=0;i<temp.length;i++){
			Solution nowSol = temp[i];
			for(int k=0;k<numberOfIsland_;k++){
				for (int n = 0; n < problem_.getNumberOfObjectives(); n++) {
					if (comparator_[k].compare(nowSol.getObjective(n) ,indArray_[k][n].getObjective(n))  && comparator_[k].execute(nowSol, indArray_[k][n])  == 1) {
						ReferencePoint_[k][n] =  alpha*nowSol.getObjective(n);
						indArray_[k][n] = new Solution(nowSol);
					}
				}
			}
		}
	}

	private void updateReference(Solution temp) throws JMException {
			for(int k=0;k<numberOfIsland_;k++){
				for (int n = 0; n < problem_.getNumberOfObjectives(); n++) {
					if (comparator_[k].compare(temp.getObjective(n) ,indArray_[k][n].getObjective(n))  && comparator_[k].execute(temp, indArray_[k][n].getObjective(n))  == 1) {
						ReferencePoint_[k][n] =  alpha*temp.getObjective(n);
						indArray_[k][n] = new Solution(temp);
					}
				}
			}

	}

	void updateProblem(Solution indiv, int id) throws JMException {
		int size;

		size = sizeOfNeiborhoodRepleaced_;
		int[] perm = new int[size];

		// generate teh random permutation.
		Permutation.randomPermutation(perm, size);

		for(int islandNumber = 0; islandNumber < numberOfIsland_;islandNumber++){
			for (int i = 0; i < size; i++) {
				int k = neighborhood_[id][i];

				comparator_[islandNumber].setWeightedVector(WeightedVector_[k]);
				comparator_[islandNumber].setRefernecePoint(ReferencePoint_[islandNumber]);

				if (comparator_[islandNumber].execute(indiv, population_[islandNumber].get(k)) == 1) {
					population_[islandNumber].replace(k, new Solution(indiv));
				}
			}
		}

	} // updateProblem
} // MOEAD

