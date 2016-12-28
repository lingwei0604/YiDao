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

	public OutAddress(int i, int newCarPhone, String startAddress2,
			String endAddress2, float totalPrice2) {
		// TODO Auto-generated constructor stub
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
