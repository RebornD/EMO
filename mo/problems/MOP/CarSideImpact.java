package mo.problems.MOP;

import java.util.Map;

import mo.core.Problem;
import mo.core.Solution;
import mo.util.JMException;

public class CarSideImpact extends Problem{

	 public CarSideImpact() {
			numberOfVariables_  =7;
		    numberOfObjectives_ = 3;
		    numberOfConstraint_ = 10;
		    problemName_        = "CarSideImpact";
		    setLimits();
		    variableType_  = 2 ;
	 }

	 private  void setLimits(){
		   lowerLimit_ = new double[numberOfVariables_];
		   upperLimit_ = new double[numberOfVariables_];

		   lowerLimit_[0] = 0.5;
		   lowerLimit_[1] = 0.45;
		   lowerLimit_[2] = 0.5;
		   lowerLimit_[3] = 0.5;
		   lowerLimit_[4] = 0.875;
		   lowerLimit_[5] = 0.4;
		   lowerLimit_[6] = 0.4;

		   upperLimit_[0] = 1.5;
		   upperLimit_[1] = 1.35;
		   upperLimit_[2] = 1.5;
		   upperLimit_[3] = 1.5;
		   upperLimit_[4] = 2.625;
		   upperLimit_[5] = 1.2;
		   upperLimit_[6] = 1.2;
	 }


	@Override
	public void evaluate(Solution sol) throws JMException {
		double[] x = new double[sol.getNumberOfVariables()];
		double[] constrain = new double[sol.getNumberOfConstraint()];
		double[] f = new double[sol.getNumberOfObjectives()];


		for(int i = 0;i<sol.getNumberOfVariables();i++){
			x[i] = sol.getValue(i);
		}

		constrain[0] = 1.16-0.3717*x[1]*x[3]-0.0092928*x[2];
		constrain[1] = 0.261 - 0.0159*x[0]*x[1] - 0.06486*x[1]*x[6]+0.0144*x[2]*x[4] + 0.0154464;
		constrain[2] = 0.214 + 0.00817*x[4] - 0.045195*x[0] - 0.0135168*x[0]+ 0.03099*x[1]*x[5] - 0.018*x[1]*x[6]+0.007176*x[2]
				+0.023232*x[2] - 0.00364*x[4]*x[5] - 0.018*x[1]*x[1];

		constrain[3] = 0.74 - 0.61*x[1]-0.031296*x[2] - 0.031872*x[6] + 0.227*x[1]*x[1];
		constrain[4] = 28.98 + 3.818*x[2]-4.2*x[0]*x[1] + 1.27296*x[5]- 2.68065*x[6];
		constrain[5] = 33.86 + 2.95*x[2] - 5.057*x[1]*x[2]-3.795*x[1]-3.4431*x[6]+1.45728;
		constrain[6] = 46.36 - 9.9*x[1] - 4.4505*x[0];
		constrain[7] = 4.72 - 0.5*x[3]-0.19*x[1]*x[2];
		constrain[8] = 10.58 - 0.674*x[0]*x[1]-0.67275*x[1];
		constrain[9] = 16.45 - 0.489*x[2]*x[6]-0.843*x[4]*x[5];

		f[0] = 1.98 + 4.9*x[0] + 6.67*x[1] + 6.98*x[2] + 4.01*x[3] + 1.78*x[4] + 0.00001*x[5]+2.73*x[6];
		f[1] = constrain[7];
		f[2] = 0.5*(constrain[8] + constrain[9]);

		for( int i = 0 ;i < sol.getNumberOfObjectives();i++){
//			sol.setValue(i, f[i]);
		}

		for( int i = 0 ;i < sol.getNumberOfConstraint();i++){
	//		sol.setValue(i, f[i]);
		}



	}



	@Override
	public void repair(Solution d, Map<String, Object> a) throws JMException {
		// TODO 自動生成されたメソッド・スタブ

	}

}
