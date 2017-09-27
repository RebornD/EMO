package experiments;
import static org.kohsuke.args4j.ExampleMode.*;

import java.util.List;

import javax.naming.NameNotFoundException;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
/*
 * ここで指定することによりプログラムを実行できるようにする．
 * 増田さんが使用していたargs4j が便利そうなのでそれを利用できるか検討する．
 *
 * 現段階の案として，いちいち変更が必要なものに関して，settingに書くよりここに書いた方がいいものはここに書く．
 * 一回の試行で一つの問題を解く;
 *
 *
 */

import mo.core.AlgorithmMain;
import mo.main.AlgorithmMainFactory;
import mo.util.JMException;

/*
 * きれいにかきましょう．
 *　SMSEMOAに使用されるrefについては，後でやる・
 */

public class start {

	@Option(name="-ga", aliases= "--GeneticAlgorithm", required=true, usage="Specify the populationSize")
	private String geneticAlgorithmName;
	@Option(name="-nowTrial", aliases= "---nowTrial", required=false, usage="Specify the nowTrial")
	private int nowTrial = 0;
	@Option(name="-p", aliases= "--Problem Name", required=true, usage="set the Problem Name")
	private String problenName;
	@Option(name="-max", aliases= "--MaxProblem", required=false, metaVar="<MaxProblem>", usage="Specify the the maximum Problem or minimum Problem: default assume min Problem")
	private boolean IsMax = false;
	@Option(name="-norm", aliases= "--norm", required=false, metaVar="<ReferencePoint>", usage="you ")
	private boolean IsNorm = false;
	@Option(name="-on", aliases= "--output Normalization", required=false, metaVar="<MOEAD Alphar>", usage="you set whether Objectives of Population are Normalized or not, Default false")
	private boolean OutNorm = false;
	@Option(name="-o", aliases= "--Objectives", required=false, metaVar="<Objectives>", usage="set the number OfObjectives")
	private int Objectives = -1;
	@Option(name="-ps", aliases= "--PopulationSize", required=false, metaVar="<populationSize>", usage="Specify the populationSize")
	private int populationSize = -1;
	@Option(name="-co", aliases= "--Crossover", required=false, metaVar="<Crossover>", usage="Specify the Crossover")
	private String crossoverName = null;
	@Option(name="-cop", aliases= "--CrossoverProbability", required=false, metaVar="<CrossovePror>", usage="Specify the CrossoverProbability")
	private double CrossoverProbablity = 2;
	@Option(name="-cod", aliases= "--CrossoverDistribution", required=false, metaVar="<CrossoverDistribution>", usage="Specify the CrossoverDistribution")
	private double CrossoverDistribution = -1;
	@Option(name="-mt", aliases= "--Mutation", required=false, metaVar="<Mutation>", usage="Specify the Mutation")
	private String MutationName = null;
	@Option(name="-mtp", aliases= "--MutationProbablity", required=false, metaVar="<mutationProbablity>", usage="Specify the Mutation Probablity")
	private double MutationProbablity =  2;
	@Option(name="-mtd", aliases= "--MutationDistribution", required=false, metaVar="<MutationDistribution>", usage="Specify the Mutation Probablity")
	private double MutationDistribution = -1;
	@Option(name="-rep", aliases= "--repeat time", required=false, metaVar="<Repeat time>", usage="Specify the the Iteration")
	private int reps = -1;
	@Option(name="-div", aliases= "--Division", required=false, metaVar="<Division>", usage="Specify the division in MOEA/D or NSGAIII")
	private int  Division = -1;
	@Option(name="-indiv", aliases= "--Innerdivision", required=false, metaVar="<InnerDivision>", usage="Specify the inner division in MOEA/D or NSGA-III")
	private int InnerDivision = -1;
	@Option(name="-ref", aliases= "--ReferencePoint", required=false, metaVar="<ReferencePoint>", usage="Specify the ReferencePoint in SMS-EMOA")
	private String ref = null;
	@Option(name="-eva", aliases= "--MaxEvaluations", required=false, metaVar="<MaxEvaluations>", usage="Specify --MaxEvaluations")
	private int maxEvaluations_ = -1;
	@Option(name="-gen", aliases= "--MaxGeneration", required=false, metaVar="<MaxGeneration>", usage="Specify --MaxGeneration")
	private int maxGenerations_ = -1;
	@Option(name="-seed", aliases= "--seed", required=false, metaVar="<Seed >", usage="Specify the seed of random number")
	private int seed_ = 1;
	@Option(name="-sf", aliases= "--ScalarFunction", required=false, metaVar="<ScalarFunction>", usage="Specify the ScalarFunction")
	private String ScalarFunction = null;
	@Option(name="-alpha", aliases= "--MOEAD Alpha", required=false, usage="Specify MOEAD Alphar, changing the magnification of the distance to the idealpoint")
	private double alphar = - 1;
	@Option(name="-mn", aliases= "--mating Neighborhood", required=false, usage="Specify MOEAD mating neighborhood")
	private int matingNeighborhood = - 1;
	@Option(name="-rn", aliases= "--replace Neighborhood", required=false, usage="Specify MOEAD replace neighborhood")
	private int ReplaceNeighborhood = - 1;
	@Option(name="-ndv", aliases= "--Number of Distance variables", required=false, usage="Specify the number of the distance variabels on DTLZ Problem")
	private int NumberOfVariables_ = - 1;
	@Option(name="-wfgk", aliases= "--Number of position variables", required=false, usage="Specify the number of the position variables on WFG Problem")
	private int k = - 1;
	@Option(name="-wfgl", aliases= "--Number of distance variables", required=false, usage="Specify the number of the distance variables on WFG Problem")
	private int l = - 1;
	@Option(name="-ep", aliases= "--Epsilon", required=false, usage="Specify the Epsilon on NormalizeObjectives")
	private double epsiron = - 1;
	@Option(name="-dir", aliases= "--Directory", required=false, usage="Specify the Directory storing Directory")
	private String Directory = null;
	@Argument
	private List<String> arguments;

	public static void main(String[] args) throws NameNotFoundException, ClassNotFoundException, JMException {
		 new start().domain(args);
	}

	public void domain(String[] args) throws NameNotFoundException, ClassNotFoundException, JMException {
	      CmdLineParser parser = new CmdLineParser(this);
	        // if you have a wider console, you could increase the value;
	        // here 80 is also the default
	        parser.setUsageWidth(80);

	        try {

	        	parser.parseArgument(args);
	        	if (arguments != null) throw new IllegalArgumentException("No file is given. ");
	        } catch( CmdLineException e ) {
	            System.err.println(e.getMessage());
	            System.err.println("java test [options...] arguments...");
	            // print the list of available options
	            parser.printUsage(System.err);
	            System.err.println();
	            // print option sample. This is useful some time
	            System.err.println("  Example: java SampleMain"+parser.printExample(ALL));
	            return;
	        }
	        Setting hashmap  = new Setting();
	        add(hashmap);
	        hashmap.experiment_setting("setting/" + geneticAlgorithmName + ".st");
	        AlgorithmMain algorithm =  AlgorithmMainFactory.getAlgorithmMain(geneticAlgorithmName, hashmap);
	        algorithm.run(nowTrial);
	}

	public void add(Setting hashmap) throws JMException{
        hashmap.add("Problem", problenName);
        hashmap.add("Seed", seed_);


        if (maxEvaluations_ != -1){
         	hashmap.add("maxEvaluations",maxEvaluations_);
        }else if (maxGenerations_ != -1){
        	maxEvaluations_ = maxGenerations_*populationSize;
        	hashmap.add("maxEvaluations",maxEvaluations_);
        }
        System.out.println(maxEvaluations_ + "	");


        hashmap.add("IsMax", IsMax);
        hashmap.add("IsNorm", IsNorm);
        hashmap.add("outputNormal", OutNorm);

        if(epsiron != -1) hashmap.add("epsilon", epsiron);
        if(populationSize != -1) hashmap.add("populationSize", populationSize);
        if(crossoverName != null) hashmap.add("CrossoverName", crossoverName);
        if(CrossoverProbablity != 2) hashmap.add("CrossoverProbablity", CrossoverProbablity);
        if(CrossoverDistribution != -1) hashmap.add("CrossoverDistribution", CrossoverDistribution);
        if(MutationName != null) hashmap.add("MutationName", MutationName);
        if(MutationProbablity != 2) hashmap.add("MutationProbablity", MutationProbablity);
        if(MutationDistribution != -1) hashmap.add("MutationDistribution", MutationDistribution);
        if(reps != -1) hashmap.add("NumberOfTrial", reps);
        if(Division != -1) hashmap.add("Division", Division);
        if(InnerDivision != -1) hashmap.add("InnerWeightDivision",InnerDivision);
        if(Objectives != -1) hashmap.add("Objectives", Objectives);
        if(Objectives != -1) hashmap.add("numberOfObjectives", Objectives);
        if(ref != null) hashmap.add("ref", ref);
        if(ScalarFunction != null) {
        	if(!IsMax)hashmap.add("ScalarFunction", ScalarFunction + "Formin");
        	else
        		hashmap.add("ScalarFunction", ScalarFunction);
        }
        if(k != -1) hashmap.add("k", k);
        if(l != -1) hashmap.add("l", l);
        if(NumberOfVariables_ != -1) hashmap.add("NumberOfVariables", NumberOfVariables_);
        if(alphar != -1) hashmap.add("alpha", alphar);
        if(matingNeighborhood !=-1) hashmap.add("matingNeighborhood", matingNeighborhood);
        if(ReplaceNeighborhood !=-1) hashmap.add("ReplaceNeighborhood", ReplaceNeighborhood);
        if(Directory != null) hashmap.add("ResultDirectory", Directory);

	}



}
