//  DTLZ1.java
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

package problems.MOP.CDTLZ;

import java.util.HashMap;
import java.util.Map;

import core.Problem;
import core.Solution;
import util.JMException;

/**
 * Class representing problem DTLZ1
 */
public class C2_DTLZ2 extends Problem {
 /**
  * Creates a default DTLZ1 problem (7 variables and 3 objectives)
  * @param solutionType The solution type must "Real" or "BinaryReal".
  */
  public C2_DTLZ2(String solutionType) throws ClassNotFoundException {
    this(7, 3);
  } // DTLZ1

	public C2_DTLZ2(HashMap<String, Object> parameters) {
		this((Integer) parameters.get("numberOfVariables"),(Integer) parameters.get("numberOfObjectives"));
	}
  /**
  * Creates a DTLZ1 problem instance
  * @param numberOfVariables Number of variables
  * @param numberOfObjectives Number of objective functions
  * @param solutionType The solution type must "Real" or "BinaryReal".
  */
  public C2_DTLZ2(  int  numberOfVariables,  int  numberOfObjectives) {
	 numberOfVariables_  = numberOfVariables;
    numberOfObjectives_ = numberOfObjectives;
    problemName_        = "C2_DTLZ2";


    numberOfConstrain_ = 1;


    lowerLimit_ = new double[numberOfVariables_];
    upperLimit_ = new double[numberOfVariables_];
    for (int var = 0; var < numberOfVariables; var++){
      lowerLimit_[var] = 0.0;
      upperLimit_[var] = 1.0;
    } //for

    	variableType_  = 2 ;
  }

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


	  double r = numberOfObjectives_ > 3 ? 0.5 : 0.4;
	  double v1 = Double.POSITIVE_INFINITY;
	  double v2 = 0.0;

	  for (int i = 0; i < numberOfObjectives_; i++) {
	   double sum = Math.pow(solution.getObjective(i)-1.0, 2.0);

	   for (int j = 0; j < numberOfObjectives_; j++) {
	    if (i != j) {
	     sum += Math.pow(solution.getObjective(j), 2.0);
	    }
	   }

	   v1 = Math.min(v1, sum - Math.pow(r, 2.0));
	   v2 += Math.pow(f[i] -
	     1.0 / Math.sqrt(numberOfObjectives_), 2.0);
	  }
	  double c = Math.min(v1, v2 - Math.pow(r, 2.0));

	    solution.setConstrain(0, c < 0.0 ? -1*c : 0.0 );
	  solution.calctotalCalc();

  } // evaluate

  @Override
  public void repair(Solution d, Map<String, Object> a) {
  }

}

