//package cn.ucai.test;
//
//import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.ObjectInputStream;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.TreeMap;
//
//import cn.ucai.yidao.Address;
//
//public class AddressOperator {
//
//	static Address address;
//	
//	//static TreeMap<String,Address> tm = new TreeMap<String, Address>();
//	static Map<String,Address> map = new HashMap<String,Address>();
//	public static void main(String [] args) {
//		try{
//		FileInputStream fis = new FileInputStream("D:/beijing.txt");
//		ObjectInputStream ios = new ObjectInputStream(fis);
//		while((address = (Address)(ios.readObject()))!=null ){
//			map.put(address.id,address);
//			System.out.println(address.id);
//		}
//		ios.close();
//		}catch(Exception e){
//			//System.out.println(e);
//		}
//		
//		System.out.println(address);
//		while(true){
//			System.out.println("[0]=主菜单  [1]=查询  [2]=添加  [3]=删除  [4]=修改  [5]=退出");
//			System.out.println("Welcome to YiDao，Please Input Number(above)：");
//			InputStreamReader isr = new InputStreamReader(System.in);
//			BufferedReader br = new BufferedReader(isr);
//			String str = null;
//			try {
//				str = br.readLine();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			int temp = Integer.parseInt(str);
//			switch(temp){
//			case 0:break;
//			case 1:check();break;
//			//case 2:add();break;
//			//case 3:delete();break;
//			//case 4:change();break;
//			case 5:map.clear();System.exit(0);break;
//			default: System.out.println("操作有误，请重新输入！");
//			}
//		}
//			
//		
//		
//	}
//	public static void check(){  //以ID作为关键字查询学生信息
//		String key=null;
//		try{
//		InputStreamReader isr=new InputStreamReader(System.in); 
//		BufferedReader br=new BufferedReader(isr); 
//		System.out.print("请输入所查询的区域遍号："); 
//		key=br.readLine();
//		}catch(IOException e){
//		System.out.println(e); 
//		}
//		if(map.containsKey(key)){
//		address = (Address)map.get(key); 
//		System.out.println(address.id+" "+address.location); 
//		}
//		else
//		System.out.println("您所查询编号信息不存在！");
//		}
//}
