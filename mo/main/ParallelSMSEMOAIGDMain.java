package mo.main;

import java.util.HashMap;

import javax.naming.NameNotFoundException;

import experiments.Setting;
import mo.core.AlgorithmMain;
import mo.core.Operator;
import mo.core.Problem;
import mo.metaheuristics.parallelsmsemoaigd.ParallelSMSEMOAIGD;
import mo.operators.crossover.CrossoverFactory;
import mo.operators.mutation.MutationFactory;
import mo.problems.MOP.MOPFactory;
import mo.util.JMException;

public class ParallelSMSEMOAIGDMain extends AlgorithmMain{

	public ParallelSMSEMOAIGDMain(Setting test) {
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
		problem = MOPFactory.getMOP(Problemname,setting_,"SMSEMOAIGD");
		algorithm = new ParallelSMSEMOAIGD(problem);
		algorithm.setInputParameter("numberOfObjectives",OBJ);
		algorithm.setInputParameter("populationSize", setting_.getAsInt("populationSize"));
		String dddname = problem.getNumberOfObjectives()  + "OBJ" + "/pop" +  setting_.getAsInt("populationSize");


		//		double[][] ref = fileReading(setting_.getAsStr("reffile"));
//		algorithm.setInputParameter("ref", ref);

		DirectoryName = "result/ParallelSMSEMOAIGD/" + Problemname+"/"+dddname;
		algorithm.setInputParameter("DirectoryName",  DirectoryName);
		System.out.println();
		System.out.println(DirectoryName);
		algorithm.setInputParameter("Norm",setting_.getAsBool("IsNorm"));
		algorithm.setInputParameter("maxEvaluations", setting_.getAsInt("maxEvaluations"));
		algorithm.setInputParameter("numberOfParents", 2);
		parameters = new HashMap();
		parameters.put("Mutationprobability",setting_.getAsDouble("MutationProbability"));
		parameters.put("MutationdistributionIndex",setting_.getAsDouble("MutationDistribution"));



		String Scpu = setting_.getAsStr("nCPU");
		int cpu;
		if(Scpu.equalsIgnoreCase("max")){
			cpu = Runtime.getRuntime().availableProcessors();
		} else {
			cpu = setting_.getAsInt("nCPU");
			cpu = cpu < Runtime.getRuntime().availableProcessors() ? cpu : Runtime.getRuntime().availableProcessors();
		}
		algorithm.setInputParameter("nCPU", cpu);


		try {
			mutation = MutationFactory.getMutationOperator(setting_.getAsStr("MutationName"), parameters);
		} catch (JMException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
//		algorithm.setInputParameter("ReferencePoint", ref);
		parameters.put("Crossoverprobability",setting_.getAsDouble("CrossoverProbability"));
		parameters.put("CrossoverdistributionIndex",setting_.getAsDouble("CrossoverDistribution"));

		try {
			crossover = CrossoverFactory.getCrossoverOperator(setting_.getAsStr("CrossoverName"), parameters);
		} catch (JMException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}


		algorithm.addOperator("crossover", crossover);
		algorithm.addOperator("mutation", mutation);

		algorithm.setInputParameter("ismax", setting_.getAsBool("isMax"));
		algorithm.setInputParameter("isNorm", setting_.getAsBool("IsNorm"));

	}


	private double[][] fileReading(String asStr) {


		return null;
	}



}
