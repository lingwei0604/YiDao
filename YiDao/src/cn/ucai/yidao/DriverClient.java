package cn.ucai.yidao;

import java.util.Calendar;

public class DriverClient extends Thread {

	public void run() {

		Calendar calendar = Calendar.getInstance();
		int hours = calendar.get(Calendar.HOUR_OF_DAY); // ʱ
		int minutes = calendar.get(Calendar.MINUTE); // ��
		int seconds = calendar.get(Calendar.SECOND); // ��
		System.out.println("˾�����Ѿ���ȥ��" + minutes + "����");
	}
}
