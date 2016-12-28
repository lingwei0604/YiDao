package cn.ucai.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.ucai.entity.Driver;
import cn.ucai.entity.DriverCar;
import cn.ucai.entity.OutAddress;
import cn.ucai.entity.Person;

public class DBDao {

	private static Connection conn = DBUtils.getConnection();
	private static PreparedStatement ps = null;
	private ResultSet rs = null;

	// 插入一条记录
	public static boolean addPerson(Person per) throws Exception {
		boolean flag = false;
		String sql = "insert into yd_person values(?,?,?,?,?,?,?)";
		ps = conn.prepareStatement(sql);
		ps.setLong(1, per.getUid());
		ps.setString(2, per.getNumber());
		ps.setString(3, per.getTime());
		ps.setString(4, per.getPassword());
		ps.setString(5, per.getSalt());
		ps.setString(6, per.getIp());
		ps.setString(7, per.getType());
		if (ps.executeUpdate() > 0) {
			flag = true;
		}
		return flag;
	}

	// 插入一条记录
	public static boolean addDriver(Driver driver) throws Exception {
		boolean flag = false;
		String sql = "insert into driver values(?,?,?,?,?,?,?)";
		ps = conn.prepareStatement(sql);
		ps.setString(1, driver.getPhone());
		ps.setString(2, driver.getPassword());
		ps.setString(3, driver.getTime());
		ps.setString(4, driver.getSalt());
		ps.setString(5, driver.getType());
		ps.setString(6, driver.getIp());
		ps.setString(7, driver.getToaddress());
		System.out.println(driver.getToaddress());
		if (ps.executeUpdate() > 0) {
			flag = true;
		}
		return flag;
	}

	public static boolean addFromTo(DriverCar drivercar) throws Exception {
		boolean flag = false;
		String sql = "insert into yd_driver_dist values(?,?)";
		ps = conn.prepareStatement(sql);
		ps.setString(1, drivercar.getUserid());
		ps.setString(2, drivercar.getCartype());
		if (ps.executeUpdate() > 0) {
			flag = true;
		}
		return flag;
	}
	public static boolean addJourney(OutAddress outaddress) throws Exception {
		boolean flag = false;
		String sql = "insert into yd_journey values(?,?,?,?,?)";
		ps = conn.prepareStatement(sql);
		ps.setLong(1, outaddress.getUid());
		ps.setLong(2, outaddress.getDrivercarid());
		ps.setString(3, outaddress.getStartaddress());
		ps.setString(4, outaddress.getEndaddress());
		ps.setFloat(5, outaddress.getTotalprice());
		if (ps.executeUpdate() > 0) {
			flag = true;
		}
		return flag;
	}


	
}
