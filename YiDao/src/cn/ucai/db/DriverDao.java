package cn.ucai.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.ucai.entity.DriverCar;

public class DriverDao {

	private static Connection conn = DBUtils.getConnection();
	private static PreparedStatement ps = null;
	private ResultSet rs = null;
	
	public List<Integer> matchDriver(int addrId, DriverCar car) throws SQLException{
		
		String sql = "select * from user where phone=" + addrId;
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery(sql);
		
		List<Integer> uids = new ArrayList<>();
		while(rs.next()){
		uids.add(rs.getInt("uid"));	
			
		}
		return uids;
	}
	
}
