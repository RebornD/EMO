package util.Comparator.NSGAIIComparator;

import java.util.HashMap;

import core.Solution;
import util.JMException;
import util.Random;

public class NSGAIIComparatorBinary extends NSGAIIComparator{

	public NSGAIIComparatorBinary(HashMap<String, Object> parameters) {
		super(parameters);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public int execute(Object one, Object two) throws JMException {
		Solution a = (Solution)one;
		Solution b = (Solution)two;


		if(a.getRank() < b.getRank()){
			return 1;
		} else if (a.getRank() > b.getRank()){
			return -1;
		}

		if(a.getCrowdDistance_() > b.getCrowdDistance_()){
			return 1;
		}  else if(a.getCrowdDistance_() < b.getCrowdDistance_()) {
			return -1;
		}

		if(Random.nextDoubleIE() > 0.5){
			return 1;
		}  else  {
			return -1;
		}



	}
}
