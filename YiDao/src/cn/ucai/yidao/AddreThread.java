package cn.ucai.yidao;

import java.sql.SQLException;

public class AddreThread extends Thread {

	AddreManage addrOperator = new AddreManage();

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {

			addrOperator.match();
			Thread.sleep(2000);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
