package mo.main;

import java.util.HashMap;

import javax.naming.NameNotFoundException;

import experiments.Setting;
import mo.core.AlgorithmMain;
import mo.core.Operator;
import mo.core.Problem;
import mo.metaheuristics.NSGAIII.NSGAIII;
import mo.operators.crossover.CrossoverFactory;
import mo.operators.mutation.MutationFactory;
import mo.problems.MOP.MOPFactory;
import mo.util.JMException;

public class NSGAIIIMain extends AlgorithmMain{

	public NSGAIIIMain(Setting test) {
		super(test);
	}



	@Override
	public void setParameter() throws NameNotFoundException, ClassNotFoundException, JMException {

		String Problemname = setting_.getAsStr("Problem");
		int OBJ = setting_.getAsInt("Objectives");
		int NumberOfRun = setting_.getAsInt("NumberOfTrial");
		Problem problem; // The problem to solve
		Operator crossover = null; // Crossover operator
		Operator mutation = null; // Mutation operator

		HashMap parameters; // Operator parameters
		HashMap d = new HashMap();
		d.put("numberOfObjectives",OBJ);
		problem = MOPFactory.getMOP(Problemname,d,"NSGAIII");
		algorithm = new NSGAIII(problem);


		String dddname = problem.getNumberOfObjectives()  + "OBJ" + setting_.getAsInt("Division") +"div";
		algorithm.setInputParameter("populationSize", setting_.getAsInt("populationSize"));
		String name = "";

		if (setting_.containsKey("DirectoryName") && setting_.getAsStr("DirectoryName") != null){
			name += setting_.getAsStr("DirectoryName");
		}
		DirectoryName = "result/NSGAIII/" + Problemname+"/"+dddname;

		DirectoryName = name + "/"+ DirectoryName;


		algorithm.setInputParameter("DirectoryName",  DirectoryName);
		algorithm.setInputParameter("maxEvaluations", setting_.getAsInt("maxEvaluations"));
		algorithm.setInputParameter("numberOfParents", 2);
//		algorithm.setInputParameter("SMSEMOA", 2);
		parameters = new HashMap();
		parameters.put("Mutationprobability",setting_.getAsDouble("MutationProbability"));
		parameters.put("MutationdistributionIndex",setting_.getAsDouble("MutationDistribution"));

		try {
			mutation = MutationFactory.getMutationOperator(setting_.getAsStr("MutationName"), parameters);
		} catch (JMException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		algorithm.setInputParameter("numberOfDivision",setting_.getAsInt("Division"));

 		int innnerWeightDivision =  setting_.getAsInt("InnerWeightDivision");
		innnerWeightDivision =  setting_.getAsInt("Objectives") >= 6 ? innnerWeightDivision : 0;
		algorithm.setInputParameter("InnerWeightDivision",innnerWeightDivision);
		algorithm.setInputParameter("numberOfObjectives",setting_.getAsInt("Objectives"));// Boolean.valueOf((String)(experiment_setting_.get("isNorm"))));

		parameters.put("Crossoverprobability",setting_.getAsDouble("CrossoverProbability"));
		parameters.put("CrossoverdistributionIndex",setting_.getAsDouble("CrossoverDistribution"));
		try {
			crossover = CrossoverFactory.getCrossoverOperator(setting_.getAsStr("CrossoverName"), parameters);
		} catch (JMException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		algorithm.setInputParameter("ismax", setting_.getAsBool("IsMax"));
		algorithm.setInputParameter("IsNorm", setting_.getAsBool("IsNorm"));
		algorithm.setInputParameter("outputNormal",setting_.getAsBool("outputNormal"));
		algorithm.addOperator("crossover", crossover);
		algorithm.addOperator("mutation", mutation);

	}








}
