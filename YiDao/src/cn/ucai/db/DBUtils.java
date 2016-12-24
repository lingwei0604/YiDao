package cn.ucai.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	public static final String DBDRIVER="org.gjt.mm.mysql.Driver";//mysql���ݿ�����
	public static final String DBURL="jdbc:mysql://localhost:3306/yidao"; //���ݿ�URL
	public static final String DBUSER="root";
	public static final String DBPASS="root";
	private static Connection conn = null; 
	
	//��̬��
		static{
			try {
				Class.forName(DBDRIVER);   //��������
				conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public DBUtils() {
			System.out.println("���췽��");
		}
		
		public static Connection getConnection(){
			return conn; //��̬����ֻ�ܷ��ʾ�̬����
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


