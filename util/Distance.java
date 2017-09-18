package util;


public class Distance {

	public static double calc(double[]  a, double[] b) {
		assert (a.length == b.length ): "calc in Distance Class :: two array have diffrecent length";

		double sum = 0;
		for (int i=0;i<a.length;i++){
			sum += (a[i]- b[i])*(a[i]- b[i]);
		}
		return Math.sqrt(sum);
	}

	public static double calc(POINT a, POINT b){
		assert a.getDimension() == b.getDimension() : "calc in Distance class :: two POINT have difference size";
		double sum  = 0.0;

		for (int i=0;i<a.getDimension();i++){
			sum += (a.get(i) - b.get(i))*(a.get(i) - b.get(i));
		}
		return Math.sqrt(sum);
	}

}
