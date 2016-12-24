package cn.ucai.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	public static final String DBDRIVER="org.gjt.mm.mysql.Driver";//mysql数据库驱动
	public static final String DBURL="jdbc:mysql://localhost:3306/yidao"; //数据库URL
	public static final String DBUSER="root";
	public static final String DBPASS="root";
	private static Connection conn = null; 
	
	//静态块
		static{
			try {
				Class.forName(DBDRIVER);   //加载驱动
				conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public DBUtils() {
			System.out.println("构造方法");
		}
		
		public static Connection getConnection(){
			return conn; //静态方法只能访问静态变量
		}
		
		public void close() {
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}


