package cn.ucai.yidao;

import java.util.Calendar;

public class DriverClient extends Thread {

	int hours, minutes, seconds;
	public void run() {

		
		Calendar calendar = Calendar.getInstance();
		for (int i = 0; i < 5; i++) {
			hours = calendar.get(Calendar.HOUR_OF_DAY); // 时
			minutes = calendar.get(Calendar.MINUTE);    // 分
			seconds = calendar.get(Calendar.SECOND);    // 秒
			System.out.println("司机：已经过去了" + seconds % 5 + "分钟");
		}
	}
}
