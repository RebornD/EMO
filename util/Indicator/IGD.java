package util.Indicator;

import java.util.HashMap;

import core.Population;
import util.Distance;
import util.JMException;



/*
 * this inplement is unconfirmed , So before use this inmpement , We must confirm this Implement
 *
 *
 */
public class IGD  extends Indicator{


	public double IGD(double[][]  objective , double[][] referencePoint){

		int numberOfReferencePoint = referencePoint.length;
		double sum = 0;
		double min = 1.0E30;

		for (int i=0;i<referencePoint.length;i++){
			min = Distance.calc(objective[0], referencePoint[i]);

			for (int j=0;j<objective.length;j++){
				if (min > Distance.calc(objective[j], referencePoint[i])){
					min = Distance.calc(objective[j], referencePoint[i]);
				}
			}
			sum += min;
		}

		return sum/numberOfReferencePoint;
	}

	
	
	
	public double dist(double[] r, double[] s){
		return Distance.calc(r, s);
	}

	@Override
	public Object execute(Population ind, HashMap<String, Object> d) throws JMException {
		assert d.containsKey("referencePoint") : "execute in IGD: there are no referecenPoint in the HashMap";
		assert false :"未検証につき使用注意";
		System.out.println("this inplement is unconfirmed , So before use this inmpement , We must confirm this Implement");

		double[][] referencePoint = (double[][])d.get("referencePoint");

		double[][] Objectives = ind.getAllObjectives();

		return IGD(Objectives,referencePoint);
	}

	@Override
	public String getIndicatorName() {
		return "IGD";
	}

}
