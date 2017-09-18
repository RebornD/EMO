package util.Comparator;

import util.JMException;

public class NormalComparator extends Comparator{

	public NormalComparator(boolean is) {
		super(is);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public int execute(Object one, Object two) throws JMException {
		assert (one instanceof Integer): "Object one is not Double";
		assert (one instanceof Integer): "Object two is not Double";

		int int_one = (Integer)one;
		int int_two = (Integer)two;

		if( int_one > int_two ){
			return 1;
		} else if (int_one < int_two){
			return -1;
		}

		return 0;
	}

}
