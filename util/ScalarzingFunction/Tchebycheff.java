package util.ScalarzingFunction;

import core.Solution;
import util.JMException;

public class Tchebycheff  extends ScalarzingFunction{

	public double execute(Solution ind,double[] weight,double[] referencePoint) throws JMException{
		double max=-1;
		double fnvue;
		max = weight[0]*Math.abs( referencePoint[0] - ind.getObjective(0));

			for(int i=0;i<weight.length;i++){
				fnvue = weight[i]*Math.abs(referencePoint[i] - ind.getObjective(i));
				if(max < fnvue)
					max = fnvue;
			}
//
			return -1*(max);
	}


	public String getFunctionName() {
		return "Tchebycheff";
	}


}
