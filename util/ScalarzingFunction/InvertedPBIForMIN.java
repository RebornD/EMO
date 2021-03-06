package util.ScalarzingFunction;

import core.Solution;
import util.JMException;

public class InvertedPBIForMIN extends ScalarzingFunction {

	double theta;

	public InvertedPBIForMIN(double a){
		theta = 5;
	}


	public double execute(Solution ind ,double[] weigh,double[] referencePoint) throws JMException{
		double dt = 0,dn;
		double scalar_weight=0;
		double d1_reg=0;
		double config = 0;
		double [] weight = new double[weigh.length];
		double [] ref_minus_fit = new double[weigh.length];


		for(int i=0;i<weight.length;i++){
			scalar_weight += weight[i]*weight[i];
			ref_minus_fit[i] = referencePoint[i]  - ind.getObjective(i);
			dt += ref_minus_fit[i] * weight[i];
		}
		scalar_weight = Math.sqrt(scalar_weight);
		dt = dt/scalar_weight;

		dn=0;
		for(int i=0;i<weight.length;i++){
			dn += (ref_minus_fit[i]  - dt*weight[i]/scalar_weight)*(ref_minus_fit[i]  - dt*weight[i]/scalar_weight);
		}
			dt = Math.abs(dt);
			dn = Math.sqrt(dn);

		double sum = dt +  theta*dn ;
		return (sum);
	}

	public String getFunctionName() {
		return "InvertedPBI(5)_ForMin";
	}

}
