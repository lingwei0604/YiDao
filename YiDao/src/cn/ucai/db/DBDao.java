package cn.ucai.db;

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
import java.util.Map;

import cn.ucai.yidao.Driver;
import cn.ucai.yidao.DriverCar;
import cn.ucai.yidao.OutAddress;
import cn.ucai.yidao.Person;

public class DBDao {

	private static Connection conn = DBUtils.getConnection();
	private static PreparedStatement ps = null;
	private ResultSet rs = null;

	// ����һ����¼
	public static boolean doInsertPerson(Person per) throws Exception {
		boolean flag = false;
		String sql = "insert into person values(?,?,?,?,?,?)";
		ps = conn.prepareStatement(sql);
		ps.setString(1, per.getNumber());
		ps.setString(2, per.getTime());
		ps.setString(3, per.getPassword());
		ps.setString(4, per.getSalt());
		ps.setString(5, per.getIp());
		ps.setString(6, per.getType());
		if (ps.executeUpdate() > 0) {
			flag = true;
		}
		return flag;
	}

	// ����һ����¼
	public static boolean doInsertDriver(Driver driver) throws Exception {
		boolean flag = false;
		String sql = "insert into driver values(?,?,?,?,?,?,?)";
		ps = conn.prepareStatement(sql);
		ps.setString(1, driver.getPhone());
		ps.setString(2, driver.getPassword());
		ps.setString(3, driver.getTime());
		ps.setString(4, driver.getSalt());
		ps.setString(5, driver.getType());
		ps.setString(6, driver.getIp());
		ps.setString(7, driver.getToaddress());
		System.out.println(driver.getToaddress());
		if (ps.executeUpdate() > 0) {
			flag = true;
		}
		return flag;
	}

	public static boolean doInsertFromTo(DriverCar drivercar) throws Exception {
		boolean flag = false;
		String sql = "insert into drivercar values(?,?)";
		ps = conn.prepareStatement(sql);
		ps.setString(1, drivercar.getCartype());
		ps.setString(2, drivercar.getUserid());
		if (ps.executeUpdate() > 0) {
			flag = true;
		}
		return flag;
	}
	public static boolean doInsertJourney(OutAddress outaddress) throws Exception {
		boolean flag = false;
		String sql = "insert into journey values(?,?,?)";
		ps = conn.prepareStatement(sql);
		ps.setString(1, outaddress.getStartaddress());
		ps.setString(2, outaddress.getEndaddress());
		ps.setString(3, outaddress.getTime());
		if (ps.executeUpdate() > 0) {
			flag = true;
		}
		return flag;
	}
	
	
	// public List<Map<String, Object>> getListFromRs() throws SQLException {
	// String sql = "select * from person ";
	// ps = conn.prepareStatement(sql);
	// ResultSet rs = ps.executeQuery();
	// ResultSetMetaData rsmd = rs.getMetaData();
	// int count = rsmd.getColumnCount();
	// Map<String, Object> map = new HashMap<String, Object>();
	// List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	// while(rs.next()){
	//
	// for (int i = 1; i < count; i++) {
	// // ��ȡָ���еı�Ŀ¼����
	// String label = rsmd.getColumnLabel(i);
	// // �� Java ��������� Object ����ʽ��ȡ�� ResultSet ����ĵ�ǰ����ָ���е�ֵ
	// Object object = rs.getObject(i);
	// // �����ݿ��е��ֶ�����ֵ��ӦΪһ��map�����е�һ����ֵ��
	// map.put(label.toLowerCase(), object);
	// // ��ÿ�������װ�ɵ�map����Ž�list��
	// list.add(map);
	// }
	//
	//
	//
	// }
	// System.out.println(list);
	// return list;
	// }
	static List<HashMap<String, String>> list;

	public static List<Map<String, String>> readMysqlFile() throws SQLException {

		// ����洢��ȡ�������ݼ�¼�ļ���
		list = new ArrayList<HashMap<String, String>>();
		String sql = "select * from person ";
		ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		Map<String, String> map = new HashMap<String, String>();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		while (rs.next()) {

			for (int i = 1; i < count; i++) {
				// ��ȡָ���еı�Ŀ¼����
				String label = rsmd.getColumnLabel(i);
				// �� Java ��������� Object ����ʽ��ȡ�� ResultSet ����ĵ�ǰ����ָ���е�ֵ
				Object object = rs.getObject(i);
				// �����ݿ��е��ֶ�����ֵ��ӦΪһ��map�����е�һ����ֵ��
				map.put(label.toLowerCase(), String.valueOf(object));
				// ��ÿ�������װ�ɵ�map����Ž�list��

			}
			list.add(map);
		}
		// System.out.println(list);
		return list;
	}
}
