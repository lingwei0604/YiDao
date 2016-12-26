package cn.ucai.yidao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.SeekableByteChannel;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.ucai.db.DBDao;
import cn.ucai.util.Constants;
import cn.ucai.util.ReadFileUtils;

//http://blog.csdn.net/u010555110/article/details/51103743
//http://www.2cto.com/kf/201312/264871.html

public class AddreOperator {

	static String startAddress;
	static String endAddress;
	static List<HashMap<String, String>> list;
	static HashMap<String, String> map;
	static List<HashMap<String, String>> newCarlist;
	static HashMap<String, String> newCarmap;
	static Scanner sc = new Scanner(System.in);

	public static void Main() throws Exception {

		list = ReadFileUtils.readAddressMysqlFile();
		Menu();

	}

	public static void Menu() throws IOException, Exception {

		int temp=0;
		while (true) {
			System.out
					.println("[0]=主菜单  [1]=所有查询  [2]=打车  [3]=菜单  [4]=模糊查询  [5]=退出");
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
				//handleSqlJourney();
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

		for (int i = 0; i < list.size(); i++) {
			System.out.println("第" + i + "条记录" + i + ":" + list.get(i));

		}
	}

	public static void select() throws Exception {

		String keylike = null;
		Map<String, Object> emap = new ConcurrentHashMap<String, Object>();
		List result = new ArrayList();
		System.out.println(Constants.IMPUT_TO_ADDRESS);

		keylike = sc.next();

		for (int i = 0; i < list.size(); i++) {

			emap = (HashMap) list.get(i);
			Iterator<String> itera = emap.keySet().iterator();
			Iterator<Object> iterb = emap.values().iterator();

			while (itera.hasNext() && iterb.hasNext()) {

				// 因为迭代器每次会移动一个位置，指针移动一位，用一个临时变量来保存每次的值
				String temp = (String) iterb.next();
				// 找出键值对，值以key开头的键值对，并遍历
				if (temp.startsWith(keylike)) {
					// System.out.println(itera.next() + "  " + list.get(i));
					System.out.println(list.get(i));
				}

			}
		}
		System.out.println(Constants.IMPUT_ID);
		endAddress = init(true);
		System.out.println(endAddress);
		merge();

	}

	public static void check() throws IOException {
		String key = "";
		System.out.print("请输入所查询的区域遍号(1-" + list.size() + ")：");
		key = sc.next();
		for (int i = 0; i < list.size(); i++) {

			if (list.get(i).containsValue(key)) {
				System.out.println(list.get(i));
			}

		}

	}

	public static void checkBy() throws IOException {
		String key = null;
		System.out.print(Constants.KEY_WORD);
		Map<String, String> emp = new HashMap<String, String>();
		Scanner sc = new Scanner(System.in);
		key = sc.next();
		for (int i = 0; i < list.size(); i++) {
			emp = (HashMap) list.get(i);
			if (emp.containsValue(key)) {
				System.out.println(list.get(i));
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

		for (int i = 0; i < list.size(); i++) {

			emap = (HashMap) list.get(i);
			Iterator<String> itera = emap.keySet().iterator();
			Iterator<Object> iterb = emap.values().iterator();

			while (itera.hasNext() && iterb.hasNext()) {

				// 因为迭代器每次会移动一个位置，指针移动一位，用一个临时变量来保存每次的值
				String temp = (String) iterb.next();
				// 找出键值对，值以key开头的键值对，并遍历
				if (temp.startsWith(keylike)) {
					// System.out.println(itera.next() + "  " + list.get(i));
					System.out.println(list.get(i));
				}

			}
		}

		System.out.println(Constants.IMPUT_ID);
		startAddress = init(false);
		System.out.println(startAddress);

	}

	public static String init(boolean needSave) throws Exception {
		String key = null;
		Map<String, String> emp = new HashMap<String, String>();
		Scanner sc = new Scanner(System.in);
		key = sc.next();
		for (int i = 0; i < list.size(); i++) {
			emp = (HashMap<String, String>) list.get(i);

			if (emp.containsValue(key)) {
				// System.out.println(list.get(i));
				HashMap<?, ?> locations = list.get(i);//这匹配的是接单司机的地址。
				endAddress = (String) locations.get("address");
				System.out.println(endAddress);
				System.out.println("您已选择的地点为：" + locations.get("address"));// 文件为大写，数据库为小写
				if (needSave) {
					// saveOutAddaress();
					handleSqlJourney();
				}
				System.out.println(Constants.NEXT_STEP);
				return (String) locations.get("address");
			}
		}
		return "";

	}

	static DBDao da = new DBDao();

	//行程表的插入
	public static void handleSqlJourney() throws Exception {

		Date nowTime = new Date(System.currentTimeMillis());
		SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String retStrFormatNowDate = sdFormatter.format(nowTime);
		DBDao.doInsertJourney(new OutAddress(1,newcarphone,startAddress, endAddress,
				totalprice));
		System.out.println(Constants.SAVE_SUCCEED);
	}

	static CarManage carmange = new CarManage();
	static int newcarphone;
	static int totalprice;
	
	
	public static void merge() throws InterruptedException, SQLException {
		// String filename1 = "D:/3.txt";
		boolean bool = false;
		// 定义存储读取到的数据记录的集合
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		list = ReadFileUtils.readDriverMysqlFile();
		for (int i = 0; i < list.size(); i++) {
			HashMap<?, ?> values = list.get(i);
			String addressdb = (String) values.get("address");
			//String startprice = (String) values.get("car");

			// HashMap valuesCar = newCarlist.get(i);
			//String phonedb = (String) values.get("phone");

			if (addressdb.indexOf(endAddress) > -1) {

				System.out.println("匹配成功");
				System.out.println("车主的手机号是:" + values.get("phone"));// 拿到司机的手机号，得到司机的车子，输出车子的信息。
				//String carphone = (String) values.get("phone");
				newCarlist = ReadFileUtils.readDriverCarMysqlFile();
				for (int j = 0; j < newCarlist.size();) {
					HashMap valuescar = newCarlist.get(j);

					System.out.println(valuescar);
					newcarphone = Integer.valueOf((String) valuescar.get("uid")); // 已经输出01
					System.out.println("为您匹配到的车子编号：" + newcarphone);
					finalcarlist = ReadFileUtils.readCarMysqlFile();
					System.out.println("请输入您选择的车子编号");
					finalCar();

					System.out.println("您本次出行所乘坐的车子起步价为：" + baseprice);
					totalprice = 5*(Integer.parseInt(baseprice));
					System.out.println("本次出行消费了" + totalprice);
					break;

				}

				bool = true;
				System.out.println(Constants.SUCCESS_ARRIVED_TIP);
				break;
			}
		}
		if (!bool) {
			System.out.println(Constants.ERROR_AREA);
		}

	}

	static String baseprice;
	static List<HashMap<String, String>> finalcarlist;

	public static void finalCar() {
		String key = null;
		Map<String, String> finalmp = new HashMap<String, String>();
		Scanner sc = new Scanner(System.in);
		key = sc.next();
		for (int i = 0; i < finalcarlist.size(); i++) {
			finalmp = (HashMap) finalcarlist.get(i);

			if (finalmp.containsValue(key)) {
				// System.out.println(finalcarlist.get(i));
				// System.out.println(finalcarlist.get(i).values());
				HashMap carlocations = finalcarlist.get(i);
				String cartypeid = (String) carlocations.get("subtype");
				String typeid = (String) carlocations.get("typeid");
				// System.out.println(typeid);
				baseprice = (String) carlocations.get("baseprice");
				String timeprice = (String) carlocations.get("timeprice");
				// System.out.println(locations.get("typeid"));
				System.out.println("您的车子是:" + cartypeid + "!起步价为：" + baseprice
						+ "时长价为：" + timeprice);
			}
		}

	}

}
