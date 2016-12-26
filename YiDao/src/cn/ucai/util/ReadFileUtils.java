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
		// 定义存储读取到的数据记录的集合
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String sql = "select * from yd_address";
		ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();// 得到结果集列的属性
		int count = rsmd.getColumnCount();// 得到记录有多少列
		// 之前写到这里，list中的都是多条同样的数据，而放下面就可以解决这个问题
		while (rs.next()) {
			HashMap<String, String> map = new HashMap<String, String>();
			for (int i = 1; i < count + 1; i++) {
				// 获取指定列的表目录名称
				String label = rsmd.getColumnLabel(i);
				// 以 Java 编程语言中 Object 的形式获取此 ResultSet 对象的当前行中指定列的值
				Object object = rs.getObject(i);
				// 把数据库中的字段名和值对应为一个map对象中的一个键值对
				map.put(label.toLowerCase(), String.valueOf(object));
				// System.out.println(map);
				// 把每条对象封装成的map对象放进list中

			}
			list.add(map);

		}
		return list;
	}
	public static List<HashMap<String, String>> readPersonMysqlFile()
			throws SQLException {
		// 定义存储读取到的数据记录的集合
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String sql = "select * from yd_person";
		ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();// 得到结果集列的属性
		int count = rsmd.getColumnCount();// 得到记录有多少列
		// 之前写到这里，list中的都是多条同样的数据，而放下面就可以解决这个问题
		while (rs.next()) {
			HashMap<String, String> map = new HashMap<String, String>();
			for (int i = 1; i < count + 1; i++) {
				// 获取指定列的表目录名称
				String label = rsmd.getColumnLabel(i);
				// 以 Java 编程语言中 Object 的形式获取此 ResultSet 对象的当前行中指定列的值
				Object object = rs.getObject(i);
				// 把数据库中的字段名和值对应为一个map对象中的一个键值对
				map.put(label.toLowerCase(), String.valueOf(object));
				// System.out.println(map);
				// 把每条对象封装成的map对象放进list中

			}
			list.add(map);

		}
		return list;
	}
	
	public static List<HashMap<String, String>> readDriverMysqlFile()
			throws SQLException {
		// 定义存储读取到的数据记录的集合
		List<HashMap<String, String>> list  = new ArrayList<HashMap<String, String>>();
		String sql = "select * from driver ";
		ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();// 得到结果集列的属性
		int count = rsmd.getColumnCount();// 得到记录有多少列
		while (rs.next()) {

			HashMap<String, String> map = new HashMap<String, String>();
			for (int i = 1; i < count + 1; i++) {
				// 获取指定列的表目录名称
				String label = rsmd.getColumnLabel(i);
				// 以 Java 编程语言中 Object 的形式获取此 ResultSet 对象的当前行中指定列的值
				Object object = rs.getObject(i);
				// 把数据库中的字段名和值对应为一个map对象中的一个键值对
				map.put(label.toLowerCase(), String.valueOf(object));
				// System.out.println(map);
				// 把每条对象封装成的map对象放进list中

			}
			list.add(map);

		}
		//System.out.println(list);
		return list;
	}
	public static List<HashMap<String, String>> readDriverCarMysqlFile()
			throws SQLException {
		// 定义存储读取到的数据记录的集合
		List<HashMap<String, String>> list  = new ArrayList<HashMap<String, String>>();
		String sql = "select * from yd_driver_dist";
		ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();// 得到结果集列的属性
		int count = rsmd.getColumnCount();// 得到记录有多少列
		while (rs.next()) {

			HashMap<String, String> map = new HashMap<String, String>();
			for (int i = 1; i < count + 1; i++) {
				// 获取指定列的表目录名称
				String label = rsmd.getColumnLabel(i);
				// 以 Java 编程语言中 Object 的形式获取此 ResultSet 对象的当前行中指定列的值
				Object object = rs.getObject(i);
				// 把数据库中的字段名和值对应为一个map对象中的一个键值对
				map.put(label.toLowerCase(), String.valueOf(object));
				// System.out.println(map);
				// 把每条对象封装成的map对象放进list中

			}
			list.add(map);

		}
		System.out.println(list);
		return list;
	}

	public static List<HashMap<String, String>> readCarMysqlFile()
			throws SQLException {
		// 定义存储读取到的数据记录的集合
		List<HashMap<String, String>> list  = new ArrayList<HashMap<String, String>>();
		String sql = "select * from yd_car ";
		ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();// 得到结果集列的属性
		int count = rsmd.getColumnCount();// 得到记录有多少列
		while (rs.next()) {

			HashMap<String, String> map = new HashMap<String, String>();
			for (int i = 1; i < count + 1; i++) {
				// 获取指定列的表目录名称
				String label = rsmd.getColumnLabel(i);
				// 以 Java 编程语言中 Object 的形式获取此 ResultSet 对象的当前行中指定列的值
				Object object = rs.getObject(i);
				// 把数据库中的字段名和值对应为一个map对象中的一个键值对
				map.put(label.toLowerCase(), String.valueOf(object));
				// System.out.println(map);
				// 把每条对象封装成的map对象放进list中

			}
			list.add(map);

		}
		//System.out.println(list);
		return list;
	}
	
	
	
	public static List<HashMap<String, String>> readTxtFile(String filePath) {

		// 定义存储读取到的数据记录的集合
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		try {

			String encoding = "GB2312";
			File file = new File(filePath);
			// 判断文件是否存在
			if (file.isFile() && file.exists()) {
				// 考虑到编码格式
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				// 记录读取的数据文件的行数
				int count = 0;
				// 定义字段的数组
				String[] fields = null;
				// 定义每条记录取出的字段值数组
				String[] fieldValue = null;
				// 定义Map集合
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
						// 将读取的每一行的记录存入到list集合中
						list.add(map);
					}

					count++;
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		return list;
	}
}
