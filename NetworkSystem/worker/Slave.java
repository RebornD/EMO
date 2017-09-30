package NetworkSystem.worker;

import java.io.ObjectInputStream;

public class Slave extends Worker{

	private int ISDEAD;

	public static void main(String[] argv){




	}


	//シェルないでのｌ
	@Override
	public void run() {
		// TODO 自動生成されたメソッド・スタブ

	}



	@Override
	public void Communication(ObjectInputStream one, ObjectInputStream two) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	//プログラムが終わったことを伝達するのがメイン
	//変更あり
	public void Send() {


	}

	@Override
	public void recieve() {
		// TODO 自動生成されたメソッド・スタブ
	}

	public int getIsDEAD(){
		return ISDEAD;
	}

}
