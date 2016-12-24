package cn.ucai.yidao;

public class DriverCar {

	private String userid;
	private String cartype;
	public DriverCar(String userid, String cartype) {
		super();
		this.userid = userid;
		this.cartype = cartype;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getCartype() {
		return cartype;
	}
	public void setCartype(String cartype) {
		this.cartype = cartype;
	}
	
}
