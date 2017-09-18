//  WFG1.java
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

package problems.MOP.InvertedWFG;

import java.util.Map;

import core.Solution;
import util.JMException;

/**
 * This class implements the WFG1 problem
 * Reference: Simon Huband, Luigi Barone, Lyndon While, Phil Hingston
 *            A Scalable Multi-objective Test Problem Toolkit.
 *            Evolutionary Multi-Criterion Optimization:
 *            Third International Conference, EMO 2005.
 *            Proceedings, volume 3410 of Lecture Notes in Computer Science
 */
public class InvertedWFG1 extends WFG {

 /**
  * Constructor
  * Creates a default WFG1 instance with
  * 2 position-related parameters
  * 4 distance-related parameters
  * and 2 objectives
  * @param solutionType The solution type must "Real" or "BinaryReal".
  */
  public InvertedWFG1(String solutionType) throws ClassNotFoundException {
    this(2, 4, 2) ;
  } // WFG1

 /**
  * Creates a WFG1 problem instance
  * @param k Number of position parameters
  * @param l Number of distance parameters
  * @param M Number of objective functions
  * @param solutionType The solution type must "Real" or "BinaryReal".
  */
  public InvertedWFG1( Integer k, Integer l, Integer M) throws ClassNotFoundException {
    super(k,l,M);
    problemName_ = "InvertedWFG1";

    S_ = new int[M_];
    for (int i = 0; i < M_; i++)
      S_[i] = 2 * (i+1);

    A_ = new int[M_-1];
    for (int i = 0; i < M_-1; i++)
      A_[i] = 1;	variableType_  = 2 ;

  } // WFG1

  /**
  * Evaluates a solution
  * @param z The solution to evaluate
  * @return a double [] with the evaluation results
  */
  public double [] evaluate(double [] z){
    double [] y;

    y = normalise(z);
    y = t1(y,k_);
    y = t2(y,k_);
    try {
      y = t3(y);
    } catch (JMException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    y = t4(y,k_,M_);


    double [] result = new double[M_];
    double [] x = calculate_x(y);
    for (int m = 1; m <= M_ - 1 ; m++) {
      result [m-1] = D_*x[M_-1] + S_[m-1] * (new Shapes()).convex(x,m);
    }

    result[M_-1] = D_*x[M_-1] + S_[M_-1] * (new Shapes()).mixed(x,5,(double)1.0);

    return result;
  } // evaluate

  /**
   * WFG1 t1 transformation
   */
  public double [] t1(double [] z, int k){
    double [] result = new double[z.length];

    System.arraycopy(z, 0, result, 0, k);

    for (int i = k; i < z.length; i++) {
      result[i] = (new Transformations()).s_linear(z[i],(double)0.35);
    }

    return result;
  } // t1

  /**
  * WFG1 t2 transformation
  */
  public double [] t2(double [] z, int k){
    double [] result = new double[z.length];

    System.arraycopy(z, 0, result, 0, k);

    for (int i = k; i < z.length; i++) {
      result[i] = (new Transformations()).b_flat(z[i],(double)0.8,(double)0.75,(double)0.85);
    }

    return result;
  } // t2

  /**
  * WFG1 t3 transformation
   * @throws JMException
  */
  public double [] t3(double [] z) throws JMException{
    double [] result = new double[z.length];

    for (int i = 0; i < z.length; i++) {
      result[i] = (new Transformations()).b_poly(z[i],(double)0.02);
    }

    return result;
  } // t3

  /**
  * WFG1 t4 transformation
  */
  public double [] t4(double [] z, int k, int M){
    double [] result = new double[M];
    double [] w      = new double[z.length];

    for (int i = 0; i < z.length; i++) {
      w[i] = (double)2.0 * (i + 1);
    }

    for (int i = 1; i <= M-1; i++){
      int head = (i - 1)*k/(M-1) + 1;
      int tail = i * k / (M - 1);
      double [] subZ = subVector(z,head-1,tail-1);
      double [] subW = subVector(w,head-1,tail-1);
      result[i-1] = (new Transformations()).r_sum(subZ,subW);
    }

    int head = k + 1 - 1;
    int tail = z.length - 1;
    double [] subZ = subVector(z,head,tail);
    double [] subW = subVector(w,head,tail);
    result[M-1] = (new Transformations()).r_sum(subZ,subW);

    return result;
  } // t4

  /**
  * Evaluates a solution
  * @param solution The solution to evaluate
   * @throws JMException
  */
  public final void evaluate(Solution solution) throws JMException {
    double [] variables = new double[getNumberOfVariables()];

    for (int i = 0; i < getNumberOfVariables(); i++) {
      variables[i] = (double) solution.getValue(i);
    }

    double [] f = evaluate(variables);

    for (int i = 0; i < f.length; i++) {
      solution.setObjective(i,f[i]);
    }
  } //evaluate

  @Override
  public void repair(Solution d, Map<String, Object> a) {
  }



} // WFG1
