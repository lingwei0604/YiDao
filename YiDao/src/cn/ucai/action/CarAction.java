package cn.ucai.action;

import java.sql.SQLException;
import java.util.List;

import cn.ucai.db.CarDao;
import cn.ucai.entity.DriverCar;

public class CarAction {

	CarDao dao = new CarDao();
	
	public List<String> queryMode(String brand) throws SQLException{
		
		return dao.queryModel(brand);
	}
	
	public DriverCar getCar(String brand, String model) throws SQLException{
		return dao.getCar(model);
	}
}
