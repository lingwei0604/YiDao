package cn.ucai.yidao;

public class OutAddress {
	private String startaddress;
	private String endaddress;
	private String time;

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
