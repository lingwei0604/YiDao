package cn.ucai.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.ucai.entity.Address;

public class AddressDao {

	private static Connection conn = DBUtils.getConnection();
	private static PreparedStatement ps = null;
	private ResultSet rs = null;

	public List<Address> query() throws Exception {

		String sql = "select * from address";
		ps = conn.prepareStatement(sql);
		try {

			rs = ps.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<>();

	}
	
	public Address queryId(String id) throws Exception{
		
		String sql = "select * from address  where id ="+id;
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery(sql);
		return resultList(rs).get(0);
		
	}
	
	
	private List<Address> resultList(ResultSet set) throws Exception{
		List<Address> result = new ArrayList<>();
		
		Address address;
		
		while(set.next()){
			address = new Address();
			address.setArea(set.getString("addressId"));
			address.setId(set.getString("id"));
			address.setLocation(set.getString(""));
			result.add(address);
			
		}
		return result;
	}
}
