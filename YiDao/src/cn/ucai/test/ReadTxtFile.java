package cn.ucai.test;

public class ReadTxtFile {

//	public static List<HashMap<String, String>> readTxtFile(String filePath) {
//
//		// 定义存储读取到的数据记录的集合
//		personList = new ArrayList<HashMap<String, String>>();
//		try {
//
//			String encoding = "GB2312";
//			File file = new File(filePath);
//			// 判断文件是否存在
//			if (file.isFile() && file.exists()) {
//				// 考虑到编码格式
//				InputStreamReader read = new InputStreamReader(
//						new FileInputStream(file), encoding);
//				BufferedReader bufferedReader = new BufferedReader(read);
//				String lineTxt = null;
//				// 记录读取的数据文件的行数
//				int count = 0;
//				// 定义字段的数组
//				String[] fields = null;
//				// 定义每条记录取出的字段值数组
//				String[] fieldValue = null;
//				// 定义Map集合
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
//						// 将读取的每一行的记录存入到list集合中
//						personList.add(map);
//					}
//
//					count++;
//				}
//				read.close();
//			} else {
//				System.out.println("找不到指定的文件");
//			}
//		} catch (Exception e) {
//			System.out.println("读取文件内容出错");
//			e.printStackTrace();
//		}
//		return personList;
//	}

}
