package cn.ucai.test;

public class ReadTxtFile {

//	public static List<HashMap<String, String>> readTxtFile(String filePath) {
//
//		// ����洢��ȡ�������ݼ�¼�ļ���
//		personList = new ArrayList<HashMap<String, String>>();
//		try {
//
//			String encoding = "GB2312";
//			File file = new File(filePath);
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
//				HashMap<String, String> map = new HashMap<String, String>();
//				while ((lineTxt = bufferedReader.readLine()) != null) {
//
//					map = new HashMap<String, String>();
//					if (count == 0) {
//						fields = lineTxt.split("\\:");
//
//					} else {
//						fieldValue = lineTxt.split("\\:");
//						for (int i = 0, row = fields.length; i < row; i++) {
//							for (int j = 0, col = fieldValue.length; j < col; j++) {
//								if (i == j) {
//									map.put(fields[i], fieldValue[j]);
//
//								}
//							}
//						}
//						// ����ȡ��ÿһ�еļ�¼���뵽list������
//						personList.add(map);
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
//		return personList;
//	}

}
