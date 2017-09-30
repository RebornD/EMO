package NetworkSystem.worker;

import java.net.InetAddress;
import java.net.UnknownHostException;

public abstract class Worker implements NetWorkConnection,Runnable{

	private String MYPCNAME;

	private final int    MAXCPU;

	public Worker(){
		MAXCPU = Runtime.getRuntime().availableProcessors();
		try {
			MYPCNAME= InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			MYPCNAME = "NONE";
		}
	}

	public String GetName(){
		return MYPCNAME;
	}

	public int GetMaxCPU(){
		return MAXCPU;
	}
}
