package util.ScalarzingFunction;

import core.Solution;
import util.JMException;

public class WeightedSum extends ScalarzingFunction{

	public double execute(Solution ind ,double[] weight,double[] referencePoint) throws JMException{
		double sum =0 ;
			for(int i=0;i<weight.length;i++){
				sum += weight[i]*ind.getObjective(i);
			}
			

			return sum;
	};

	public String getFunctionName() {
		return "WeightedSum";
	}

}
