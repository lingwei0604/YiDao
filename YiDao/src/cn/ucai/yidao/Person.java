package cn.ucai.yidao;

public class Person {

	private int uid;
	private String number;
	private String password;
	private String salt;
	private String time;
	private String ip;
	private String type;

	public int getUid() {
		return uid;
	}

	public Person(int uid, String number, String password, String salt,
			String time, String ip, String type) {
		super();
		this.uid = uid;
		this.number = number;
		this.password = password;
		this.salt = salt;
		this.time = time;
		this.ip = ip;
		this.type = type;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Person(String number, String password, String salt, String time,
			String ip, String type) {
		super();
		this.number = number;
		this.password = password;
		this.salt = salt;
		this.time = time;
		this.ip = ip;
		this.type = type;
	}
}
