package util.Comparator.MultiIslandMOEADComparator;

import java.util.HashMap;

import core.Solution;
import util.JMException;
import util.ScalarzingFunction.ScalarzingFunction;

public class  ConstrainMultiMOEADComparator extends  MultiIslandMOEADComparator{

	public ConstrainMultiMOEADComparator(HashMap<String, Object> parameters) {
		super(parameters);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public ConstrainMultiMOEADComparator(HashMap<String, Object> parameters,ScalarzingFunction[] d ){
		super(parameters,d);
	}



	@Override
	public int execute(Object one, Object two) throws JMException {
		Solution one_sol = (Solution)one;
		Solution two_sol = (Solution)two;
		assert one_sol != null : "one Solution is null ";
	    assert two_sol != null  : "two Solution is null ";
		assert one_sol.getnumberOfConstrain() == two_sol.getnumberOfConstrain() : "one Sslution has " + one_sol.getnumberOfConstrain() + " and the other has "  + two_sol.getnumberOfConstrain();
		assert weightedVector != null  : "WeightedVector ";
		assert  referencePoint != null : "ReferencePoint ";



		if  (one_sol.getFeasible() && !two_sol.getFeasible()) return 1;
		if  (!one_sol.getFeasible() && two_sol.getFeasible()) return -1;
		if  (!one_sol.getFeasible() && !two_sol.getFeasible()) {
			if (one_sol.getViolation() < two_sol.getViolation()){
				return 1;
			} else if (one_sol.getViolation() > two_sol.getViolation()){
				return -1;
			}else {
				return 0;
			}
		}

		double scalar_one = NowScalaringFunction_.execute(one_sol, weightedVector.get(), referencePoint.get());
		double scalar_two = NowScalaringFunction_.execute(two_sol, weightedVector.get(), referencePoint.get());


		if(isMAX_== (scalar_one > scalar_two) ){
			return 1;
		} else if(isMAX_== (scalar_one < scalar_two) ) {
			return -1;
		}
		return 0;
	}

}
