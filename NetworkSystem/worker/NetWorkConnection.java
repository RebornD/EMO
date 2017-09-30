package NetworkSystem.worker;

import java.io.ObjectInputStream;



public interface NetWorkConnection {

	String HOSTNAME = "hughes.cs.osakafu-u.ac.jp";

	int PORT_INDEX = 23579;

	public static final int 	BYTE_SIZE = 512;

	void Communication(  ObjectInputStream one, ObjectInputStream two);


	public void Send();

	public void recieve();

}
