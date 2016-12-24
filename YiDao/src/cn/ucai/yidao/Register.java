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
import cn.ucai.util.Constants;
import cn.ucai.util.MD5Utils;
import cn.ucai.util.ReadFileUtils;
import cn.ucai.util.StringUtils;

public class Register {

	static String phone, pass, type, mdpass, toaddress, retStrFormatNowDate,
			ip, salt;
	static String ctype, model, price;
	private static Connection conn = DBUtils.getConnection();
	private static PreparedStatement ps = null;
	private ResultSet rs = null;

	static List<Person> per;
	static List<Driver> per2;
	static List<DriverCar> drivercar;
	static List<HashMap<String, String>> list;
	static List<HashMap<String, String>> carSelectFromTolist;
	static Map<String, List<DriverCar>> map3;
	static Scanner sc = new Scanner(System.in);

	static AddreOperator addre = new AddreOperator();

	static CarManage carmange = new CarManage();
	static String newcartypeid = null;
	static DBDao dao = new DBDao();

	// �ֻ��ŵļ�⣬���ȣ����֡�
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Map<String, List<Person>> map = new HashMap<String, List<Person>>();
		per = new ArrayList<Person>();
		Map<String, List<Driver>> map2 = new HashMap<String, List<Driver>>();
		per2 = new ArrayList<Driver>();

		map3 = new HashMap<String, List<DriverCar>>();
		drivercar = new ArrayList<DriverCar>();
		map3.put("p3", drivercar);
		map2.put("p2", per2);
		map.put("p", per);

		inputInit();

		if (type.equals("chengke")) {
			// �����ݿ���д�롢��ȡ����
			handleSqlPerson();
			list = ReadFileUtils.readPersonMysqlFile();

		} else {
			// �����ݿ���д�롢��ȡ����
			handleSqlDriver();
			list = ReadFileUtils.readDriverMysqlFile();

		}
		signin();

	}

	public static void handleSqlPerson() throws Exception {

		dao.doInsertPerson(new Person(phone, mdpass, "123456",
				retStrFormatNowDate, ip, type));
		System.out.println(Constants.SIGN_UP_SUCCEED);
	}

	public static void handleSqlDriver() throws Exception {

		dao.doInsertDriver(new Driver(phone, mdpass, "123456",
				retStrFormatNowDate, type, ip, toaddress));
		System.out.println(Constants.SIGN_UP_SUCCEED);
	}

	public static void handleSqlFormTo() throws Exception {
		dao.doInsertFromTo(new DriverCar(newcartypeid, phone));
	}

	public static void inputInit() throws Exception {

		System.out.println(Constants.INPUT_PHONE);
		phone = sc.next();

		while (!StringUtils.isMobile(phone)) {
			System.out.println("��������Ч���ֻ�����");
			phone = sc.next();
		}

		System.out.println(Constants.INPUT_PASSWORD);
		pass = sc.next();
		System.out.println("��ѡ��ע������ͣ��˿�/������");

		type = sc.next();

		if (type.equals("����")) {// ����ǳ��������п��Ե����Ŀ�ĵ�ѡ��
			System.out.println(Constants.IMPUT_TO_ADDRESS);
			carSelectFromTolist = ReadFileUtils.readAddressMysqlFile();
			trip();

		}

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// �������ڸ�ʽ
		Date nowTime = new Date(System.currentTimeMillis());
		SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd");
		retStrFormatNowDate = sdFormatter.format(nowTime);

		InetAddress netAddress = getInetAddress();
		ip = getHostIp(netAddress);

		mdpass = MD5Utils.md5Encode(pass + "123456");
	}

	

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

				// ��Ϊ������ÿ�λ��ƶ�һ��λ�ã�ָ���ƶ�һλ����һ����ʱ����������ÿ�ε�ֵ
				String temp = (String) iterb.next();
				// �ҳ���ֵ�ԣ�ֵ��key��ͷ�ļ�ֵ�ԣ�������
				if (temp.startsWith(keylike)) {
					System.out.println(carSelectFromTolist.get(i));
				}

			}
		}
		System.out.println(Constants.IMPUT_ID);
		toaddress = init();

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
				// System.out.println(locations.get("location"));//�ļ�Ϊ��д�����ݿ�ΪСд
				return (String) locations.get("location");
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
		for (int i = 0; i < list.size(); i++) {
			HashMap values = list.get(i);
			String phonedb = (String) values.get("phone");
			String passdb = (String) values.get("pass");
			usertype = (String) values.get("type");
			if ((phonedb.indexOf(phone) != -1)
					&& ((passdb.indexOf(mdpass)) != -1)) {
				System.out.println("��¼�ɹ�");
				break;
			}

		}

		if (usertype.equals("chengke")) {
			new AddreOperator().Main();
		} else {
			new CarManage().Main2();
			newcartypeid = carmange.typeid;
			// String filename3 = "D:\\drivercar.txt";
			// handleType3(filename3, map3);
			handleSqlFormTo();
		}
	}

	public static void handleType3(String filename,
			Map<String, List<DriverCar>> map3) throws IOException {

		drivercar.add(new DriverCar(newcartypeid, phone));

		String line = System.getProperty("line.separator");
		StringBuffer str = new StringBuffer();
		FileWriter fw = new FileWriter(filename, true);
		Set<String> keySet = map3.keySet();
		for (Iterator<String> it = keySet.iterator(); it.hasNext();) {
			String key = it.next();
			List<DriverCar> list = map3.get(key);
			for (Iterator<DriverCar> it2 = list.iterator(); it2.hasNext();) {
				DriverCar pd = it2.next();
				str.append(pd.getUserid() + " : " + pd.getCartype()).append(
						line);
				System.out.println("ע�����");
			}
		}
		fw.write(str.toString());
		fw.close();

	}

	public static List<HashMap<String, String>> readTxtFile(String filePath) {

		// ����洢��ȡ�������ݼ�¼�ļ���
		list = new ArrayList<HashMap<String, String>>();
		try {

			String encoding = "GB2312";
			File file = new File(filePath);
			// �ж��ļ��Ƿ����
			if (file.isFile() && file.exists()) {
				// ���ǵ������ʽ
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				// ��¼��ȡ�������ļ�������
				int count = 0;
				// �����ֶε�����
				String[] fields = null;
				// ����ÿ����¼ȡ�����ֶ�ֵ����
				String[] fieldValue = null;
				// ����Map����
				HashMap<String, String> map = new HashMap<String, String>();
				while ((lineTxt = bufferedReader.readLine()) != null) {

					map = new HashMap<String, String>();
					if (count == 0) {
						fields = lineTxt.split("\\:");

					} else {
						fieldValue = lineTxt.split("\\:");
						for (int i = 0; i < fields.length; i++) {
							for (int j = 0; j < fieldValue.length; j++) {
								if (i == j) {
									map.put(fields[i], fieldValue[j]);

								}
							}
						}
						// ����ȡ��ÿһ�еļ�¼���뵽list������
						list.add(map);
					}

					count++;
				}
				read.close();
			} else {
				System.out.println("�Ҳ���ָ�����ļ�");
			}
		} catch (Exception e) {
			System.out.println("��ȡ�ļ����ݳ���");
			e.printStackTrace();
		}
		return list;
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
