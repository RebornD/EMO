package util.ScalarzingFunction;

import core.Solution;
import util.JMException;

public abstract class ScalarzingFunction {

	public double execute(Solution ind,double[] weight,double[] referencePoint) throws JMException{
		return execute(ind.getObjectives(),weight,referencePoint);
	}

	abstract public double execute(double[] d, double[] s, double[] p) throws JMException;


	abstract public String getFunctionName();
}
