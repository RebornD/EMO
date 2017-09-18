package util.ScalarzingFunction;

import core.Solution;
import util.JMException;

public class WeightedSumForMin extends ScalarzingFunction{

	public double execute(Solution ind ,double[] weigh,double[] referencePoint) throws JMException{
		double sum =0 ;

			for(int i=0;i<weigh.length;i++){

				sum += weigh[i]*ind.getObjective(i);
			}

			return (sum);
	};

	public String getFunctionName() {
		return "WeightedSumForMin";
	}

}
