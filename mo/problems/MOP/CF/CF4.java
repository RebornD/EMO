
package mo.problems.MOP.CF;

import java.util.HashMap;
import java.util.Map;

import mo.core.Problem;
import mo.core.Solution;
import mo.util.JMException;


/**
 * Class representing problem DTLZ1
 */
public class CF4 extends Problem {
 /**
  * Creates a default DTLZ1 problem (7 variables and 3 objectives)
  * @param solutionType The solution type must "Real" or "BinaryReal".
  */
  public CF4(String solutionType) throws ClassNotFoundException {
    this(7, 3);
  } // DTLZ1
  public CF4(HashMap<String, Object> parameters) {
		this((Integer) parameters.get("numberOfVariables"),(Integer) parameters.get("numberOfObjectives"));
	}
  /**
  * Creates a DTLZ1 problem instance
  * @param numberOfVariables Number of variables
  * @param numberOfObjectives Number of objective functions
  * @param solutionType The solution type must "Real" or "BinaryReal".
  */
  public CF4(  int  numberOfVariables,  int  numberOfObjectives) {
	 numberOfVariables_  = numberOfVariables;
    numberOfObjectives_ = 2;
    problemName_        = "CF4";
    numberOfConstraint_ = 1;

    lowerLimit_ = new double[numberOfVariables_];
    upperLimit_ = new double[numberOfVariables_];
    for (int var = 0; var < numberOfVariables; var++){
      lowerLimit_[var] = -2.0;
      upperLimit_[var] = 2.0;
    } //for
    int var = 0;
    lowerLimit_[var] = 0.0;
    upperLimit_[var] = 1.0;

    	variableType_  = 2 ;

  }
  public void evaluate(Solution solution) throws JMException {
	    double [] x = new double[numberOfVariables_];
	    double [] f = new double[numberOfObjectives_];
	    double [] c = new double[numberOfConstraint_];

	    int k = numberOfVariables_ - numberOfObjectives_ + 1;
	    int nx = numberOfVariables_;
	    double sum1 = 0.0, sum2 = 0.0;
		int count1 = 0, count2 = 0;
		double yj;
		double N = 10.0, a = 1.0;
		for(int i = 0;i < x.length;i++){
			x[i] = solution.getValue(i);
		}


		for (int j = 2; j <= nx; j++)
		{
			yj = x[j - 1] - Math.sin(6.0*Math.PI*x[0] + j*Math.PI / nx);
			if (j % 2 == 1)
			{
				sum1 += yj*yj;
			}
			else
			{
				if (j == 2)
					sum2 += yj < 1.5 - 0.75*Math.sqrt(2.0) ? Math.abs(yj) : (0.125 + (yj - 1)*(yj - 1));
				else
					sum2 += yj*yj;
			}
		}
		f[0] = x[0] + sum1;
		f[1] = 1.0 - x[0] + sum2;
		double t = x[1] - Math.sin(6.0*x[0] * Math.PI + 2.0*Math.PI / nx) - 0.5*x[0] + 0.25;
		c[0] = MYSIGN(t)*Math.abs(t) / (1 + Math.exp(4.0*Math.abs(t)));

	    for (int i = 0; i < numberOfObjectives_; i++)
	      solution.setObjective(i,f[i]);



	    solution.setConstrain(0, c[0]  > 0.0 ? 0.0 : -1*c[0] );

	    solution.calctotalCalc();
  }
  public double MYSIGN(double x){
	  return x > 0.0 ? 1.0 :-1.0;
  }


  @Override
  public void repair(Solution d, Map<String, Object> a) {
  }

} //evaluate
