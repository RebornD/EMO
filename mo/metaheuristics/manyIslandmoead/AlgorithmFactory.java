package mo.metaheuristics.manyIslandmoead;

import mo.core.Algorithm;
import mo.core.Problem;
import mo.util.Configuration;
import mo.util.JMException;

public class AlgorithmFactory {


	public static Algorithm getAlgorithm(String name, Problem problem_) throws JMException{


		if(name.equalsIgnoreCase("N+N")){
			return new mu_mu_MOEAD(problem_);
		} else if (name.equalsIgnoreCase("N+N")){

		}


		 else {
			Configuration.logger_.severe("Operator '" + name + "' not found ");
			Class cls = java.lang.String.class;
			String name2 = cls.getName();
			throw new JMException("Exception in " + name2 + ".getMutationOperator()");
		}
		return null;
	}

}

