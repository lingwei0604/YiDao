package cn.ucai.yidao;

import java.util.Calendar;

public class PassengerClient extends Thread {

	
	public void run(){
		
		double  fare= Double.parseDouble(AddreManage.basePrice);
		System.out.println("�˿ͣ����γ���������"+fare+"Ԫ");
	}
}
