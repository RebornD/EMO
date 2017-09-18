package main;

import java.util.HashMap;

import javax.naming.NameNotFoundException;

import core.AlgorithmMain;
import core.Operator;
import core.Problem;
import experiments.Setting;
import metaheuristics.smsemoa_igd.SMSEMOAIGD;
import operators.crossover.CrossoverFactory;
import operators.mutation.MutationFactory;
import problems.MOP.MOPFactory;
import util.JMException;

public class SMSEMOAIGDMain extends AlgorithmMain{

	public SMSEMOAIGDMain(Setting test) {
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
		problem = MOPFactory.getMOP(Problemname,d,"SMSEMOAIGD");
		algorithm = new SMSEMOAIGD(problem);
		String dddname = problem.getNumberOfObjectives()  + "OBJ" + setting_.getAsInt("Division")+"div";
		algorithm.setInputParameter("numberOfObjectives",OBJ);
		algorithm.setInputParameter("populationSize", setting_.getAsInt("populationSize"));


		//		double[][] ref = fileReading(setting_.getAsStr("reffile"));
//		algorithm.setInputParameter("ref", ref);

		DirectoryName = "result/SMSEMOAIGD/" + Problemname+"/"+dddname;
		algorithm.setInputParameter("DirectoryName",  DirectoryName);
		System.out.println();
		System.out.println(DirectoryName);
		algorithm.setInputParameter("Norm",setting_.getAsBool("IsNorm"));
		algorithm.setInputParameter("maxEvaluations", setting_.getAsInt("maxEvaluations"));
		algorithm.setInputParameter("numberOfParents", 2);
		parameters = new HashMap();
		parameters.put("Mutationprobability",setting_.getAsDouble("MutationProbability"));
		parameters.put("MutationdistributionIndex",setting_.getAsDouble("MutationDistribution"));

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
		algorithm.setInputParameter("numberOfDivision",setting_.getAsInt("Division"));
 		int innnerWeightDivision =  setting_.getAsInt("InnerWeightDivision");
		innnerWeightDivision =  setting_.getAsInt("Objectives") >= 6 ? innnerWeightDivision : 0;
		algorithm.setInputParameter("InnerWeightDivision",innnerWeightDivision);


		algorithm.addOperator("crossover", crossover);
		algorithm.addOperator("mutation", mutation);

		algorithm.setInputParameter("ismax", setting_.getAsBool("isMax"));
		algorithm.setInputParameter("isNorm", setting_.getAsBool("IsNorm"));

	}


	private double[][] fileReading(String asStr) {


		return null;
	}



}
