
package mo.problems.MOP.CDTLZ;

import java.util.HashMap;
import java.util.Map;

import mo.core.Problem;
import mo.core.Solution;
import mo.util.JMException;


/**
 * Class representing problem DTLZ1
 */
public class C1_DTLZ1 extends Problem {
 /**
  * Creates a default DTLZ1 problem (7 variables and 3 objectives)
  * @param solutionType The solution type must "Real" or "BinaryReal".
  */
  public C1_DTLZ1(String solutionType) throws ClassNotFoundException {
    this(7, 3);
  } // DTLZ1
  public C1_DTLZ1(HashMap<String, Object> parameters) {
		this((Integer) parameters.get("numberOfVariables"),(Integer) parameters.get("numberOfObjectives"));
	}
  /**
  * Creates a DTLZ1 problem instance
  * @param numberOfVariables Number of variables
  * @param numberOfObjectives Number of objective functions
  * @param solutionType The solution type must "Real" or "BinaryReal".
  */
  public C1_DTLZ1(  int  numberOfVariables,  int  numberOfObjectives) {
	 numberOfVariables_  = numberOfVariables;
    numberOfObjectives_ = numberOfObjectives;
    problemName_        = "C1_DTLZ1";
    numberOfConstraint_ = 1;

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
	      g += (x[i] - 0.5)*(x[i] - 0.5) - Math.cos(20.0 * Math.PI * ( x[i] - 0.5));

	    g = 100 * (k + g);
	    for (int i = 0; i < numberOfObjectives_; i++)
	      f[i] = (1.0 + g) * 0.5;

	    for (int i = 0; i < numberOfObjectives_; i++){
	      for (int j = 0; j < numberOfObjectives_ - (i + 1); j++)
	    	  f[i] *= x[j];
	        	if (i != 0){
	          int aux = numberOfObjectives_ - (i + 1);
	          f[i] *= 1 - x[aux];
	        } //if
	    }//for

	    for (int i = 0; i < numberOfObjectives_; i++)
	      solution.setObjective(i,f[i]);

	    double c = 1.0 - solution.getObjective(numberOfObjectives_-1) / 0.6;
	    for (int i = 0; i < numberOfObjectives_-2; i++) {
	     c -= solution.getObjective(i) / 0.5;
	    }
	    solution.setConstrain(0, c  <= 0.0 ? -1*c : 0.0 );
	    solution.calctotalCalc();
  }

  @Override
  public void repair(Solution d, Map<String, Object> a) {
  }

} //evaluate
