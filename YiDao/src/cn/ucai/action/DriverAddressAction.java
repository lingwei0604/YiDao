package cn.ucai.action;

import cn.ucai.db.AddressDao;
import cn.ucai.db.DriverAddressDao;
import cn.ucai.entity.OutAddress;

public class DriverAddressAction {

	DriverAddressDao dao = new DriverAddressDao();
	
	public void addAddr(OutAddress outAddr){
		
		dao.add(outAddr);
		
	}
}
