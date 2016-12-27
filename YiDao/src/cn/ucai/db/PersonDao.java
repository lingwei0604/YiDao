package cn.ucai.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.ucai.entity.Person;

public class PersonDao {

	private static Connection conn = DBUtils.getConnection();
	private static PreparedStatement ps = null;
	private ResultSet rs = null;

	public Person getUserByPhone(String phone) throws Exception {

		String sql = "select * from user where phone=" + phone;
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery(sql);

		if (rs.next()) {
			Person person = new Person();
			person.setUid(rs.getInt("uid"));
			person.setNumber(rs.getString("phone"));
			person.setTime(rs.getString("ciphertext"));
			person.setSalt(rs.getString("salt"));
			person.setIp(rs.getString("register_time"));
			person.setType(rs.getString("ip"));
			return person;
		}
		return null;
	}
	
	
	public List<String> queryAllPhone() throws Exception{
		
		
		String sql = "select phone from person";
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery(sql);
		
		List<String> result = new ArrayList<>();
		while(rs.next()){
			result.add(rs.getString("phone"));
		}
		return result;
		
	}
	public Person getPerson(int uid) throws Exception{
		
		String sql = "select * from person where=uid" + uid;
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery(sql);
		
		if(rs.next()){
			
			Person person = new Person();
			person.setUid(rs.getInt("uid"));
			person.setNumber(rs.getString("phone"));
			person.setTime(rs.getString("ciphertext"));
			person.setSalt(rs.getString("salt"));
			person.setIp(rs.getString("register_time"));
			person.setType(rs.getString("ip"));
			return person;
		}
		return null;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
