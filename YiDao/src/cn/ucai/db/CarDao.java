package cn.ucai.db;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.ucai.entity.DriverCar;

public class CarDao {

	private static Connection conn = DBUtils.getConnection();
	private static PreparedStatement ps = null;
	private ResultSet rs = null;

	public List<String> queryModel(String brand) throws SQLException {

		String sql = "select * from user where brand=" + brand;
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery(sql);

		List<String> models = new ArrayList<>();

		while (rs.next()) {
			models.add(rs.getString("type"));
		}

		return models;
	}
	
	
	public DriverCar getCar(String model) throws SQLException{
		
		String sql = "select * from car where model='" + model + "'";
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery(sql);

		if (rs.next()) {
            DriverCar drivercar = new DriverCar();
            drivercar.setUserid(rs.getString("uid"));
            drivercar.setCartype(rs.getString("cartype"));
            return drivercar;
        }
		return null;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
