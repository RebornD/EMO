package mo.util.Comparator.MOEADComparator;

import java.util.HashMap;

import mo.core.Solution;
import mo.util.JMException;
import mo.util.ScalarzingFunction.ScalarzingFunction;

public class  ConstrainSumMOEADComparator extends MOEADComparator{


	double parameter = 10;

	public ConstrainSumMOEADComparator(HashMap<String, Object> parameters) {
		super(parameters);

		// TODO 自動生成されたコンストラクター・スタブ
	}

	public ConstrainSumMOEADComparator(HashMap<String, Object> parameters,ScalarzingFunction d ){
		super(parameters,d);

		if(parameters != null)
			if(parameters.containsKey("parameter")){
				parameter = (double)parameters.get("parameter");
			}
	}
	public ConstrainSumMOEADComparator(HashMap<String, Object> parameters,ScalarzingFunction d , double para){
		super(parameters,d);
		parameter = para;
	}


	@Override
	public int execute(Object one, Object two) throws JMException {
		Solution one_sol = (Solution)one;
		Solution two_sol = (Solution)two;
		assert one_sol.getnumberOfConstrain() == two_sol.getnumberOfConstrain() : "one Sslution has " + one_sol.getnumberOfConstrain() + " and the other has "  + two_sol.getnumberOfConstrain();
		assert weightedVector != null  : "WeightedVector";
		assert  referencePoint != null : "ReferencePoint";
		double scalar_one = ScalaringFunction_.execute(one_sol, weightedVector.get(), referencePoint.get());
		double scalar_two = ScalaringFunction_.execute(two_sol, weightedVector.get(), referencePoint.get());
		double parameter_one = one_sol.getViolation();
		double parameter_two = two_sol.getViolation();


		if ((isMAX_) == (scalar_one  - parameter_one * parameter > scalar_two - parameter_two * parameter)){
			return 1;
		} else if ((isMAX_) == (scalar_one  - parameter_one * parameter < scalar_two - parameter_two * parameter)){
			return -1;
		}
		return 0;
	}

}
