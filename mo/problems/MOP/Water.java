package mo.problems.MOP;

import java.util.Map;

import mo.core.Problem;
import mo.core.Solution;
import mo.util.JMException;

public class Water extends Problem{



	 public Water() {
		numberOfVariables_  =3;
	    numberOfObjectives_ = 5;
	    numberOfConstraint_ = 5;

	    problemName_        = "Water";


	    lowerLimit_ = new double[numberOfVariables_];
	    upperLimit_ = new double[numberOfVariables_];


	    setLimits();

	    variableType_  = 2 ;
	 }

	private void setLimits() {
	    lowerLimit_[0] = 0.01;
	    lowerLimit_[1] = 0.01;
	    lowerLimit_[2] = 0.01;

	    upperLimit_[0] = 0.45;
	    upperLimit_[0] = 0.10;
	    upperLimit_[0] = 0.10;

	}

	@Override
	public void evaluate(Solution solution) throws JMException {
		double[] fx = new double[2];
		double[] constraint = new double[solution.getNumberOfConstraint()];
		double[] x = new double[solution.getNumberOfVariables()];
	    for (int i = 0; i < solution.getNumberOfVariables(); i++) {
	    	x[i] = solution.getValue(i) ;
	    }

	    fx[0] = 106780.37 * (x[1] + x[2]) + 61704.67 ;
	    fx[1] = 3000 * x[0] ;
	    fx[2] = 305700 * 2289 * x[1] / Math.pow(0.06*2289, 0.65) ;
	    fx[3] = 250 * 2289 * Math.exp(-39.75*x[1]+9.9*x[2]+2.74) ;
	    fx[4] = 25 * (1.39 /(x[0]*x[1]) + 4940*x[2] -80) ;

	    solution.setObjective(0,fx[0]);
	    solution.setObjective(1,fx[1]);
	    solution.setObjective(2,fx[2]);
	    solution.setObjective(3,fx[3]);
	    solution.setObjective(4,fx[4]);

	    constraint[0] = 1 - (0.00139/(x[0]*x[1])+4.94*x[2]-0.08);
	    constraint[1] = 1 - (0.000306/(x[0]*x[1])+1.082*x[2]-0.0986);
	    constraint[2] = 50000 - (12.307/(x[0]*x[1]) + 49408.24*x[2]+4051.02);
	    constraint[3] = 16000 - (2.098/(x[0]*x[1])+8046.33*x[2]-696.71);
	    constraint[4] = 10000 - (2.138/(x[0]*x[1])+7883.39*x[2]-705.04);
	    constraint[5] = 2000 - (0.417*x[0]*x[1] + 1721.26*x[2]-136.54);
	    constraint[6] = 550 - (0.164/(x[0]*x[1])+631.13*x[2]-54.48);

	    for(int c = 0; c<solution.getNumberOfConstraint();c++){
	    	solution.setConstrain(constraint);
	    }

	}

	@Override
	public void repair(Solution d, Map<String, Object> a) throws JMException {
		// TODO 自動生成されたメソッド・スタブ

	}

}
