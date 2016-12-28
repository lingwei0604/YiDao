package cn.ucai.yidao;

import java.io.BufferedReader;
import java.lang.System;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import cn.ucai.db.DBDao;
import cn.ucai.entity.OutAddress;
import cn.ucai.util.Constants;
import cn.ucai.util.ReadFileUtils;

//http://blog.csdn.net/u010555110/article/details/51103743
//http://www.2cto.com/kf/201312/264871.html

public class AddreManage {

	static String startAddress;
	static String endAddress;
	static int carId;
	static float totalPrice;
	static String carType;
	static List<HashMap<String, String>> addrList;
	static HashMap<String, String> map;
	static List<HashMap<String, String>> newCarList;
	static HashMap<String, String> newCarMap;
	static Scanner sc = new Scanner(System.in);

	public static void Main() throws Exception {

		String tabAddress = "yd_address";
		addrList = ReadFileUtils.findAll(tabAddress);
		Menu();

	}

	public static void Menu() throws IOException, Exception {

		int temp = 0;
		boolean select = true;
		while (select) {
			System.out
					.println("[0]=主菜单  [1]=所有查询  [2]=打车  [3]=菜单  [4]=模糊查询[5]=退出");
			System.out
					.println("Welcome to YiDao，Please Input a Number(above)：");
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			String str = null;
			try {
				str = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (isInteger(str)) {
				temp = Integer.parseInt(str);
			} else {
				System.out.println("请输入主目录中的有效数字");
				continue;
			}

			switch (temp) {
			case 0:
				break;
			case 1:
				check();
				break;
			case 2:
				trip();
				select();
				startRoad();
				break;
			case 3:
				display();
				break;
			case 4:
				checkBy();
				break;
			case 5:

				System.exit(0);
				break;
			default:
				System.out.println("操作有误，请重新输入！");
			}
		}
	}

	public static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static void display() {

		for (int i = 0; i < addrList.size(); i++) {
			System.out.println("第" + i + "条记录" + i + ":" + addrList.get(i));

		}
	}

	public static void select() throws Exception {

		String keylike = null;
		Map<String, Object> emap = new ConcurrentHashMap<String, Object>();
		System.out.println(Constants.IMPUT_TO_ADDRESS);
		keylike = sc.next();

		for (int i = 0; i < addrList.size(); i++) {

			emap = (HashMap) addrList.get(i);
			Iterator<String> itera = emap.keySet().iterator();
			Iterator<Object> iterb = emap.values().iterator();

			while (itera.hasNext() && iterb.hasNext()) {

				String temp = (String) iterb.next();
				if (temp.startsWith(keylike)) {
					System.out.println(addrList.get(i));
				}

			}
		}
		System.out.println(Constants.IMPUT_ID);
		System.out.println(matchAddress(true));

		AddreThread addrthread = new AddreThread();
		addrthread.start();
		match();

	}

	public static void check() throws IOException {
		String key = null;
		System.out.print("请输入所查询的区域遍号(1-" + addrList.size() + ")：");
		for (int i = 0; i < addrList.size(); i++) {
			if (addrList.get(i).containsValue(sc.next())) {
				System.out.println(addrList.get(i));
			}
		}

	}

	public static void checkBy() throws IOException {
		String key = null;
		System.out.print(Constants.KEY_WORD);
		Map<String, String> emp = new HashMap<String, String>();
		for (int i = 0; i < addrList.size(); i++) {
			emp = (HashMap) addrList.get(i);
			if (emp.containsValue(sc.next())) {
				System.out.println(addrList.get(i));
			}
		}
	}

	public static void trip() throws Exception {

		String keylike = null;
		Map<String, Object> emap = new ConcurrentHashMap<String, Object>();
		System.out.println(Constants.IMPUT_FROM_ADDRESS);
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		keylike = br.readLine();

		for (int i = 0; i < addrList.size(); i++) {

			emap = (HashMap) addrList.get(i);
			Iterator<String> itera = emap.keySet().iterator();
			Iterator<Object> iterb = emap.values().iterator();

			while (itera.hasNext() && iterb.hasNext()) {

				String temp = (String) iterb.next();
				if (temp.startsWith(keylike)) {
					System.out.println(addrList.get(i));
				}

			}
		}

		System.out.println(Constants.IMPUT_ID);
		System.out.println(matchAddress(false));

	}

	public static String matchAddress(boolean needSave) throws Exception {
		String key = null;
		Map<String, String> emp = new HashMap<String, String>();
		for (int i = 0; i < addrList.size(); i++) {
			emp = (HashMap<String, String>) addrList.get(i);

			if (emp.containsValue(sc.next())) {
				// System.out.println(list.get(i));
				HashMap<?, ?> locations = addrList.get(i);
				endAddress = (String) locations.get("address");
				System.out.println(endAddress);
				// System.out.println("您已选择的地点为：" + locations.get("location"));
				if (needSave) {
					addJourney();
				}
				System.out.println(Constants.NEXT_STEP);
				return (String) locations.get("address");
			}
		}
		return "";

	}

	public static void addJourney() throws Exception {

		Date nowTime = new Date(System.currentTimeMillis());
		SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String retStrFormatNowDate = sdFormatter.format(nowTime);
		// DBDao.addJourney(new OutAddress(1, carId, startAddress,endAddress,
		// totalPrice));
		System.out.println(Constants.SAVE_SUCCEED);
	}

	public static void match() throws InterruptedException, SQLException {
		boolean bool = false;
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String tabDriver = "driver";
		list = ReadFileUtils.findAll(tabDriver);
		for (int i = 0; i < list.size(); i++) {
			HashMap<?, ?> values = list.get(i);
			String addressdb = (String) values.get("address");

			if (addressdb.indexOf(endAddress) > -1) {
				// 匹配成功，可以断断续续的跳出多条数据。
				System.out.println("匹配成功");
				System.out.println("车主的手机号是:" + values.get("phone"));

				String tabDriverList = "yd_driver_dist";
				newCarList = ReadFileUtils.findAll(tabDriverList);
				for (int j = 0, row = newCarList.size(); j < row; j++) {
					HashMap valuescar = newCarList.get(j);

					System.out.println(valuescar);
					carId = Integer.valueOf((String) valuescar.get("uid"));
					System.out.println("为您匹配到的车子编号：" + carId);
					String tabCar = "yd_car";
					System.out.println(Thread.currentThread().getName());
					matchCarList = ReadFileUtils.findAll(tabCar);
					// System.out.println("请输入您选择的车子");
					carInfo((String) valuescar.get("uid"));
					Thread.sleep(2000);
				}

				bool = true;
				break;
			}
		}
		if (!bool) {
			System.out.println(Constants.ERROR_AREA);
		}

	}

	static String basePrice;
	static List<HashMap<String, String>> matchCarList;

	public static void carInfo(String uid) throws InterruptedException {
		Map<String, String> finalmp = new HashMap<String, String>();
		for (int i = 0, row = matchCarList.size(); i < row; i++) {
			finalmp = (HashMap) matchCarList.get(i);
			if (finalmp.containsValue(uid)) {

				HashMap carlocations = matchCarList.get(i);
				String cartypeid = (String) carlocations.get("subtype");
				String typeid = (String) carlocations.get("typeid");
				basePrice = (String) carlocations.get("baseprice");
				String timeprice = (String) carlocations.get("timeprice");
			}
		}
		System.out.println("您本次出行所乘坐的车子起步价为：" + basePrice);
		System.out.println(Constants.SUCCESS_ARRIVED_TIP);
		System.out.println("本次出行预计消费了"
				+ (Math.random() * 5 * (int) (Double.parseDouble(basePrice))));

	}

	public static void startRoad() throws InterruptedException {
		System.out.println("请选择你本次出行的车子");
		carInfo(sc.next());
		DriverClient driverClient = new DriverClient();
		driverClient.start();
		PassengerClient passengerClient = new PassengerClient();
		passengerClient.start();
	}

}
