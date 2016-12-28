package cn.ucai.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Test {
	
	static HashMap<String, String> map;
	
	static List<HashMap<String, String>> list;
	public static void main(String[] args) {

		String filePath = "D:/beijing1.txt";
		 list = readTxtFile2(filePath);
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.println("第i条记录" + i + ":" + list.get(i));

		}
		
		
		

	}

	public static List<HashMap<String, String>> readTxtFile2(String filePath) {

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
				map = new HashMap<String, String>();
				while ((lineTxt = bufferedReader.readLine()) != null) {

					map = new HashMap<String, String>();
					if (count == 0) {
						fields = lineTxt.split("\\-");
						// System.out.println("fields:"+fields);
					} else {
						fieldValue = lineTxt.split("\\-");
						// System.out.println("fieldValue:"+fieldValue);
						//System.out.println(fields.length);
						//System.out.println(fieldValue.length);
						for (int i = 0; i < fields.length; i++) {
							for (int j = 0; j < fieldValue.length; j++) {
								if (i == j) {
									map.put(fields[i], fieldValue[j]);
									//System.out.println(map);
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
