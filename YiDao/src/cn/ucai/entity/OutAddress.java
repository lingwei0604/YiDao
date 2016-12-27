package cn.ucai.entity;

public class OutAddress {
	private String startaddress;
	private String endaddress;
	private String time;
	private int uid;
    private int drivercarid;
    private float totalprice;
    
	public float getTotalprice() {
		return totalprice;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public OutAddress(int uid, int newcarphone, String startaddress,
			String endAddress2, float totalprice) {
		super();
		this.uid=uid;
		this.startaddress = startaddress;
		this.endaddress = endaddress;
		this.time = time;
		this.drivercarid = newcarphone;
		this.totalprice = totalprice;
	}

	public void setTotalprice(float totalprice) {
		this.totalprice = totalprice;
	}

	public OutAddress(String startaddress, String endaddress, String time,
			int newcarphone) {
		super();
		this.startaddress = startaddress;
		this.endaddress = endaddress;
		this.time = time;
		this.drivercarid = newcarphone;
	}

	public int getDrivercarid() {
		return drivercarid;
	}

	public void setDrivercarid(int drivercarid) {
		this.drivercarid = drivercarid;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public OutAddress(String startaddress, String endaddress, String time) {
		this.startaddress = startaddress;
		this.endaddress = endaddress;
		this.time = time;
	}

	public String getStartaddress() {
		return startaddress;
	}

	public void setStartaddress(String startaddress) {
		this.startaddress = startaddress;
	}

	public String getEndaddress() {
		return endaddress;
	}

	public void setEndaddress(String endaddress) {
		this.endaddress = endaddress;
	}
}
