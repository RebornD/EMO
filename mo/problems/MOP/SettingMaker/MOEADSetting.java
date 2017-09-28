package mo.problems.MOP.SettingMaker;

import java.util.ArrayList;
import java.util.List;

// these settings are for the black box test

public class MOEADSetting {

	public static List<Integer> getSetting(int numberOfObj,String name){
		List<Integer> ret = new ArrayList<Integer>();
		int k=-1,l=-1,M=-1,numberOfVariables_=-1;



		if (name.equalsIgnoreCase( "DTLZ1")){
			numberOfVariables_	 = 	numberOfObj + 4;
		} else if (name.equalsIgnoreCase( "DTLZ2")){
			numberOfVariables_	 = 	numberOfObj + 9;
		} else if (name.equalsIgnoreCase( "DTLZ3")){
			numberOfVariables_	 = 	numberOfObj + 9;
		} else if (name.equalsIgnoreCase( "DTLZ4")){
			numberOfVariables_	 = 	numberOfObj + 9;
		} else if (name.equalsIgnoreCase( "DTLZ4")){
			numberOfVariables_	 = 	numberOfObj + 9;
		} else if (name.equalsIgnoreCase( "DTLZ7")){
			numberOfVariables_	 = 	numberOfObj + 19;
		}else if (name.equalsIgnoreCase("NormDTLZ1")){
			numberOfVariables_	 = 	numberOfObj -1;
		}	else if (name.equalsIgnoreCase( "WFG1")){
			numberOfVariables_	 = 	24;

			k = numberOfObj -1;
			l = numberOfVariables_ - numberOfObj + 1 ;
			M = numberOfObj;

		} else if (name.equalsIgnoreCase( "WFG2")){
			if (numberOfObj <7){
			numberOfVariables_	 = 	24;
			k = numberOfObj -1;
			l = numberOfVariables_ - numberOfObj +1;
			M = numberOfObj;
			} else {
				numberOfVariables_	 = 	23;
				k = numberOfObj -1;
				l=numberOfVariables_ - numberOfObj +1;
				M = numberOfObj;
			}
		} else if (name.equalsIgnoreCase( "WFG3")){
			if (numberOfObj <7){
				numberOfVariables_	 = 	24;
				k = numberOfObj -1;
				l = numberOfVariables_ - numberOfObj +1;
				M= numberOfObj;
				} else {
					numberOfVariables_	 = 	23;
					k= numberOfObj -1;
					l = numberOfVariables_ - numberOfObj +1;
					M = numberOfObj;
				}
		} else if (name.equalsIgnoreCase( "WFG4")){
			numberOfVariables_	 = 	24;
			k = numberOfObj -1;
			l = numberOfVariables_ - numberOfObj +1;
			M = numberOfObj;
		} else if (name.equalsIgnoreCase( "WFG5")){
			numberOfVariables_	 = 	24;
			k = numberOfObj -1;
			l = numberOfVariables_ - numberOfObj +1;
			M = numberOfObj;
		} else if (name.equalsIgnoreCase( "WFG6")){
			numberOfVariables_	 = 	24;
			k = numberOfObj -1;
			l = numberOfVariables_ - numberOfObj +1;
			M = numberOfObj;
		} else if (name.equalsIgnoreCase( "WFG7")){
			numberOfVariables_	 = 	24;
			k = numberOfObj -1;
			l = numberOfVariables_ - numberOfObj +1;
			M = numberOfObj;
		} else if (name.equalsIgnoreCase( "WFG8")){
			numberOfVariables_	 = 	24;
			k = numberOfObj -1;
			l = numberOfVariables_ - numberOfObj +1;
			M = numberOfObj;
		}  else if (name.equalsIgnoreCase( "WFG9")){
			numberOfVariables_	 = 	24;
			k = numberOfObj -1;
			l = numberOfVariables_ - numberOfObj +1;
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
