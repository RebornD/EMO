
package problems.MOP.ScaledDTLZ;

import java.util.HashMap;
import java.util.Map;

import core.Problem;
import core.Solution;
import util.JMException;

/**
 * Class representing problem DTLZ1
 */
public class ScaledDTLZ2 extends Problem {
 /**
  * Creates a default DTLZ1 problem (7 variables and 3 objectives)
  * @param solutionType The solution type must "Real" or "BinaryReal".
  */
  public ScaledDTLZ2(String solutionType) throws ClassNotFoundException {
    this(7, 3);
  } // DTLZ1
  public ScaledDTLZ2(HashMap<String, Object> parameters) {
		this((Integer) parameters.get("numberOfVariables"),(Integer) parameters.get("numberOfObjectives"));
	}
  /**
  * Creates a DTLZ1 problem instance
  * @param numberOfVariables Number of variables
  * @param numberOfObjectives Number of objective functions
  * @param solutionType The solution type must "Real" or "BinaryReal".
  */
  public ScaledDTLZ2(  int  numberOfVariables,  int  numberOfObjectives) {
	 numberOfVariables_  = numberOfVariables;
    numberOfObjectives_ = numberOfObjectives;
    problemName_        = "ScaledDTLZ2";

    lowerLimit_ = new double[numberOfVariables_];
    upperLimit_ = new double[numberOfVariables_];
    for (int var = 0; var < numberOfVariables; var++){
      lowerLimit_[var] = 0.0;
      upperLimit_[var] = 1.0;
    } //for

    	variableType_  = 2 ;

  }
  public void evaluate(Solution solution) throws JMException {

	    double [] x = new double[numberOfVariables_];
	    double [] f = new double[numberOfObjectives_];
	    int k = numberOfVariables_ - numberOfObjectives_ + 1;

	    for (int i = 0; i < numberOfVariables_; i++)
	      x[i] = solution.getValue(i);

	    double g = 0.0;
	    for (int i = numberOfVariables_ - k; i < numberOfVariables_; i++)
	      g += (x[i] - 0.5)*(x[i] - 0.5);

	    for (int i = 0; i < numberOfObjectives_; i++)
	      f[i] = 1.0 + g;

	    for (int i = 0; i < numberOfObjectives_; i++){
	      for (int j = 0; j < numberOfObjectives_ - (i + 1); j++)
	        f[i] *= Math.cos(x[j]*0.5*Math.PI);
	        if (i != 0){
	          int aux = numberOfObjectives_ - (i + 1);
	          f[i] *= Math.sin(x[aux]*0.5*Math.PI);
	        } //if
	    } // for
	    for(int i=0;i<f.length;i++)
	    	f[i] = f[i]*Math.pow(10, i);


	    for (int i = 0; i < numberOfObjectives_; i++)
	    	solution.setObjective(i,f[i]);

  }

  @Override
  public void repair(Solution d, Map<String, Object> a) {
  }


} //evaluate
