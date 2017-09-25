package mo.util.Comparator.MOEADComparator;


import java.util.HashMap;

import mo.core.Solution;
import mo.util.JMException;
import mo.util.ScalarzingFunction.ScalarzingFunction;
public class NormalizeMOEAComparator extends MOEADComparator {

	public NormalizeMOEAComparator(HashMap<String, Object> parameters) {
		super(parameters);
		// TODO 自動生成されたコンストラクター・スタブ
	}
	public NormalizeMOEAComparator(HashMap<String, Object> parameters,ScalarzingFunction set) {
		super(parameters,set);
	}

	double epsilon = 0;

	public void setEpsilon(double d){
		epsilon = d;
	}


	@Override
	public int execute(Object one, Object two) throws JMException {
		Solution solone = (Solution)one;
		Solution soltwo = (Solution)two;
		double[] soloneDouble = new double[solone.getNumberOfObjectives()];
		double[] soltwoDouble = new double[solone.getNumberOfObjectives()];
		double[] refs = new double[solone.getNumberOfObjectives()];


		for(int obj =0; obj < solone.getNumberOfObjectives();obj++){
			soloneDouble[obj] = (solone.getObjective(obj) - Min[obj])/(Max[obj] - Min[obj] + epsilon);
			soltwoDouble[obj] = (soltwo.getObjective(obj)- Min[obj])/(Max[obj] - Min[obj] + epsilon);
			refs[obj] =(referencePoint.get(obj)- Min[obj])/(Max[obj] - Min[obj] + epsilon);
		}
		double scalar_one = 0;
		double scalar_two = 0;

		scalar_one = ScalaringFunction_.execute(soloneDouble, weightedVector.get(), refs);
//		scalar_one = ScalaringFunction_.execute(solone, weightedVector.get(), referencePoint.get());

		scalar_two = ScalaringFunction_.execute(soltwoDouble, weightedVector.get(), refs);
//		scalar_two = ScalaringFunction_.execute(soltwo, weightedVector.get(), referencePoint.get());


		assert scalar_one > 0;
		assert scalar_two > 0;
		if (isMAX_== (scalar_one > scalar_two)){
			return 1;
		} else if  (isMAX_== (scalar_one < scalar_two)){
			return -1;
		}

		return 0;
	}


}
