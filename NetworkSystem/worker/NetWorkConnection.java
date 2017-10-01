package NetworkSystem.worker;

import java.io.ObjectInputStream;



public interface NetWorkConnection {

	String HOSTNAME = "hughes.cs.osakafu-u.ac.jp";

	int PORT_INDEX = 23579;

	int SUCCESS = 1;
	int NETWORKEXCEPTION = 2;
	int WORKEREXCEPTION = 3;
	int DEAD	= -1;

	public static final int 	BUFFER_SIZE = 512;

	void Communication(  ObjectInputStream one, ObjectInputStream two);

	public void Send();

	public void recieve();


}
