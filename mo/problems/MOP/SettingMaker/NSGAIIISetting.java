package mo.problems.MOP.SettingMaker;

import java.util.ArrayList;
import java.util.List;

public class NSGAIIISetting {
	public static List<Integer> getSetting(int numberOfObj,String Problemname){
		List<Integer> ret = new ArrayList<Integer>();
		int k=-1,l=-1,M=-1,numberOfVariables_=-1;


		l = 20;
		if (numberOfObj == 2){
			k = 4;
		} else {
			k =2*(numberOfObj - 1);
		}

		if (Problemname.equalsIgnoreCase( "DTLZ1")){
			numberOfVariables_	 = 	numberOfObj + 4;
		} else if (Problemname.equalsIgnoreCase( "DTLZ2")){
			numberOfVariables_	 = 	numberOfObj + 9;
		} else if (Problemname.equalsIgnoreCase( "DTLZ3")){
			numberOfVariables_	 = 	numberOfObj + 9;
		} else if (Problemname.equalsIgnoreCase( "DTLZ4")){
			numberOfVariables_	 = 	numberOfObj + 9;
		} else if (Problemname.equalsIgnoreCase( "ConvexDTLZ2")){
			numberOfVariables_	 = 	numberOfObj + 9;
		} else if (Problemname.equalsIgnoreCase( "ScaledDTLZ1")){
			numberOfVariables_	 = 	numberOfObj + 4;
		} else if (Problemname.equalsIgnoreCase( "ScaledDTLZ2")){
			numberOfVariables_	 = 	numberOfObj + 9;
		} else if (Problemname.equalsIgnoreCase( "WFG1")){
			numberOfVariables_	 = 	24;
			k = k;
			l = 20;
			M = numberOfObj;
		} else if (Problemname.equalsIgnoreCase( "WFG2")){
			k = k;
			l = 20;
			M = numberOfObj;
		} else if (Problemname.equalsIgnoreCase( "WFG3")){
			k = k;
			l = 20;
			M = numberOfObj;
		} else if (Problemname.equalsIgnoreCase( "WFG4")){
			k = k;
			l = 20;
			M = numberOfObj;
		} else if (Problemname.equalsIgnoreCase( "WFG5")){
			k = k;
			l = 20;
			M = numberOfObj;
		} else if (Problemname.equalsIgnoreCase( "WFG6")){
			k = k;
			l = 20;
			M = numberOfObj;
		} else if (Problemname.equalsIgnoreCase( "WFG7")){
			k = k;
			l = 20;
			M = numberOfObj;
		} else if (Problemname.equalsIgnoreCase( "WFG8")){
			k = k;
			l = 20;
			M = numberOfObj;
		}  else if (Problemname.equalsIgnoreCase( "WFG9")){
			k = k;
			l = 20;
			M = numberOfObj;
		}




		ret.clear();
		ret.add(numberOfObj);
		ret.add(numberOfVariables_);
		ret.add(k);
		ret.add(l);
		ret.add(M);



		return ret;
	}


}
