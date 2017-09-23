/**
 * WFG9.java
 * @author Juan J. Durillo
 * @version 1.0
 */

package mo.problems.MOP.InvertedWFG;

import java.util.HashMap;
import java.util.Map;

import mo.core.Solution;
import mo.util.JMException;


/**
* Creates a default WFG9 problem with
* 2 position-related parameters,
* 4 distance-related parameters,
* and 2 objectives
*/
public class InvertedWFG9 extends WFG{

 /**
  * Creates a default WFG9 with
  * 2 position-related parameters,
  * 4 distance-related parameters,
  * and 2 objectives
  * @param solutionType The solution type must "Real" or "BinaryReal".
  */
  public InvertedWFG9(String solutionType) throws ClassNotFoundException {
    this( 2, 4, 2) ;
  } // WFG9

 /**
  * Creates a WFG9 problem instance
  * @param k Number of position variables
  * @param l Number of distance variables
  * @param M Number of objective functions
  * @param solutionType The solution type must "Real" or "BinaryReal".
  */
  public InvertedWFG9 ( Integer k, Integer l, Integer M) throws ClassNotFoundException {
    super( k,l,M);
    problemName_ = "InvertedWFG9";

    S_ = new int[M_];
    for (int i = 0; i < M_; i++) {
      S_[i] = 2 * (i+1);
    }
    variableType_  = 2 ;
    A_ = new int[M_-1];
    for (int i = 0; i < M_-1; i++) {
      A_[i] = 1;
    }
  } // WFG9


  /**
  * Evaluates a solution
  * @param z The solution to evaluate
  * @return double [] with the evaluation results
  */
  public double [] evaluate(double [] z){
    double [] y;

    y = normalise(z);
    y = t1(y,k_);
    y = t2(y,k_);
    y = t3(y,k_,M_);

    double [] result = new double[M_];
    double [] x = calculate_x(y);
    for (int m = 1; m <= M_ ; m++) {
      result [m-1] = D_*x[M_-1] + S_[m-1] * (new Shapes()).concave(x,m);
    }
    return result;
  } //evaluate
	public InvertedWFG9(HashMap<String, Object> parameters) throws ClassNotFoundException {
		this( (Integer) parameters.get("K"),   (Integer) parameters.get("I"),(Integer) parameters.get("M"));
	}

  /**
   * WFG9 t1 transformation
   */
  public double [] t1(double [] z, int k){
    double [] result = new double[z.length];
    double [] w      = new double[z.length];

    for (int i = 0; i < w.length; i++) {
      w[i] = (double)1.0;
    }

    for (int i = 0; i < z.length-1; i++){
      int head = i+1;
      int tail = z.length-1;
      double [] subZ = subVector(z,head,tail);
      double [] subW = subVector(w,head,tail);
      double aux = (new Transformations()).r_sum(subZ,subW);
      result[i] = (new Transformations()).b_param(z[i],aux,(double)0.98/(double)49.98,(double)0.02,(double)50);
    }

    result[z.length-1] = z[z.length-1];
    return result;
  } // t1

  /**
   * WFG9 t2 transformation
   */
  public double [] t2(double [] z, int k){
    double [] result = new double[z.length];

    for (int i = 0; i < k; i++) {
      result[i] = (new Transformations()).s_decept(z[i],(double)0.35,(double)0.001,(double)0.05);
    }

    for (int i = k; i < z.length; i++) {
      result[i] = (new Transformations()).s_multi(z[i],30,95,(double)0.35);
    }

    return result;
  } // t2

  /**
   * WFG9 t3 transformation
   */
  public double [] t3(double [] z, int k, int M){
    double [] result = new double[M];

    for (int i = 1; i <= M-1; i++){
      int head = (i - 1)*k/(M-1) + 1;
      int tail = i * k / (M - 1);
      double [] subZ = subVector(z,head-1,tail-1);
      result[i-1] = (new Transformations()).r_nonsep(subZ,k/(M-1));
    }

    int head = k + 1;
    int tail = z.length;
    int l = z.length - k;
    double [] subZ = subVector(z,head-1,tail-1);
    result[M-1] = (new Transformations()).r_nonsep(subZ,l);

    return result;
  } // t3

  /**
  * Evaluates a solution
  * @param solution The solution to evaluate
   * @throws JMException
  */
  public final void evaluate(Solution solution) throws JMException {
	    double [] variables = new double[getNumberOfVariables()];

	    for (int i = 0; i < getNumberOfVariables(); i++) {
	      variables[i] = (double)solution.getValue(i);
	    }

	    double [] sol = evaluate(variables);

	    for (int i = 0; i < sol.length; i++) {
	      solution.setObjective(i,sol[i]);
	    }
	  } // evaluate

  @Override
  public void repair(Solution d, Map<String, Object> a) {
  }


} // WFG9


