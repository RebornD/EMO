
package mo.problems.MOP.CF;

import java.util.HashMap;
import java.util.Map;

import mo.core.Problem;
import mo.core.Solution;
import mo.util.JMException;


/**
 * Class representing problem DTLZ1
 */
public class CF9 extends Problem {
 /**
  * Creates a default DTLZ1 problem (7 variables and 3 objectives)
  * @param solutionType The solution type must "Real" or "BinaryReal".
  */
  public CF9(String solutionType) throws ClassNotFoundException {
    this(7, 3);
  } // DTLZ1
  public CF9(HashMap<String, Object> parameters) {
		this((Integer) parameters.get("numberOfVariables"),(Integer) parameters.get("numberOfObjectives"));
	}
  /**
  * Creates a DTLZ1 problem instance
  * @param numberOfVariables Number of variables
  * @param numberOfObjectives Number of objective functions
  * @param solutionType The solution type must "Real" or "BinaryReal".
  */
  public CF9(  int  numberOfVariables,  int  numberOfObjectives) {
	 numberOfVariables_  = numberOfVariables;
    numberOfObjectives_ = 3;
    problemName_        = "CF9";
    numberOfConstraint_ = 1;

    lowerLimit_ = new double[numberOfVariables_];
    upperLimit_ = new double[numberOfVariables_];
    for (int var = 0; var < numberOfVariables; var++){
      lowerLimit_[var] = -2.0;
      upperLimit_[var] = 2.0;
    } //for
    lowerLimit_[0] = 0.0;
    upperLimit_[0] = 1.0;
    lowerLimit_[1] = 0.0;
    upperLimit_[1] = 1.0;

    	variableType_  = 2 ;

  }
  public void evaluate(Solution solution) throws JMException {
	    double [] x = new double[numberOfVariables_];
	    double [] f = new double[numberOfObjectives_];
	    double [] c = new double[numberOfConstraint_];

	    int k = numberOfVariables_ - numberOfObjectives_ + 1;
	    int nx = numberOfVariables_;
	    double sum1 = 0.0, sum2 = 0.0,sum3 = 0.0;
		int count1 = 0, count2 = 0,count3 = 0;
		double yj;
		double N = 2.0, a = 3.0;
		for(int i = 0;i < x.length;i++){
			x[i] = solution.getValue(i);
		}


		for (int j = 3; j <= nx; j++)
		{
			yj = x[j - 1] - 2.0*x[1] * Math.sin(2.0*Math.PI*x[0] + j*Math.PI / nx);
			if (j % 3 == 1)
			{
				sum1 += yj*yj;
				count1++;
			}
			else if (j % 3 == 2)
			{
				sum2 += yj*yj;
				count2++;
			}
			else
			{
				sum3 += yj*yj;
				count3++;
			}
		}
		f[0] = Math.cos(0.5*Math.PI*x[0])*Math.cos(0.5*Math.PI*x[1]) + 2.0*sum1 / (double)count1;
		f[1] = Math.cos(0.5*Math.PI*x[0])*Math.sin(0.5*Math.PI*x[1]) + 2.0*sum2 / (double)count2;
		f[2] = Math.sin(0.5*Math.PI*x[0]) + 2.0*sum3 / (double)count3;
		c[0] = (f[0] * f[0] + f[1] * f[1]) / (1 - f[2] * f[2]) - a*Math.abs(Math.sin(N*Math.PI*((f[0] * f[0] - f[1] * f[1]) / (1 - f[2] * f[2]) + 1.0))) - 1.0;


	    for (int i = 0; i < numberOfObjectives_; i++)
	      solution.setObjective(i,f[i]);

	    solution.setConstrain(0, c[0]  <= 0.0 ? -1*c[0] : 0.0 );
	    solution.setConstrain(0, c[1]  <= 0.0 ? -1*c[1] : 0.0 );

	    solution.calctotalCalc();
  }
  public double MYSIGN(double x){
	  return x > 0.0 ? 1.0 :-1.0;
  }


  @Override
  public void repair(Solution d, Map<String, Object> a) {
  }

} //evaluate
