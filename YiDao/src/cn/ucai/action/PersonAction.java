package cn.ucai.action;

import java.util.List;

import cn.ucai.db.PersonDao;
import cn.ucai.entity.Person;

public class PersonAction {

	
	PersonDao dao = new PersonDao();
	
	
	public List<String> queryAllPhone() throws Exception{
		 return dao.queryAllPhone();
	}
	
	public Person getUerByPhone(String phone) throws Exception{
		return dao.getUserByPhone(phone);
	}
	
	public Person getPerson(int id) throws Exception{
		return dao.getPerson(id);
	}
	
	
}
