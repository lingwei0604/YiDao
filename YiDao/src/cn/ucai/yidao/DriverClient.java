package cn.ucai.yidao;

import java.util.Calendar;

public class DriverClient extends Thread {

	public void run() {

		Calendar calendar = Calendar.getInstance();
		int hours = calendar.get(Calendar.HOUR_OF_DAY); // 时
		int minutes = calendar.get(Calendar.MINUTE); // 分
		int seconds = calendar.get(Calendar.SECOND); // 秒
		System.out.println("司机：已经过去了" + minutes + "分钟");
	}
}
