//  DTLZ4.java
//
//  Author:
//       Antonio J. Nebro <antonio@lcc.uma.es>
//       Juan J. Durillo <durillo@lcc.uma.es>
//
//  Copyright (c) 2011 Antonio J. Nebro, Juan J. Durillo
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU Lesser General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU Lesser General Public License for more details.
//
//  You should have received a copy of the GNU Lesser General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>.

package mo.problems.MOP.CDTLZ;

import java.util.HashMap;
import java.util.Map;

import mo.core.Problem;
import mo.core.Solution;
import mo.util.JMException;

/**
 * Class representing problem DTLZ4
 */
public class CF_DTLZ3 extends Problem{


 /**
  * Creates a default DTLZ4 problem (12 variables and 3 objectives)
  * @param solutionType The solution type must "Real" or "BinaryReal".
  */
  public CF_DTLZ3(String solutionType) throws ClassNotFoundException {
    this(12, 3);
  }
  public CF_DTLZ3(HashMap<String, Object> parameters) {
		this((Integer) parameters.get("numberOfVariables"),(Integer) parameters.get("numberOfObjectives"));
	}
 /**
  * Creates a DTLZ4 problem problem instance
  * @param numberOfVariables Number of variables
  * @param numberOfObjectives Number of objective functions
  * @param solutionType The solution type must "Real" or "BinaryReal".
  */
  public CF_DTLZ3( int numberOfVariables, int numberOfObjectives) {
    numberOfVariables_  = numberOfVariables;
    numberOfObjectives_ = numberOfObjectives;
    problemName_        = "CF_DTLZ3";
    numberOfConstrain_ = 1;

    lowerLimit_ = new double[numberOfVariables_];
    upperLimit_ = new double[numberOfVariables_];
    for (int var = 0; var < numberOfVariables_; var++){
      lowerLimit_[var] = 0.0;
      upperLimit_[var] = 1.0;
    }
  	variableType_  = 2 ;

  } //DTLZ4

  /**
  * Evaluates a solution
  * @param solution The solution to evaluate
   * @throws JMException
  */
  public void evaluate(Solution solution) throws JMException {

	    double [] x = new double[numberOfVariables_];
	    double [] f = new double[numberOfObjectives_];
	    int k = numberOfVariables_ - numberOfObjectives_ + 1;

	    for (int i = 0; i < numberOfVariables_; i++)
	      x[i] = solution.getValue(i);

	    double g = 0.0;
	    for (int i = numberOfVariables_ - k; i < numberOfVariables_; i++)
	      g += (x[i] - 0.5)*(x[i] - 0.5) - Math.cos(20.0 * Math.PI * (x[i] - 0.5));

	    g = 100.0 * (k + g);
	    for (int i = 0; i < numberOfObjectives_; i++)
	      f[i] = 1.0 + g;

	    for (int i = 0; i < numberOfObjectives_; i++){
	      for (int j = 0; j < numberOfObjectives_ - (i + 1); j++)
	        f[i] *= Math.cos(x[j]*0.5*java.lang.Math.PI);
	        if (i != 0){
	          int aux = numberOfObjectives_ - (i + 1);
	          f[i] *= Math.sin(x[aux]*0.5*java.lang.Math.PI);
	        } // if
	    } //for

	    for (int i = 0; i < numberOfObjectives_; i++)
	      solution.setObjective(i,f[i]);


	    double c1 = 0 ,c2  = 0;
	    double d_ = 0;


	    for(int i=0;i<numberOfObjectives_;i++){
	    	d_ = f[i]*f[i];
	    }
	    c1 = (d_-9)*(d_ -  Math.pow(getR(), 2.0));
	    c2 = (d_-4)*(d_ - 0);


	    double c =Math.abs(Math.max(c1, 0) +Math.max(c2, 0)  );

	    System.out.print("test");
	    solution.setConstrain(0, c > 0.0 ? c : 0.0 );
	    solution.calctotalCalc();

  } // evaluate
  private double getR() {
	  if (numberOfObjectives_ <= 3) {
		  return 9;
	  } else if (numberOfObjectives_ <= 8) {
		  return 12.5;
	  } else {
		  return 15;
	  }
  }

  @Override
  public void repair(Solution d, Map<String, Object> a) {
  }

}

