package cn.ucai.yidao;

import java.util.Calendar;

public class DriverClient extends Thread {

	public void run() {

		int hours, minutes, seconds;
		Calendar calendar = Calendar.getInstance();
		for (int i = 0; i < 5; i++) {
			hours = calendar.get(Calendar.HOUR_OF_DAY); // ʱ
			minutes = calendar.get(Calendar.MINUTE);    // ��
			seconds = calendar.get(Calendar.SECOND);    // ��
			System.out.println("˾�����Ѿ���ȥ��" + seconds % 5 + "����");
		}
	}
}
