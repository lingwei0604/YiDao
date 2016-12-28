package cn.ucai.yidao;

import java.util.Calendar;

public class PassengerClient extends Thread {

	
	public void run(){
		
		double  fare= Double.parseDouble(AddreManage.basePrice);
		System.out.println("乘客：本次出行消费了"+fare+"元");
	}
}
