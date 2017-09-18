package problems.MOP.SettingMaker;

import java.util.ArrayList;
import java.util.List;

public class SMSEMOASetting {
	public static List<Integer> getSetting(int numberOfObj,String Problemname){
		List<Integer> ret = new ArrayList<Integer>();
		int k=-1,l=-1,M=-1,numberOfVariables_=-1;

		numberOfVariables_ = 5 + numberOfObj -1;

		ret.clear();
		ret.add(numberOfObj);
		ret.add(numberOfVariables_);
		ret.add(k);
		ret.add(l);
		ret.add(M);

		assert Problemname.equalsIgnoreCase("DTLZ1") :"設定まだしていない";


		return ret;
	}
}
