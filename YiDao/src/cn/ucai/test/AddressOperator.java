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
//			System.out.println("[0]=���˵�  [1]=��ѯ  [2]=���  [3]=ɾ��  [4]=�޸�  [5]=�˳�");
//			System.out.println("Welcome to YiDao��Please Input Number(above)��");
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
//			default: System.out.println("�����������������룡");
//			}
//		}
//			
//		
//		
//	}
//	public static void check(){  //��ID��Ϊ�ؼ��ֲ�ѯѧ����Ϣ
//		String key=null;
//		try{
//		InputStreamReader isr=new InputStreamReader(System.in); 
//		BufferedReader br=new BufferedReader(isr); 
//		System.out.print("����������ѯ�������ţ�"); 
//		key=br.readLine();
//		}catch(IOException e){
//		System.out.println(e); 
//		}
//		if(map.containsKey(key)){
//		address = (Address)map.get(key); 
//		System.out.println(address.id+" "+address.location); 
//		}
//		else
//		System.out.println("������ѯ�����Ϣ�����ڣ�");
//		}
//}
