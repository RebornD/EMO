package util.ScalarzingFunction;

import core.Solution;
import util.JMException;

public abstract class ScalarzingFunction {

	abstract public double execute(Solution ind,double[] weight,double[] referencePoint) throws JMException;

	abstract public String getFunctionName();
}
