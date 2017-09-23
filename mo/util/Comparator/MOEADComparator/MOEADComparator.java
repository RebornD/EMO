package mo.util.Comparator.MOEADComparator;

import java.util.HashMap;

import mo.util.ReferencePoint;
import mo.util.WeightedVector;
import mo.util.Comparator.Comparator;
import mo.util.ScalarzingFunction.ScalarzingFunction;

public abstract class MOEADComparator extends Comparator {

	public MOEADComparator(HashMap<String, Object> parameters) {
		super(parameters);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public MOEADComparator(HashMap<String, Object> parameters,ScalarzingFunction d ) {
		super(parameters);
		ScalaringFunction_ = d;
	}



	protected ScalarzingFunction ScalaringFunction_;

	protected ReferencePoint referencePoint;

	protected WeightedVector weightedVector;

	protected double[] Max;
	protected double[] Min;

	public void setMaxPoint(double[] d){Max = d;};
	public void setMinPoint(double[] d){Min = d;};

	public ScalarzingFunction getScalarzingFunction(){return ScalaringFunction_;};

	public void setRefernecePoint(double[] d){
		setRefernecePoint(new ReferencePoint(d));
	}

	public void setRefernecePoint(ReferencePoint d){
		referencePoint = d;
	}

	public void setWeightedVector(double[] d){
		setWeightedVector(new WeightedVector(d));
	}

	public void setWeightedVector(WeightedVector d){
		weightedVector = d;
	}





}
