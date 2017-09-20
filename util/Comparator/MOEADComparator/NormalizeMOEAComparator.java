package util.Comparator.MOEADComparator;


import java.util.HashMap;

import core.Solution;
import util.JMException;
import util.ScalarzingFunction.ScalarzingFunction;
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

		double[] solone = ((Solution)one).getObjectives().clone();;
		double[] soltwo = ((Solution)two).getObjectives().clone();;


		for(int obj =0; obj < solone.length;obj++){
			solone[obj] = (solone[obj] - Min[obj])/(Max[obj] - Min[obj] + epsilon);
			soltwo[obj] = (solone[obj] - Min[obj])/(Max[obj] - Min[obj] + epsilon);
		}

		double scalar_one = ScalaringFunction_.execute(solone, weightedVector.get(), referencePoint.get());
		double scalar_two = ScalaringFunction_.execute(soltwo, weightedVector.get(), referencePoint.get());

		
		if (isMAX_== (scalar_one > scalar_two)){
			return 1;
		} else if  (isMAX_== (scalar_one < scalar_two)){
			return -1;
		}

		return 0;
	}


}
