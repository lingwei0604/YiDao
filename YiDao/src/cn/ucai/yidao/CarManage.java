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

	static String typeId = null;
	static String basePrice;
	static List<HashMap<String, String>> carList;
	static HashMap<String, String> carMap;
	
	public static void Main2() throws IOException, SQLException {

		// 从文本文件读取数据
		// String filePath = "D:/car.txt";
		// carlist = readTxtFile(filePath);
		// 从数据库读取数据
		String tabCar = "yd_car";
		carList = ReadFileUtils.findAll(tabCar);
		//carList = ReadFileUtils.readCarMysqlFile();
		selectCarType();

	}

	public static void selectCarType() throws IOException {

		String keyLike = null;
		Map<String, Object> careMap = new ConcurrentHashMap<String, Object>();
		List carresult = new ArrayList();
		System.out.println("请选择您的车型");
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		keyLike = br.readLine();

		for (int i = 0; i < carList.size(); i++) {
			careMap = (HashMap) carList.get(i);
			Iterator<String> itera = careMap.keySet().iterator();
			Iterator<Object> iterb = careMap.values().iterator();

			while (itera.hasNext() && iterb.hasNext()) {

				// 因为迭代器每次会移动一个位置，指针移动一位，用一个临时变量来保存每次的值
				String cartemp = (String) iterb.next();
				// 找出键值对，值以key开头的键值对，并遍历
				if (cartemp.startsWith(keyLike)) {
					// System.out.println(itera.next() + "  " + carlist.get(i));
					System.out.println(carList.get(i));
				}

			}
		}

		System.out.println(Constants.IMPUT_ID);
		initCar();
	}

	

	public static void initCar() {
		String key = null;
		Map<String, String> caremp = new HashMap<String, String>();
		Scanner sc = new Scanner(System.in);
		key = sc.next();
		for (int i = 0; i < carList.size(); i++) {
			caremp = (HashMap) carList.get(i);

			if (caremp.containsValue(key)) {
				System.out.println(carList.get(i));
				System.out.println(carList.get(i).values());
				HashMap carlocations = carList.get(i);
				String cartypeid = (String) carlocations.get("subtype");
				typeId = (String) carlocations.get("car_id");
				System.out.println(typeId);
				basePrice = (String) carlocations.get("baseprice");
				String timeprice = (String) carlocations.get("timeprice");
				// System.out.println(locations.get("typeid"));
				System.out.println("您的车子是:" + cartypeid + "!起步价为：" + basePrice
						+ "时长价为：" + timeprice);
			}
		}

	}


}
