package cn.ucai.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.ucai.db.DBUtils;

public class ReadFileUtils {

	
	private static Connection conn = DBUtils.getConnection();
	private static PreparedStatement ps = null;
	private ResultSet rs = null;
	
	
	public static List<HashMap<String, String>> readAddressMysqlFile()
			throws SQLException {
		// ����洢��ȡ�������ݼ�¼�ļ���
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String sql = "select * from yd_address";
		ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();// �õ�������е�����
		int count = rsmd.getColumnCount();// �õ���¼�ж�����
		// ֮ǰд�����list�еĶ��Ƕ���ͬ�������ݣ���������Ϳ��Խ���������
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
			list.add(map);

		}
		return list;
	}
	public static List<HashMap<String, String>> readPersonMysqlFile()
			throws SQLException {
		// ����洢��ȡ�������ݼ�¼�ļ���
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String sql = "select * from yd_person";
		ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();// �õ�������е�����
		int count = rsmd.getColumnCount();// �õ���¼�ж�����
		// ֮ǰд�����list�еĶ��Ƕ���ͬ�������ݣ���������Ϳ��Խ���������
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
			list.add(map);

		}
		return list;
	}
	
	public static List<HashMap<String, String>> readDriverMysqlFile()
			throws SQLException {
		// ����洢��ȡ�������ݼ�¼�ļ���
		List<HashMap<String, String>> list  = new ArrayList<HashMap<String, String>>();
		String sql = "select * from driver ";
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
			list.add(map);

		}
		//System.out.println(list);
		return list;
	}
	public static List<HashMap<String, String>> readDriverCarMysqlFile()
			throws SQLException {
		// ����洢��ȡ�������ݼ�¼�ļ���
		List<HashMap<String, String>> list  = new ArrayList<HashMap<String, String>>();
		String sql = "select * from yd_driver_dist";
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
			list.add(map);

		}
		System.out.println(list);
		return list;
	}

	public static List<HashMap<String, String>> readCarMysqlFile()
			throws SQLException {
		// ����洢��ȡ�������ݼ�¼�ļ���
		List<HashMap<String, String>> list  = new ArrayList<HashMap<String, String>>();
		String sql = "select * from yd_car ";
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
			list.add(map);

		}
		//System.out.println(list);
		return list;
	}
	
	
	
	public static List<HashMap<String, String>> readTxtFile(String filePath) {

		// ����洢��ȡ�������ݼ�¼�ļ���
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
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
