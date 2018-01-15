
package mo.problems.MOP.CTP;

import java.util.HashMap;
import java.util.Map;

import mo.core.Problem;
import mo.core.Solution;
import mo.util.JMException;


/**
 * Class representing problem DTLZ1
 */
public class CTP1 extends Problem {
 /**
  * Creates a default DTLZ1 problem (7 variables and 3 objectives)
  * @param solutionType The solution type must "Real" or "BinaryReal".
  */
  public CTP1(String solutionType) throws ClassNotFoundException {
    this(7, 3);
  } // DTLZ1
  public CTP1(HashMap<String, Object> parameters) {
		this((Integer) parameters.get("numberOfVariables"),(Integer) parameters.get("numberOfObjectives"));
	}
  /**
  * Creates a DTLZ1 problem instance
  * @param numberOfVariables Number of variables
  * @param numberOfObjectives Number of objective functions
  * @param solutionType The solution type must "Real" or "BinaryReal".
  */
  public CTP1(  int  numberOfVariables,  int  numberOfObjectives) {
	 numberOfVariables_  = 4;
    numberOfObjectives_ = 2;
    problemName_        = "CTP1";
    numberOfConstraint_ = 2;

    lowerLimit_ = new double[numberOfVariables_];
    upperLimit_ = new double[numberOfVariables_];
    for (int var = 0; var < numberOfVariables_; var++){
      lowerLimit_[var] = -5.0;
      upperLimit_[var] = 5.0;
    } //for
    lowerLimit_[0] = 0.0;
    upperLimit_[0] = 1.0;

    	variableType_  = 2 ;

  }
  public void evaluate(Solution solution) throws JMException {
	    double [] x = new double[numberOfVariables_];
	    double [] f = new double[numberOfObjectives_];
	    double [] c = new double[numberOfConstraint_];
	    int k = numberOfVariables_ - numberOfObjectives_ + 1;

		for(int i = 0;i < x.length;i++){
			x[i] = solution.getValue(i);
		}
		double g = 0;
		g = 31;
		for(int i = 1; i < numberOfVariables_;i++){
			g+= x[i]*x[i] - 10*Math.cos(4*Math.PI*x[i]);
		}

		f[0] = x[0];
		f[1] = g*(Math.exp(-1*x[0]/g));
		c[0] = f[1] - 0.858*Math.exp(-0.541*f[0]);
		c[1] = f[1] - 0.728*Math.exp(-0.295*f[0]);

	    for (int i = 0; i < numberOfObjectives_; i++)
	      solution.setObjective(i,f[i]);


	    solution.setConstrain(0, c[0]  > 0.0 ? 0.0 : -1*c[0] );
	    solution.setConstrain(0, c[1]  > 0.0 ? 0.0 : -1*c[1] );

	    solution.calctotalCalc();

  }

  @Override
  public void repair(Solution d, Map<String, Object> a) {
  }

} //evaluate
