package cn.ucai.action;

import java.sql.SQLException;
import java.util.List;

import cn.ucai.db.DriverDao;
import cn.ucai.entity.DriverCar;

public class DriverAction {

	DriverDao dao = new DriverDao();
	
	public List<Integer> matchDriverCar(int addressId, DriverCar type) throws Exception{
		
		return dao.matchDriver(addressId, type);
		
	}
}
