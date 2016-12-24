package cn.ucai.yidao;

public class Driver {
	private String phone;
	private String password;
	private String time;
	private String type;
	private String ip;
	private String toaddress;
	private String salt;
	// CarManage car;

	private String carmodel;
	private String typemodel;

	String price;

	public String getToaddress() {
		return toaddress;
	}

	public void setToaddress(String toaddress) {
		this.toaddress = toaddress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Driver(String phone, String password, String time, String salt,
			String type, String ip, String toaddress) {
		super();
		this.phone = phone;
		this.password = password;
		this.salt = salt;
		this.time = time;
		this.type = type;
		this.ip = ip;
		this.toaddress = toaddress;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Driver(String phone, String password, String time, String type,
			String ip, String toaddress, String salt, String carmodel,
			String typemodel, String price) {
		super();
		this.phone = phone;
		this.password = password;
		this.time = time;
		this.type = type;
		this.ip = ip;
		this.toaddress = toaddress;
		this.salt = salt;
		this.carmodel = carmodel;
		this.typemodel = typemodel;
		this.price = price;
	}
}
