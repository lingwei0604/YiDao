package cn.ucai.db;

import java.sql.SQLException;

public class DBtest {

	public static void main(String[] args) throws SQLException {
		System.out.println(DBUtils.getConnection());
		//new DBOperator().getListFromRs();
		new DBDao().readMysqlFile();

	}

	
}
