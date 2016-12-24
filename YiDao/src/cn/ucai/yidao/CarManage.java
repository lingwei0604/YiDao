package cn.ucai.yidao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import cn.ucai.db.DBUtils;
import cn.ucai.util.Constants;
import cn.ucai.util.ReadFileUtils;

public class CarManage {

	static String typeid = null;
	static List<HashMap<String, String>> carlist;
	static HashMap<String, String> carmap;

	private static Connection conn = DBUtils.getConnection();
	private static PreparedStatement ps = null;
	private ResultSet rs = null;

	public static void Main2() throws IOException, SQLException {

		// ���ı��ļ���ȡ����
		// String filePath = "D:/car.txt";
		// carlist = readTxtFile(filePath);
		// �����ݿ��ȡ����
		carlist = ReadFileUtils.readCarMysqlFile();
		selectCarType();

	}

	public static void selectCarType() throws IOException {

		String keylike = null;
		Map<String, Object> caremap = new ConcurrentHashMap<String, Object>();
		List carresult = new ArrayList();
		System.out.println("��ѡ�����ĳ���");
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		keylike = br.readLine();

		for (int i = 0; i < carlist.size(); i++) {
			caremap = (HashMap) carlist.get(i);
			Iterator<String> itera = caremap.keySet().iterator();
			Iterator<Object> iterb = caremap.values().iterator();

			while (itera.hasNext() && iterb.hasNext()) {

				// ��Ϊ������ÿ�λ��ƶ�һ��λ�ã�ָ���ƶ�һλ����һ����ʱ����������ÿ�ε�ֵ
				String cartemp = (String) iterb.next();
				// �ҳ���ֵ�ԣ�ֵ��key��ͷ�ļ�ֵ�ԣ�������
				if (cartemp.startsWith(keylike)) {
					// System.out.println(itera.next() + "  " + carlist.get(i));
					System.out.println(carlist.get(i));
				}

			}
		}

		System.out.println(Constants.IMPUT_ID);
		initCar();
	}

	static String baseprice;

	public static void initCar() {
		String key = null;
		Map<String, String> caremp = new HashMap<String, String>();
		Scanner sc = new Scanner(System.in);
		key = sc.next();
		for (int i = 0; i < carlist.size(); i++) {
			caremp = (HashMap) carlist.get(i);

			if (caremp.containsValue(key)) {
				System.out.println(carlist.get(i));
				System.out.println(carlist.get(i).values());
				HashMap carlocations = carlist.get(i);
				String cartypeid = (String) carlocations.get("subtype");
				typeid = (String) carlocations.get("typeid");
				System.out.println(typeid);
				baseprice = (String) carlocations.get("baseprice");
				String timeprice = (String) carlocations.get("timeprice");
				// System.out.println(locations.get("typeid"));
				System.out.println("���ĳ�����:" + cartypeid + "!�𲽼�Ϊ��" + baseprice
						+ "ʱ����Ϊ��" + timeprice);
			}
		}

	}

	public static List<HashMap<String, String>> readCarMysqlFile()
			throws SQLException {
		// ����洢��ȡ�������ݼ�¼�ļ���
		carlist = new ArrayList<HashMap<String, String>>();
		String sql = "select * from car ";
		ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();// �õ�������е�����
		int count = rsmd.getColumnCount();// �õ���¼�ж�����

		while (rs.next()) {

			HashMap<String, String> map = new HashMap<String, String>();
			for (int i = 1; i < count + 1; i++) {
				// ��ȡָ���еı�Ŀ¼����
				String label = rsmd.getColumnLabel(i);
				// �� Java ��������� Object ����ʽ��ȡ�� ResultSet ����ĵ�ǰ����ָ���е�ֵ
				Object object = rs.getObject(i);
				// �����ݿ��е��ֶ�����ֵ��ӦΪһ��map�����е�һ����ֵ��
				map.put(label.toLowerCase(), String.valueOf(object));
				// System.out.println(map);
				// ��ÿ�������װ�ɵ�map����Ž�list��

			}
			carlist.add(map);

		}
		System.out.println(carlist);
		return carlist;
	}

}
