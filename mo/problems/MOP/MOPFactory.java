package mo.problems.MOP;
import java.util.HashMap;
import java.util.List;

import javax.naming.NameNotFoundException;

import experiments.Setting;
import experiments.SettingWriter;
import mo.core.Problem;
import mo.problems.MOP.CDTLZ.C1_DTLZ1;
import mo.problems.MOP.CDTLZ.C1_DTLZ3;
import mo.problems.MOP.CDTLZ.C2_DTLZ2;
import mo.problems.MOP.CDTLZ.C3_DTLZ1;
import mo.problems.MOP.CDTLZ.C3_DTLZ4;
import mo.problems.MOP.CDTLZ.CF_DTLZ3;
import mo.problems.MOP.CDTLZ.ConvexC2_DTLZ2;
import mo.problems.MOP.CDTLZ.ConvexDTLZ2;
import mo.problems.MOP.DTLZ.DTLZ1;
import mo.problems.MOP.DTLZ.DTLZ2;
import mo.problems.MOP.DTLZ.DTLZ3;
import mo.problems.MOP.DTLZ.DTLZ4;
import mo.problems.MOP.DTLZ.DTLZ5;
import mo.problems.MOP.DTLZ.DTLZ6;
import mo.problems.MOP.DTLZ.DTLZ7;
import mo.problems.MOP.InvertedDTLZ.InvertedDTLZ1;
import mo.problems.MOP.InvertedDTLZ.InvertedDTLZ2;
import mo.problems.MOP.InvertedDTLZ.InvertedDTLZ3;
import mo.problems.MOP.InvertedDTLZ.InvertedDTLZ4;
import mo.problems.MOP.InvertedDTLZ.InvertedDTLZ5;
import mo.problems.MOP.InvertedDTLZ.InvertedDTLZ6;
import mo.problems.MOP.InvertedDTLZ.InvertedDTLZ7;
import mo.problems.MOP.InvertedWFG.InvertedWFG1;
import mo.problems.MOP.InvertedWFG.InvertedWFG2;
import mo.problems.MOP.InvertedWFG.InvertedWFG3;
import mo.problems.MOP.InvertedWFG.InvertedWFG4;
import mo.problems.MOP.InvertedWFG.InvertedWFG5;
import mo.problems.MOP.InvertedWFG.InvertedWFG6;
import mo.problems.MOP.InvertedWFG.InvertedWFG7;
import mo.problems.MOP.InvertedWFG.InvertedWFG8;
import mo.problems.MOP.InvertedWFG.InvertedWFG9;
import mo.problems.MOP.ScaledDTLZ.ScaledDTLZ1;
import mo.problems.MOP.ScaledDTLZ.ScaledDTLZ2;
import mo.problems.MOP.SettingMaker.Settingpublisher;
import mo.problems.MOP.WFG.WFG1;
import mo.problems.MOP.WFG.WFG2;
import mo.problems.MOP.WFG.WFG3;
import mo.problems.MOP.WFG.WFG4;
import mo.problems.MOP.WFG.WFG5;
import mo.problems.MOP.WFG.WFG6;
import mo.problems.MOP.WFG.WFG7;
import mo.problems.MOP.WFG.WFG8;
import mo.problems.MOP.WFG.WFG9;
import mo.util.Configuration;
import mo.util.JMException;

public class MOPFactory {
	public static Problem getMOP(String name,HashMap<String, Object> parameters,String Algorithmname) throws JMException, ClassNotFoundException {
		int k=-1,l=-1,M=-1,numberOfVariables_=-1,numberOfObj=-1;
		String algo = Algorithmname;

		if (parameters.containsKey("numberOfObjectives")){
			numberOfObj =(Integer) parameters.get("numberOfObjectives");
		}

		List<Integer> ret = Settingpublisher.getprograming(algo,name , numberOfObj);
		SettingWriter.add("ProblemSttingname", algo);
		numberOfObj = ret.get(0);
		numberOfVariables_ = ret.get(1);
		k = ret.get(2);
		l = ret.get(3);
		M = ret.get(4);
		SettingWriter.add("numberOfObj", numberOfObj);
		SettingWriter.add("numberOfVariables", algo);
		SettingWriter.add("k", k);
		SettingWriter.add("l", l);
		SettingWriter.add("M", M);

		if (name.equalsIgnoreCase("DTLZ1")){
			return new DTLZ1(numberOfVariables_,numberOfObj);
		} else if (name.equalsIgnoreCase("DTLZ2")){
			return new DTLZ2(numberOfVariables_,numberOfObj);
		}	else if (name.equalsIgnoreCase("DTLZ3")){
			return new DTLZ3(numberOfVariables_,numberOfObj);
		}	else if (name.equalsIgnoreCase("DTLZ4")){
			return new DTLZ4(numberOfVariables_,numberOfObj);
		}else if (name.equalsIgnoreCase("DTLZ5")){
			return new DTLZ5(numberOfVariables_,numberOfObj);
		}else if (name.equalsIgnoreCase("DTLZ6")){
			return new DTLZ6(numberOfVariables_,numberOfObj);
		}else if (name.equalsIgnoreCase("DTLZ7")){
			return new DTLZ7(numberOfVariables_,numberOfObj);
		} else if (name.equalsIgnoreCase("ScaledDTLZ1")){
			return new ScaledDTLZ1(numberOfVariables_,numberOfObj);
		} else if (name.equalsIgnoreCase("ScaledDTLZ2")){
			return new ScaledDTLZ2(numberOfVariables_,numberOfObj);
		}else if (name.equalsIgnoreCase("InvertedDTLZ1")){
			return new InvertedDTLZ1(numberOfVariables_,numberOfObj);
		} else if (name.equalsIgnoreCase("InvertedDTLZ2")){
			return new InvertedDTLZ2(numberOfVariables_,numberOfObj);
		}	else if (name.equalsIgnoreCase("InvertedDTLZ3")){
			return new InvertedDTLZ3(numberOfVariables_,numberOfObj);
		}	else if (name.equalsIgnoreCase("InvertedDTLZ4")){
			return new InvertedDTLZ4(numberOfVariables_,numberOfObj);
		}else if (name.equalsIgnoreCase("InvertedDTLZ5")){
			return new InvertedDTLZ5(numberOfVariables_,numberOfObj);
		}else if (name.equalsIgnoreCase("InvertedDTLZ6")){
			return new InvertedDTLZ6(numberOfVariables_,numberOfObj);
		}else if (name.equalsIgnoreCase("InvertedDTLZ7")){
			return new InvertedDTLZ7(numberOfVariables_,numberOfObj);
		}else if (name.equalsIgnoreCase("ConvexDTLZ2")){
			return new ConvexDTLZ2(numberOfVariables_,numberOfObj);
		}  else if (name.equalsIgnoreCase("C1_DTLZ1")){
			return new C1_DTLZ1(numberOfVariables_,numberOfObj);
		} else if (name.equalsIgnoreCase("C1_DTLZ3")){
			return new C1_DTLZ3(numberOfVariables_,numberOfObj);
		}	else if (name.equalsIgnoreCase("CF_DTLZ3")){
			return new CF_DTLZ3(numberOfVariables_,numberOfObj);
		}else if (name.equalsIgnoreCase("C2_DTLZ2")){
			return new C2_DTLZ2(numberOfVariables_,numberOfObj);
		}	else if (name.equalsIgnoreCase("C3_DTLZ1")){
			return new C3_DTLZ1(numberOfVariables_,numberOfObj);
		}else if (name.equalsIgnoreCase("C3_DTLZ4")){
			return new C3_DTLZ4(numberOfVariables_,numberOfObj);
		}else if (name.equalsIgnoreCase("ConvexC2_DTLZ2")){
			return new ConvexC2_DTLZ2(numberOfVariables_,numberOfObj);
		}else if (name.equalsIgnoreCase("ConvexDTLZ2")){
			return new ConvexDTLZ2(numberOfVariables_,numberOfObj);
		}   else 	if (name.equalsIgnoreCase("InvertedWFG1")){
			return new InvertedWFG1(k,l,M);
		} else if (name.equalsIgnoreCase("InvertedWFG2")){
			return new InvertedWFG2(k,l,M);
		}	else if (name.equalsIgnoreCase("InvertedWFG3")){
			return new InvertedWFG3(k,l,M);
		}	else if (name.equalsIgnoreCase("InvertedWFG4")){
			return new InvertedWFG4(k,l,M);
		}else if (name.equalsIgnoreCase("InvertedWFG5")){
			return new InvertedWFG5(k,l,M);
		}else if (name.equalsIgnoreCase("InvertedWFG6")){
			return new InvertedWFG6(k,l,M);
		}else if (name.equalsIgnoreCase("InvertedWFG7")){
			return new InvertedWFG7(k,l,M);
		}else if (name.equalsIgnoreCase("InvertedWFG8")){
			return new InvertedWFG8(k,l,M);
		}else if (name.equalsIgnoreCase("InvertedWFG9")){
			return new InvertedWFG9(k,l,M);
		} else 	if (name.equalsIgnoreCase("WFG1")){
			return new WFG1(k,l,M);
		} else if (name.equalsIgnoreCase("WFG2")){
			return new WFG2(k,l,M);
		}	else if (name.equalsIgnoreCase("WFG3")){
			return new WFG3(k,l,M);
		}	else if (name.equalsIgnoreCase("WFG4")){
			return new WFG4(k,l,M);
		}else if (name.equalsIgnoreCase("WFG5")){
			return new WFG5(k,l,M);
		}else if (name.equalsIgnoreCase("WFG6")){
			return new WFG6(k,l,M);
		}else if (name.equalsIgnoreCase("WFG7")){
			return new WFG7(k,l,M);
		}else if (name.equalsIgnoreCase("WFG8")){
			return new WFG8(k,l,M);
		}else if (name.equalsIgnoreCase("WFG9")){
			return new WFG9(k,l,M);
		}else if (name.equalsIgnoreCase("Knapsack")){
			return new Knapsack(numberOfObj);
		}
/*		else if (name.equalsIgnoreCase("SinglePointCrossover"))
			return new SinglePointCrossover(parameters);
		else if (name.equalsIgnoreCase("PMXCrossover"))
			return new PMXCrossover(parameters);
		else if (name.equalsIgnoreCase("TwoPointsCrossover"))
			return new TwoPointsCrossover(parameters);
		else if (name.equalsIgnoreCase("HUXCrossover"))
			return new HUXCrossover(parameters);
		else if (name.equalsIgnoreCase("DifferentialEvolutionCrossover"))
			return new DifferentialEvolutionCrossover(parameters);
		else if (name.equalsIgnoreCase("BLXAlphaCrossover"))
			return new BLXAlphaCrossover(parameters);
		}*/ else{
			Configuration.logger_
					.severe("CrossoverFactory.getProblem. " + " Problem '" + name + "' not found ");
			throw new JMException("Exception in " + name + ".getCrossoverOperator()");
		} // else
	} // getCrossoverOperator
	public static Problem getMOP(String name,Setting parameters,String Algorithmname) throws JMException, ClassNotFoundException, NameNotFoundException {
		int k=-1,l=-1,M=-1,par_=-1,numberOfObj=-1;
		String algo = Algorithmname;

		if (parameters.containsKey("numberOfObjectives")){
			numberOfObj =parameters.getAsInt("numberOfObjectives");
		}

		List<Integer> ret = Settingpublisher.getprograming(algo,name , numberOfObj);
		parameters.add("numberOfObjectives", numberOfObj);
		parameters.add("numberOfVariables", ret.get(1));
		parameters.add("k", ret.get(2));
		parameters.add("l", ret.get(3));
		parameters.add("M", ret.get(4));

		/*SettingWriter.add("ProblemSttingname", algo);
		SettingWriter.add("numberOfObj", numberOfObj);
		SettingWriter.add("parameters.getAsInt("numberOfVariables")", algo);
		SettingWriter.add("k", k);
		SettingWriter.add("l", l);
		SettingWriter.add("M", M);
*/
		if (name.equalsIgnoreCase("DTLZ1")){
			return new DTLZ1(parameters.getAsInt("numberOfVariables"),parameters.getAsInt("numberOfObjectives"));
		} else if (name.equalsIgnoreCase("DTLZ2")){
			return new DTLZ2(parameters.getAsInt("numberOfVariables"),parameters.getAsInt("numberOfObjectives"));
		}	else if (name.equalsIgnoreCase("DTLZ3")){
			return new DTLZ3(parameters.getAsInt("numberOfVariables"),parameters.getAsInt("numberOfObjectives"));
		}	else if (name.equalsIgnoreCase("DTLZ4")){
			return new DTLZ4(parameters.getAsInt("numberOfVariables"),parameters.getAsInt("numberOfObjectives"));
		}else if (name.equalsIgnoreCase("DTLZ5")){
			return new DTLZ5(parameters.getAsInt("numberOfVariables"),parameters.getAsInt("numberOfObjectives"));
		}else if (name.equalsIgnoreCase("DTLZ6")){
			return new DTLZ6(parameters.getAsInt("numberOfVariables"),parameters.getAsInt("numberOfObjectives"));
		}else if (name.equalsIgnoreCase("DTLZ7")){
			return new DTLZ7(parameters.getAsInt("numberOfVariables"),parameters.getAsInt("numberOfObjectives"));
		} else if (name.equalsIgnoreCase("ScaledDTLZ1")){
			return new ScaledDTLZ1(parameters.getAsInt("numberOfVariables"),parameters.getAsInt("numberOfObjectives"));
		} else if (name.equalsIgnoreCase("ScaledDTLZ2")){
			return new ScaledDTLZ2(parameters.getAsInt("numberOfVariables"),parameters.getAsInt("numberOfObjectives"));
		}else if (name.equalsIgnoreCase("InvertedDTLZ1")){
			return new InvertedDTLZ1(parameters.getAsInt("numberOfVariables"),parameters.getAsInt("numberOfObjectives"));
		} else if (name.equalsIgnoreCase("InvertedDTLZ2")){
			return new InvertedDTLZ2(parameters.getAsInt("numberOfVariables"),parameters.getAsInt("numberOfObjectives"));
		}	else if (name.equalsIgnoreCase("InvertedDTLZ3")){
			return new InvertedDTLZ3(parameters.getAsInt("numberOfVariables"),parameters.getAsInt("numberOfObjectives"));
		}	else if (name.equalsIgnoreCase("InvertedDTLZ4")){
			return new InvertedDTLZ4(parameters.getAsInt("numberOfVariables"),parameters.getAsInt("numberOfObjectives"));
		}else if (name.equalsIgnoreCase("InvertedDTLZ5")){
			return new InvertedDTLZ5(parameters.getAsInt("numberOfVariables"),parameters.getAsInt("numberOfObjectives"));
		}else if (name.equalsIgnoreCase("InvertedDTLZ6")){
			return new InvertedDTLZ6(parameters.getAsInt("numberOfVariables"),parameters.getAsInt("numberOfObjectives"));
		}else if (name.equalsIgnoreCase("InvertedDTLZ7")){
			return new InvertedDTLZ7(parameters.getAsInt("numberOfVariables"),parameters.getAsInt("numberOfObjectives"));
		}else if (name.equalsIgnoreCase("ConvexDTLZ2")){
			return new ConvexDTLZ2(parameters.getAsInt("numberOfVariables"),parameters.getAsInt("numberOfObjectives"));
		}  else if (name.equalsIgnoreCase("C1_DTLZ1")){
			return new C1_DTLZ1(parameters.getAsInt("numberOfVariables"),parameters.getAsInt("numberOfObjectives"));
		} else if (name.equalsIgnoreCase("C1_DTLZ3")){
			return new C1_DTLZ3(parameters.getAsInt("numberOfVariables"),parameters.getAsInt("numberOfObjectives"));
		}	else if (name.equalsIgnoreCase("CF_DTLZ3")){
			return new CF_DTLZ3(parameters.getAsInt("numberOfVariables"),parameters.getAsInt("numberOfObjectives"));
		}else if (name.equalsIgnoreCase("C2_DTLZ2")){
			return new C2_DTLZ2(parameters.getAsInt("numberOfVariables"),parameters.getAsInt("numberOfObjectives"));
		}	else if (name.equalsIgnoreCase("C3_DTLZ1")){
			return new C3_DTLZ1(parameters.getAsInt("numberOfVariables"),parameters.getAsInt("numberOfObjectives"));
		}else if (name.equalsIgnoreCase("C3_DTLZ4")){
			return new C3_DTLZ4(parameters.getAsInt("numberOfVariables"),parameters.getAsInt("numberOfObjectives"));
		}else if (name.equalsIgnoreCase("ConvexC2_DTLZ2")){
			return new ConvexC2_DTLZ2(parameters.getAsInt("numberOfVariables"),parameters.getAsInt("numberOfObjectives"));
		}else if (name.equalsIgnoreCase("ConvexDTLZ2")){
			return new ConvexDTLZ2(parameters.getAsInt("numberOfVariables"),parameters.getAsInt("numberOfObjectives"));
		}   else 	if (name.equalsIgnoreCase("InvertedWFG1")){
			return new InvertedWFG1(k,l,M);
		} else if (name.equalsIgnoreCase("InvertedWFG2")){
			return new InvertedWFG2(k,l,M);
		}	else if (name.equalsIgnoreCase("InvertedWFG3")){
			return new InvertedWFG3(k,l,M);
		}	else if (name.equalsIgnoreCase("InvertedWFG4")){
			return new InvertedWFG4(k,l,M);
		}else if (name.equalsIgnoreCase("InvertedWFG5")){
			return new InvertedWFG5(k,l,M);
		}else if (name.equalsIgnoreCase("InvertedWFG6")){
			return new InvertedWFG6(k,l,M);
		}else if (name.equalsIgnoreCase("InvertedWFG7")){
			return new InvertedWFG7(k,l,M);
		}else if (name.equalsIgnoreCase("InvertedWFG8")){
			return new InvertedWFG8(k,l,M);
		}else if (name.equalsIgnoreCase("InvertedWFG9")){
			return new InvertedWFG9(k,l,M);
		} else 	if (name.equalsIgnoreCase("WFG1")){
			return new WFG1(k,l,M);
		} else if (name.equalsIgnoreCase("WFG2")){
			return new WFG2(k,l,M);
		}	else if (name.equalsIgnoreCase("WFG3")){
			return new WFG3(k,l,M);
		}	else if (name.equalsIgnoreCase("WFG4")){
			return new WFG4(k,l,M);
		}else if (name.equalsIgnoreCase("WFG5")){
			return new WFG5(k,l,M);
		}else if (name.equalsIgnoreCase("WFG6")){
			return new WFG6(k,l,M);
		}else if (name.equalsIgnoreCase("WFG7")){
			return new WFG7(k,l,M);
		}else if (name.equalsIgnoreCase("WFG8")){
			return new WFG8(k,l,M);
		}else if (name.equalsIgnoreCase("WFG9")){
			return new WFG9(k,l,M);
		}else if (name.equalsIgnoreCase("Knapsack")){
			return new Knapsack(parameters.getAsInt("numberOfObjectives"));
		}
/*		else if (name.equalsIgnoreCase("SinglePointCrossover"))
			return new SinglePointCrossover(parameters);
		else if (name.equalsIgnoreCase("PMXCrossover"))
			return new PMXCrossover(parameters);
		else if (name.equalsIgnoreCase("TwoPointsCrossover"))
			return new TwoPointsCrossover(parameters);
		else if (name.equalsIgnoreCase("HUXCrossover"))
			return new HUXCrossover(parameters);
		else if (name.equalsIgnoreCase("DifferentialEvolutionCrossover"))
			return new DifferentialEvolutionCrossover(parameters);
		else if (name.equalsIgnoreCase("BLXAlphaCrossover"))
			return new BLXAlphaCrossover(parameters);
		}*/ else{
			Configuration.logger_
					.severe("CrossoverFactory.getProblem. " + " Problem '" + name + "' not found ");
			throw new JMException("Exception in " + name + ".getCrossoverOperator()");
		} // else
	} // getCrossoverOperator
}
