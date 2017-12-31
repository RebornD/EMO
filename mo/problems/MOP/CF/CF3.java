
package mo.problems.MOP.CF;

import java.util.HashMap;
import java.util.Map;

import mo.core.Problem;
import mo.core.Solution;
import mo.util.JMException;


/**
 * Class representing problem DTLZ1
 */
public class CF3 extends Problem {
 /**
  * Creates a default DTLZ1 problem (7 variables and 3 objectives)
  * @param solutionType The solution type must "Real" or "BinaryReal".
  */
  public CF3(String solutionType) throws ClassNotFoundException {
    this(7, 3);
  } // DTLZ1
  public CF3(HashMap<String, Object> parameters) {
		this((Integer) parameters.get("numberOfVariables"),(Integer) parameters.get("numberOfObjectives"));
	}
  /**
  * Creates a DTLZ1 problem instance
  * @param numberOfVariables Number of variables
  * @param numberOfObjectives Number of objective functions
  * @param solutionType The solution type must "Real" or "BinaryReal".
  */
  public CF3(  int  numberOfVariables,  int  numberOfObjectives) {
	 numberOfVariables_  = numberOfVariables;
    numberOfObjectives_ = 2;
    problemName_        = "CF3";
    numberOfConstraint_ = 1;

    lowerLimit_ = new double[numberOfVariables_];
    upperLimit_ = new double[numberOfVariables_];
    for (int var = 0; var < numberOfVariables; var++){
      lowerLimit_[var] = -2.0;
      upperLimit_[var] = 2.0;
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
	    double sum1 = 0.0, sum2 = 0.0;
		int count1 = 0, count2 = 0;
		double yj,t;
		double pj;
		int nx = numberOfVariables_;
		double N = 0, a = 0.0;
		for(int i = 0;i < x.length;i++){
			x[i] = solution.getValue(i);
		}

		N = 2.0; a = 1.0;

		sum1 = sum2 = 0.0;
		count1 = count2 = 0;
		double prod1 = 1.0,prod2 = 1.0;
		for (int j = 2; j <= nx; j++)
		{
			yj = x[j - 1] - Math.sin(6.0*Math.PI*x[0] + j*Math.PI / nx);
			pj = Math.cos(20.0*yj*Math.PI / Math.sqrt(j + 0.0));
			if (j % 2 == 0)
			{
				sum2 += yj*yj;
				prod2 *= pj;
				count2++;
			}
			else
			{
				sum1 += yj*yj;
				prod1 *= pj;
				count1++;
			}
		}

		f[0] = x[0] + 2.0*(4.0*sum1 - 2.0*prod1 + 2.0) / (double)count1;
		f[1] = 1.0 - x[0] * x[0] + 2.0*(4.0*sum2 - 2.0*prod2 + 2.0) / (double)count2;
		c[0] = f[1] + f[0] * f[0] - a*Math.sin(N*Math.PI*(f[0] * f[0] - f[1] + 1.0)) - 1.0;


	    for (int i = 0; i < numberOfObjectives_; i++)
	      solution.setObjective(i,f[i]);

	    solution.setConstrain(0, c[0]  <= 0.0 ? -1*c[0] : 0.0 );
	    solution.calctotalCalc();
  }
  public double MYSIGN(double x){
	  return x > 0.0 ? 1.0 :-1.0;
  }

  @Override
  public void repair(Solution d, Map<String, Object> a) {
  }

} //evaluate
