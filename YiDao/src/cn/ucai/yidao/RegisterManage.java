package cn.ucai.yidao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import cn.ucai.db.DBDao;
import cn.ucai.db.DBUtils;
import cn.ucai.entity.Driver;
import cn.ucai.entity.DriverCar;
import cn.ucai.entity.Person;
import cn.ucai.util.Constants;
import cn.ucai.util.MD5Utils;
import cn.ucai.util.ReadFileUtils;
import cn.ucai.util.StringUtils;

public class RegisterManage {

	static String phone, pass, type, password, toAddress, retStrFormatNowDate,
			ip, salt;
	static String ctype, model, price;
	static List<Person> person;
	static List<Driver> driver;
	static List<DriverCar> driverCar;
	static List<HashMap<String, String>> personList;
	static Map<String, List<DriverCar>> driverCarMap;
	static Scanner sc = new Scanner(System.in);

	static CarManage carManage = new CarManage();
	static String newCarTypeId = null;
	static DBDao dao = new DBDao();

	// 手机号的检测，长度，数字。
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Map<String, List<Person>> map = new HashMap<String, List<Person>>();
		person = new ArrayList<Person>();
		Map<String, List<Driver>> map2 = new HashMap<String, List<Driver>>();
		driver = new ArrayList<Driver>();

		driverCarMap = new HashMap<String, List<DriverCar>>();
		driverCar = new ArrayList<DriverCar>();
		driverCarMap.put("p3", driverCar);
		map2.put("p2", driver);
		map.put("p", person);

		inputInit();

		if (type.equals("0")) {
			// 从数据库中写入、读取数据
			handleSqlPerson();
			// personList = ReadFileUtils.readPersonMysqlFile();
			String tabPerson = "yd_person";
			personList = ReadFileUtils.findAll(tabPerson);

		} else {
			// 从数据库中写入、读取数据
			handleSqlDriver();
			// personList = ReadFileUtils.readDriverMysqlFile();
			String tabDriver = "driver";
			personList = ReadFileUtils.findAll(tabDriver);

		}

		signin();

	}

	public static void handleSqlPerson() throws Exception {
		int uid = (int) (Math.random() * 100);
		DBDao.addPerson(new Person(uid, phone, password, "123456",
				retStrFormatNowDate, ip, type));
		System.out.println(Constants.SIGN_UP_SUCCEED);
	}

	public static void handleSqlDriver() throws Exception {

		DBDao.addDriver(new Driver(phone, password, "123456",
				retStrFormatNowDate, type, ip, toAddress));
		System.out.println(Constants.SIGN_UP_SUCCEED);
	}

	public static void handleSqlFormTo() throws Exception {
		DBDao.addFromTo(new DriverCar(newCarTypeId, phone));
	}

	public static void inputInit() throws Exception {

		System.out.println(Constants.INPUT_PHONE);
		phone = sc.next();
		//
		// while (!StringUtils.isMobile(phone)) {
		// System.out.println("请输入有效的手机号码");
		// phone = sc.next();
		// }

		System.out.println(Constants.INPUT_PASSWORD);
		pass = sc.next();
		System.out.println("请选择注册的类型（乘客/车主）");

		type = sc.next();

		if (type.equals("1")) {// 如果是车主，进行可以到达的目的地选择
			System.out.println(Constants.IMPUT_TO_ADDRESS);
			String tabAddress = "yd_address";
			// carSelectFromTolist = ReadFileUtils.readAddressMysqlFile();
			carSelectFromTolist = ReadFileUtils.findAll(tabAddress);
			trip();

		}

		Date nowTime = new Date(System.currentTimeMillis());
		SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd");
		retStrFormatNowDate = sdFormatter.format(nowTime);

		InetAddress netAddress = getInetAddress();
		ip = getHostIp(netAddress);

		password = MD5Utils.md5Encode(pass + "123456");
	}

	static List<HashMap<String, String>> carSelectFromTolist;

	public static void trip() throws Exception {

		String keylike = null;
		Map<String, Object> emap = new ConcurrentHashMap<String, Object>();
		List result = new ArrayList();
		// System.out.println(Constants.IMPUT_FROM_ADDRESS);
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		keylike = br.readLine();

		for (int i = 0; i < carSelectFromTolist.size(); i++) {

			emap = (HashMap) carSelectFromTolist.get(i);
			Iterator<String> itera = emap.keySet().iterator();
			Iterator<Object> iterb = emap.values().iterator();

			while (itera.hasNext() && iterb.hasNext()) {

				// 因为迭代器每次会移动一个位置，指针移动一位，用一个临时变量来保存每次的值
				String temp = (String) iterb.next();
				// 找出键值对，值以key开头的键值对，并遍历
				if (temp.startsWith(keylike)) {
					System.out.println(carSelectFromTolist.get(i));
				}

			}
		}
		System.out.println(Constants.IMPUT_ID);
		toAddress = init();

	}

	public static String init() throws Exception {
		String key = null;
		Map<String, String> emp = new HashMap<String, String>();
		Scanner sc = new Scanner(System.in);
		key = sc.next();
		for (int i = 0; i < carSelectFromTolist.size(); i++) {
			emp = (HashMap) carSelectFromTolist.get(i);

			if (emp.containsValue(key)) {
				System.out.println(carSelectFromTolist.get(i));
				HashMap locations = carSelectFromTolist.get(i);
				// System.out.println(locations.get("location"));//文件为大写，数据库为小写
				return (String) locations.get("address");
			}
		}
		return "";

	}

	public static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static void signin() throws Exception {
		String phone, usertype = null;
		System.out.println(Constants.INPUT_PHONE);
		phone = sc.next();
		System.out.println(Constants.INPUT_PASSWORD);
		String pass = sc.next();
		String mdpass = MD5Utils.md5Encode(pass + "123456");
		for (int i = 0; i < personList.size(); i++) {
			HashMap values = personList.get(i);
			String phonedb = (String) values.get("phone");
			String passdb = (String) values.get("password");
			usertype = (String) values.get("type");
			if ((phonedb.indexOf(phone) != -1)
					&& ((passdb.indexOf(mdpass)) != -1)) {
				System.out.println("登录成功");
				break;
			}

		}

		if (usertype.equals("0")) {
			// new AddreOperator();
			AddreManage.Main();
		} else {
			// new CarManage();
			CarManage.Main2();
			newCarTypeId = CarManage.typeId;
			// String filename3 = "D:\\drivercar.txt";
			// handleType3(filename3, map3);
			handleSqlFormTo();
		}
	}

	public static InetAddress getInetAddress() {

		try {
			return InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			System.out.println("unknown host!");
		}
		return null;

	}

	public static String getHostIp(InetAddress netAddress) {
		if (null == netAddress) {
			return null;
		}
		String ip = netAddress.getHostAddress();
		return ip;
	}

}
