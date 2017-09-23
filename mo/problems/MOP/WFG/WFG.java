//  WFG.java
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

package mo.problems.MOP.WFG;

import java.util.Random;

import mo.core.Problem;

/**
 * Implements a reference abstract class for all WFG test problems
 * Reference: Simon Huband, Luigi Barone, Lyndon While, Phil Hingston
 *            A Scalable Multi-objective Test Problem Toolkit.
 *            Evolutionary Multi-Criterion Optimization:
 *            Third International Conference, EMO 2005.
 *            Proceedings, volume 3410 of Lecture Notes in Computer Science
 */
public abstract class WFG extends Problem{

  /**
   * stores a epsilon default value
   */
  private final double epsilon = (double)1e-7;

  protected int k_; //Var for walking fish group
  protected int M_;
  protected int l_;
  protected int [] A_;
  protected int [] S_;
  protected int D_ = 1;
  protected Random random = new Random();

  /**
  * Constructor
  * Creates a WFG problem
  * @param k position-related parameters
  * @param l distance-related parameters
  * @param M Number of objectives
  * @param solutionType The solution type must "Real" or "BinaryReal".
  */
  public WFG ( Integer k, Integer l, Integer M) {
    this.k_ = k;
    this.l_ = l;
    this.M_ = M;
    numberOfVariables_  = this.k_ + this.l_;
    numberOfObjectives_ = this.M_;

    lowerLimit_ = new double[numberOfVariables_];
    upperLimit_ = new double[numberOfVariables_];
    for (int var = 0; var < numberOfVariables_; var++) {
      lowerLimit_[var] = 0;
      upperLimit_[var] = 2 * (var + 1);
    }
    
    for (int var = 0; var < numberOfVariables_; var++) {
      	lowerLimit_[var] = 0;
        upperLimit_[var] = 2 * (var + 1);
      }
  } // WFG

  /**
   * Gets the x vector (consulte WFG tooltik reference)
   */
  public double [] calculate_x(double [] t){
    double [] x = new double[M_];

    for (int i = 0; i < M_-1; i++){
      x[i] = Math.max(t[M_-1],A_[i]) * (t[i]  - (double)0.5) + (double)0.5;
    }

    x[M_-1] = t[M_-1];

    return x;
  } // calculate_x

  /**
   * Normalizes a vector (consulte WFG toolkit reference)
   */
  public double [] normalise(double [] z){
    double [] result = new double[z.length];

    for (int i = 0; i < z.length; i++){
      double bound = (double)2.0 * (i + 1);
      result[i] = z[i] / bound;
      result[i] = correct_to_01(result[i]);
    }

    return result;
  } // normalize


  /**
   */
  public double correct_to_01(double a){
    double min = (double)0.0;
    double max = (double)1.0;

    double min_epsilon = min - epsilon;
    double max_epsilon = max + epsilon;

    if (( a <= min && a >= min_epsilon ) || (a >= min && a <= min_epsilon)) {
      return min;
    } else if (( a >= max && a <= max_epsilon ) || (a <= max && a >= max_epsilon)) {
      return max;
    } else {
      return a;
    }
  } // correct_to_01

  /**
  * Gets a subvector of a given vector
  * (Head inclusive and tail inclusive)
  * @param z the vector
  * @return the subvector
  */
  public double [] subVector(double [] z, int head, int tail){
    int size = tail - head + 1;
    double [] result = new double[size];

    System.arraycopy(z, head, result, head - head, tail + 1 - head);

    return result;
  } // subVector

  /**
  * Evaluates a solution
  * @param variables The solution to evaluate
  * @return a double [] with the evaluation results
  */
  abstract public double[] evaluate(double[] variables);
  // evaluate
}
