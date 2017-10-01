package NetworkSystem.worker;

import java.io.ObjectInputStream;

public class Slave extends Worker{

	private int ISDEAD;



	public static void main(String[] argv){




	}


	@Override
	public void run() {

	}



	@Override
	public void Communication(ObjectInputStream one, ObjectInputStream two) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	//プログラムが終わったことを伝達するのがメイン
	//変更あり
	public void Send() {
/*		  ServerSocket ServerSocket = null;
		  byte[] buffer = new byte[BUFFER_SIZE];
		  Socket socket = null;
		  int test;
		  try {
			  ServerSocket = new ServerSocket(PORT_INDEX);
			  socket = ServerSocket.accept();

			  ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());//new ObjectOutputStream(test);

			  String line;

			  while((line = in.readLine()) != null){
				  System.out.println("recieve!!: " + line);
				  out.println(line);
				  System.out.println("Transmission!!: " + line);
			  }

		  }catch (IOException e){
			  e.printStackTrace();
			  return
		  } finally{
			  try {
			        if (socket != null) {
			          socket.close();
			        }
			  } catch (IOException e){}

			  try {
				  if(ServerSocket != null){
					  ServerSocket.close();
				  }
			  }catch (IOException e){}
		  }

*/

	}

	@Override
	public void recieve() {
		// TODO 自動生成されたメソッド・スタブ
	}

	public int getIsDEAD(){
		return ISDEAD;
	}

}
