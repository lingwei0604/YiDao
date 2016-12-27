package cn.ucai.test;

import java.sql.SQLException;

import cn.ucai.db.DBDao;
import cn.ucai.db.DBUtils;

public class DBtest {

	public static void main(String[] args) throws SQLException {
		System.out.println(DBUtils.getConnection());
		//new DBOperator().getListFromRs();
		new DBDao().readMysqlFile();

	}

	
}
