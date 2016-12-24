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
	static Scanner sc = new Scanner(System.in);

	
	public static void Main() throws Exception {

		//���ı��ļ���ȡ����
		//String filePath = "D:/beijing1.txt";
		//list = ReadFileUtils.readTxtFile(filePath);
		
		//�����ݿ��ȡ�ļ�
		list = ReadFileUtils.readAddressMysqlFile();
		//System.out.println(list);
		Menu();

	}

	public static void Menu() throws IOException, Exception {

		int temp = 0;
		while (true) {
			System.out
					.println("[0]=���˵�  [1]=���в�ѯ  [2]=��  [3]=�˵�  [4]=ģ����ѯ  [5]=�˳�");
			System.out
					.println("Welcome to YiDao��Please Input a Number(above)��");
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			String str = null;
			try {
				str = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (isInteger(str)) {
				temp = Integer.parseInt(str);
			} else {
				System.out.println("��������Ŀ¼�е���Ч����");
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
				break;
			case 3:
				display();
				break;
			case 4:
				checkBy();
				// init();
				break;
			case 5:

				// map.clear();
				// System.exit(0);
				break;
			default:
				System.out.println("�����������������룡");
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
			System.out.println("��" + i + "����¼" + i + ":" + list.get(i));

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

				// ��Ϊ������ÿ�λ��ƶ�һ��λ�ã�ָ���ƶ�һλ����һ����ʱ����������ÿ�ε�ֵ
				String temp = (String) iterb.next();
				// �ҳ���ֵ�ԣ�ֵ��key��ͷ�ļ�ֵ�ԣ�������
				if (temp.startsWith(keylike)) {
					//System.out.println(itera.next() + "  " + list.get(i));
					System.out.println(list.get(i));
				}

			}
		}
		System.out.println(Constants.IMPUT_ID);
		endAddress = init(true);
		merge();

	}

	public static void finish() {
		System.out.println("�г̷����ɹ���Լ���" + Math.random() * 10 + "����");
	}

	public static void check() throws IOException {
		String key = "";
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		// System.out.println(list);
		System.out.print("����������ѯ��������(1-" + list.size() + ")��");
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
		List result = new ArrayList();
		System.out.println(Constants.IMPUT_FROM_ADDRESS);
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		keylike = br.readLine();

		for (int i = 0; i < list.size(); i++) {

			emap = (HashMap) list.get(i);
			Iterator<String> itera = emap.keySet().iterator();
			Iterator<Object> iterb = emap.values().iterator();

			while (itera.hasNext() && iterb.hasNext()) {

				// ��Ϊ������ÿ�λ��ƶ�һ��λ�ã�ָ���ƶ�һλ����һ����ʱ����������ÿ�ε�ֵ
				String temp = (String) iterb.next();
				// �ҳ���ֵ�ԣ�ֵ��key��ͷ�ļ�ֵ�ԣ�������
				if (temp.startsWith(keylike)) {
					//System.out.println(itera.next() + "  " + list.get(i));
					System.out.println(list.get(i));
				}

			}
		}

		System.out.println(Constants.IMPUT_ID);
		startAddress = init(false);

	}

	public static String init(boolean needSave) throws Exception {
		String key = null;
		Map<String, String> emp = new HashMap<String, String>();
		Scanner sc = new Scanner(System.in);
		key = sc.next();
		for (int i = 0; i < list.size(); i++) {
			emp = (HashMap) list.get(i);

			if (emp.containsValue(key)) {
				//System.out.println(list.get(i));
				HashMap locations = list.get(i);
				System.out.println("����ѡ��ĵص�Ϊ��"+locations.get("location"));//�ļ�Ϊ��д�����ݿ�ΪСд
				if (needSave) {
					//saveOutAddaress();
					handleSqlJourney();
				}
				System.out.println(Constants.NEXT_STEP);
				return (String) locations.get("location");
			}
		}
		return "";

	}
	
	static DBDao da = new DBDao();
	public static void handleSqlJourney() throws Exception {

		Date nowTime = new Date(System.currentTimeMillis());
		SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String retStrFormatNowDate = sdFormatter.format(nowTime);
		da.doInsertJourney(new OutAddress(startAddress, endAddress, retStrFormatNowDate));
		System.out.println(Constants.SAVE_SUCCEED);
	}
	
	public static void saveOutAddaress() {
		Map<String, List<OutAddress>> map = new HashMap<String, List<OutAddress>>();

		List<OutAddress> per = new ArrayList<OutAddress>();
		map.put("p", per);
		Date nowTime = new Date(System.currentTimeMillis());
		SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String retStrFormatNowDate = sdFormatter.format(nowTime);
		per.add(new OutAddress(startAddress, endAddress, retStrFormatNowDate));

		try {

			String line = System.getProperty("line.separator");
			StringBuffer str = new StringBuffer();
			FileWriter fw = new FileWriter("D:\\2.txt", true);

			Set<String> keySet = map.keySet();
			for (Iterator<String> it = keySet.iterator(); it.hasNext();) {
				String key = it.next();
				List<OutAddress> list = map.get(key);
				for (Iterator<OutAddress> it2 = list.iterator(); it2.hasNext();) {
					OutAddress address = it2.next();
					str.append(
							address.getStartaddress() + " : "
									+ address.getEndaddress()).append(line);
					System.out.println("�������");
				}
			}
			fw.write(str.toString());
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	static CarManage carmange = new CarManage();

	public static void merge() throws InterruptedException, SQLException {
		//String filename1 = "D:/3.txt";
		boolean bool = false;
		// ����洢��ȡ�������ݼ�¼�ļ���
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		list = ReadFileUtils.readDriverMysqlFile();
//		try {
//
//			String encoding = "GB2312";
//			File file = new File(filename1);
//			// �ж��ļ��Ƿ����
//			if (file.isFile() && file.exists()) {
//				// ���ǵ������ʽ
//				InputStreamReader read = new InputStreamReader(
//						new FileInputStream(file), encoding);
//				BufferedReader bufferedReader = new BufferedReader(read);
//				String lineTxt = null;
//				// ��¼��ȡ�������ļ�������
//				int count = 0;
//				// �����ֶε�����
//				String[] fields = null;
//				// ����ÿ����¼ȡ�����ֶ�ֵ����
//				String[] fieldValue = null;
//				// ����Map����
//				map = new HashMap<String, String>();
//				while ((lineTxt = bufferedReader.readLine()) != null) {
//
//					map = new HashMap<String, String>();
//					if (count == 0) {
//						fields = lineTxt.split("\\:");
//
//					} else {
//						fieldValue = lineTxt.split("\\:");
//						for (int i = 0; i < fields.length; i++) {
//							for (int j = 0; j < fieldValue.length; j++) {
//								if (i == j) {
//									map.put(fields[i], fieldValue[j]);
//
//								}
//							}
//						}
//						// ����ȡ��ÿһ�еļ�¼���뵽list������
//						list.add(map);
//					}
//
//					count++;
//				}
//				read.close();
//			} else {
//				System.out.println("�Ҳ���ָ�����ļ�");
//			}
//		} catch (Exception e) {
//			System.out.println("��ȡ�ļ����ݳ���");
//			e.printStackTrace();
//		}
		for (int i = 0; i < list.size(); i++) {
			HashMap values = list.get(i);
			String addressdb = (String) values.get("address");
			String startprice = (String) values.get("car");

			// HashMap valuesCar = newCarlist.get(i);
			String phonedb = (String) values.get("phone");

			if (addressdb.indexOf(endAddress) > -1) {

				System.out.println("ƥ��ɹ�");
				System.out.println("�������ֻ�����:" + values.get("phone"));// �õ�˾�����ֻ��ţ��õ�˾���ĳ��ӣ�������ӵ���Ϣ��
				String carphone = (String) values.get("phone");
				//String filenamecar = "D:/drivercar.txt";
				//readDriverCarFile(filenamecar);
			    newCarlist = ReadFileUtils.readDriverCarMysqlFile();
				for (int j = 0; j < newCarlist.size(); j++) {
					HashMap valuescar = newCarlist.get(j);

					System.out.println(valuescar);
					String newcarphone = (String) valuescar.get("usercarid"); // �Ѿ����01
					System.out.println("Ϊ��ƥ�䵽�ĳ��ӱ�ţ�"+newcarphone);
					// if(newcarphone.indexOf(carphone)>-1){
					//String filePathCar = "D:/car.txt";
					//CarManage carmanage = new CarManage();
					//carmanage.readTxtFile(filePathCar);
					finalcarlist = ReadFileUtils.readCarMysqlFile();
					//System.out.println(finalcarlist);
					System.out.println("��������ѡ��ĳ��ӱ��");
				    finalCar();
					
					//Thread.sleep(5000);
					//String bprice = carmanage.baseprice;
					System.out.println("�����γ����������ĳ����𲽼�Ϊ��"+baseprice);
					System.out.println("���γ��л�����"+Math.random()*10  * (Integer.parseInt(baseprice)));
					// }
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
				//System.out.println(finalcarlist.get(i));
				//System.out.println(finalcarlist.get(i).values());
				HashMap carlocations = finalcarlist.get(i);
				String cartypeid = (String) carlocations.get("subtype");
				String typeid = (String) carlocations.get("typeid");
				//System.out.println(typeid);
				baseprice = (String) carlocations.get("baseprice");
				String timeprice = (String) carlocations.get("timeprice");
				// System.out.println(locations.get("typeid"));
				System.out.println("���ĳ�����:" + cartypeid + "!�𲽼�Ϊ��" + baseprice
						+ "ʱ����Ϊ��" + timeprice);
			}
		}

	}

	static List<HashMap<String, String>> newCarlist;
	static HashMap<String, String> newCarmap;

	public static List<HashMap<String, String>> readDriverCarFile(
			String filePath) {

		// ����洢��ȡ�������ݼ�¼�ļ���
		newCarlist = new ArrayList<HashMap<String, String>>();
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
				newCarmap = new HashMap<String, String>();
				while ((lineTxt = bufferedReader.readLine()) != null) {

					newCarmap = new HashMap<String, String>();
					if (count == 0) {
						fields = lineTxt.split("\\:");

					} else {
						fieldValue = lineTxt.split("\\:");
						for (int i = 0; i < fields.length; i++) {
							for (int j = 0; j < fieldValue.length; j++) {
								if (i == j) {
									newCarmap.put(fields[i], fieldValue[j]);

								}
							}
						}
						// ����ȡ��ÿһ�еļ�¼���뵽list������
						newCarlist.add(newCarmap);
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
		return newCarlist;
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
				map = new HashMap<String, String>();
				while ((lineTxt = bufferedReader.readLine()) != null) {

					map = new HashMap<String, String>();
					if (count == 0) {
						fields = lineTxt.split("\\-");

					} else {
						fieldValue = lineTxt.split("\\-");
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
}
