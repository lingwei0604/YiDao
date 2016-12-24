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
			System.out.println("��i����¼" + i + ":" + list.get(i));

		}
		
		
		

	}

	public static List<HashMap<String, String>> readTxtFile2(String filePath) {

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
