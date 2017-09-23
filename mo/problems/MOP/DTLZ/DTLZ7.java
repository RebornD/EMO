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

package mo.problems.MOP.DTLZ;

import java.util.HashMap;
import java.util.Map;

import mo.core.Problem;
import mo.core.Solution;
import mo.util.JMException;

/**
 * Class representing problem DTLZ4
 */
public class DTLZ7 extends Problem{


 /**
  * Creates a default DTLZ4 problem (12 variables and 3 objectives)
  * @param solutionType The solution type must "Real" or "BinaryReal".
  */
  public DTLZ7(String solutionType) throws ClassNotFoundException {
    this(12, 3);
  }
  public DTLZ7(HashMap<String, Object> parameters) {
		this((Integer) parameters.get("numberOfVariables"),(Integer) parameters.get("numberOfObjectives"));
	}
 /**
  * Creates a DTLZ4 problem problem instance
  * @param numberOfVariables Number of variables
  * @param numberOfObjectives Number of objective functions
  * @param solutionType The solution type must "Real" or "BinaryReal".
  */
  public DTLZ7( int numberOfVariables, int numberOfObjectives) {
    numberOfVariables_  = numberOfVariables;
    numberOfObjectives_ = numberOfObjectives;
    problemName_        = "DTLZ7";

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

	    //Calculate g
	    double g = 0.0;
	    for (int i = this.numberOfVariables_ - k; i < numberOfVariables_; i++)
	      g += x[i] ;

	    g = 1 + (9.0 * g) / k;
	    //<-

	    //Calculate the value of f1,f2,f3,...,fM-1 (take acount of vectors start at 0)
	      System.arraycopy(x, 0, f, 0, numberOfObjectives_ - 1);
	    //<-

	    //->Calculate fM
	    double h = 0.0;
	    for (int i = 0; i < numberOfObjectives_ -1; i++)
	      h += (f[i]/(1.0 + g))*(1 + Math.sin(3.0 * Math.PI * f[i]));

	    h = numberOfObjectives_ - h;

	    f[numberOfObjectives_-1] = (1 + g) * h;
	    //<-

	    //-> Setting up the value of the objetives
	    for (int i = 0; i < numberOfObjectives_; i++)
	      solution.setObjective(i,f[i]);
  } // evaluate

  @Override
  public void repair(Solution d, Map<String, Object> a) {
  }


}

