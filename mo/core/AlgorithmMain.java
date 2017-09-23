package mo.core;

import javax.naming.NameNotFoundException;

import experiments.Setting;
import experiments.SettingWriter;
import mo.util.DirectoryMaker;
import mo.util.JMException;
import mo.util.Random;

public abstract class AlgorithmMain {

	protected Algorithm algorithm;

	protected Setting setting_;

	protected String DirectoryName = "result";

	public AlgorithmMain(Setting test){
		setting_ = test;
	}

	public final void MakeDirectory(String name){
		DirectoryMaker.Make(name + "/" + "FinalFUN");
		DirectoryMaker.Make(name + "/" + "FinalVAR");
		DirectoryMaker.Make(name + "/" + "InitialFUN");
		DirectoryMaker.Make(name + "/" + "InitialVAR");
		DirectoryMaker.Make(name + "/" + "Setting");
	}

	public final void write(){
		SettingWriter.clear();
		SettingWriter.merge(setting_.get());
		SettingWriter.write(DirectoryName + "/Setting");
	}

	public final void MakeDirectory() {
		System.out.println(DirectoryName);
		MakeDirectory(DirectoryName);
	}

	public final void setSeed(int seed){
		Random.set_seed(seed);
	}

	public final void execute(int nowTrial) throws NameNotFoundException, ClassNotFoundException, JMException{

		int counter=0;
		long initTime = System.currentTimeMillis();
		int  NumberOfRun = setting_.getAsInt("NumberOfTrial");
		do {
			counter++;
			setSeed(nowTrial + counter + setting_.getAsInt("Seed"));
			algorithm.setInputParameter("times",nowTrial+counter);
			System.out.println(counter + "th start");
			long innerinitTime = System.currentTimeMillis();
			algorithm.execute();
			long estimatedTime = System.currentTimeMillis() - innerinitTime;
			System.out.println(counter + "thstart  trial time is"  + estimatedTime + "ms" );
		} while(counter<NumberOfRun);
		long estimatedTime = System.currentTimeMillis() - initTime;
		setting_.add("exuecutionTime",  estimatedTime +"ms" );
	};


	public final void run(int nowTrial) throws ClassNotFoundException, JMException, NameNotFoundException{
		setParameter();
		MakeDirectory();
		write();
		execute(nowTrial);
		write();
	};

	public abstract void setParameter() throws NameNotFoundException, ClassNotFoundException, JMException;

}
