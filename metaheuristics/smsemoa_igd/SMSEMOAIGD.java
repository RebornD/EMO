package metaheuristics.smsemoa_igd;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import core.Algorithm;
import core.Operator;
import core.Population;
import core.Problem;
import core.Solution;
import operators.selection.ParentsSelection.BinaryTournament;
import operators.selection.ParentsSelection.ParentsSelection;
import util.Front;
import util.JMException;
import util.POINT;
import util.Random;
import util.WeightedVector;
import util.Comparator.SMSEMOAComparator.SMSEMOAComparator;
import util.Comparator.SMSEMOAComparator.SMSEMOASelectionComparator;
import util.Indicator.Hypervolume.WFGHV;
import util.Ranking.NDSRanking;


/****************************
 *
 * @author Ryuichi
 * 正規化についての注意
 * 正規化するときに0 devide にならないように注意する
 */

public class SMSEMOAIGD extends Algorithm{

	public SMSEMOAIGD(Problem problem) {
		super(problem);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	WFGHV WFGHV_;

	List<Integer>  Sp_;
	int[]	Np_;
	List<List<Integer> > F_all;
	List<List<Integer> > Sp_all  ;
	private  boolean  isNorm = false;
	private int maxEvaluation_ ;
	int[] rank_;

	double[][] ReferencePoint_;


	int numberofObjectives_;
	int numberOfDivision_;
	boolean isInnerWeightVector_;
	int InnerWeightVectorDivision_;

	int numberOfParents_;
	private SMSEMOAComparator comparator;
	private int populationSize_;

	NDSRanking ranking;
	int evaluations_;
	HashMap parameters;
	Operator crossover_;
	Operator mutation_;
	ParentsSelection ParentSelection_;
	private Population population_;
	private Population Merge_;
	String directoryname;


	boolean  isMaxProblem_ ;

	@Override
	public Population execute() throws JMException, ClassNotFoundException {
		setting();
		HashMap parameters = new HashMap();
		int time = ((Integer) this.getInputParameter("times")).intValue();

		setReferencePoint();
		initPopulation();



		population_.printVariablesToFile(directoryname + "/InitialVAR/InitialVAR" + time + ".dat");
		population_.printObjectivesToFile(directoryname + "/InitialFUN/InitiaFUN" + time + ".dat");

		Sp_all = new  ArrayList<List <Integer>>();
		F_all = new  ArrayList<List <Integer>>();

		System.out.print(maxEvaluation_ + " ");
		do {

			makeNextGeneration();

			GotoNextGeneration();

		}while(	evaluations_ <maxEvaluation_);

		System.out.print(evaluations_+ "");
		population_.printVariablesToFile(directoryname + "/FinalVAR/FinalVAR" + time + ".dat");
		population_.printObjectivesToFile(directoryname + "/FinalFUN/FinalFUN" + time + ".dat");
		return population_;
	}


	public int[] ParentsSelection(int[] d){
		assert numberOfParents_ == d.length: "permutation size is wrong permutation size is " + d.length   + "	" + "numberOfparents is " + numberOfParents_;

		for(int i=0;i<numberOfParents_;i++){
			d[i] = Random.nextIntIE(populationSize_);
		}
		return d;
	}

	public void GotoNextGeneration() throws JMException{

		Merge_.AssignID();

		ranking = new NDSRanking(isMAX_);
		ranking.setPop(Merge_);
		ranking.Ranking();
		
		Population empty = ranking.get(ranking.getworstrank()-1);
		int number = -1;
		if(empty.size() == 1){
			number = empty.get(0).getID();
		}  else  {
			Front no = new Front(empty);
			if(isNorm) {
				if(!isMaxProblem_){
					NormiaztionFormin(no);
				} else {
					assert false : "まだ　未設定";
				}
			}
			double[] empt =IGDCalclator.calclate(no,ReferencePoint_);
			List<Integer> list = IGDCalclator.getLowestContribution(empt);
			int d = Random.nextIntIE(list.size());
			number = empty.get( list.get(d) ).getID();
		}

		Merge_.remove(number);
		population_ = new Population(Merge_);
		Merge_ = null;

		assert (population_.size() == populationSize_): "now pop size is " + population_.size() +" but hope " + populationSize_;
	}



	private void NormiaztionFormin(Front no) {
		assert no.size() > 0 : "Front size is "+ no.size();
		assert no.getDimension() > 1 : "Front Dimesnion is "+ no.getDimension();

		Front bestFront = new Front(ranking.get(0));
		double[] Ideal = bestFront.getIdeal(isMaxProblem_);
		double[] Nadia = bestFront.getNadia(isMaxProblem_);

		double[] vvv = new double[Ideal.length];
		for(int j=0;	j	<vvv.length;	j++){
			vvv[j] = Math.sqrt( (Nadia[j] -Ideal[j])*(Nadia[j] -Ideal[j])) > 1.0E-10 ? Math.sqrt( (Nadia[j] -Ideal[j])*(Nadia[j] -Ideal[j])): 1.0E-10;
		}

		for(int i=0;i<no.size();i++){
			POINT d = no.get(i);
			for(int j=0;j<no.getDimension();j++){
				double val = d.get(j);
				d.set(j, (val - Ideal[j]) / (vvv[j]));
			}
		}
	}


	public void setReferencePoint() throws JMException{

		double[][] weight,weight_one;

		weight_one = WeightedVector.getWeightedVector(numberofObjectives_,numberOfDivision_);

		if (isInnerWeightVector_){
			double[][] weightinner = WeightedVector.getWeightedVector(numberofObjectives_,InnerWeightVectorDivision_);
			WeightedVector.getinnerWeightVector(weightinner);
			weight = 	WeightedVector.conbine(weight_one, weightinner);

		} else {
			weight = weight_one;
		}
		ReferencePoint_ = weight;
		System.out.println(ReferencePoint_.length);
	}

	public void initPopulation() throws JMException, ClassNotFoundException {

 		for (int i = 0; i < populationSize_; i++) {
			Solution newSolution = new Solution(problem_);
			evaluations_++;
			problem_.repair(newSolution,null);
			problem_.evaluate(newSolution);
		 	population_.add(newSolution);
		} // for
	} // initPopulatiown
 // initPopulation

	public void makeNextGeneration() throws JMException {
		Merge_ = new Population(populationSize_ + 1);
		Merge_.merge(population_);

		Solution   child;
		Solution[] parents   = new Solution[2];

		int one  = (int) ParentSelection_.execute(population_);
		int two  = (int) ParentSelection_.execute(population_);

		parents[0] = population_.get(one);
		parents[1] = population_.get(two);

		child = (Solution) crossover_.execute(parents);
		mutation_.execute(child);
		problem_.repair(child ,null);

		problem_.evaluate(child);
		evaluations_ = evaluations_ + 1;
		Merge_.add(new Solution(child));

	}

	public void setting(){
		numberOfParents_ = 2;
		comparator = new SMSEMOASelectionComparator(null);
		evaluations_ = 0;
		populationSize_ = ((Integer)this.getInputParameter("populationSize"));
		maxEvaluation_ = ((Integer)this.getInputParameter("maxEvaluations"));
		population_ = new Population(populationSize_);

		ParentSelection_ = new BinaryTournament(null);
		ParentSelection_.setComparator(comparator);
		isMaxProblem_    = ((boolean)this.getInputParameter("ismax"));
		comparator.setIs(isMaxProblem_);

		crossover_ = operators_.get("crossover");
		mutation_ = operators_.get("mutation");
		directoryname = ((String) this.getInputParameter("DirectoryName"));
		isNorm = ((boolean) this.getInputParameter("Norm"));
		numberOfDivision_    = ((Integer)this.getInputParameter("numberOfDivision"));
		numberofObjectives_    = ((Integer)this.getInputParameter("numberOfObjectives"));
		InnerWeightVectorDivision_ = ((Integer)this.getInputParameter("InnerWeightDivision"));
		isInnerWeightVector_ = ((InnerWeightVectorDivision_ > 0));

	}

}
