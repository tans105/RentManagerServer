package com.rentmanager.tester;

import java.util.Date;

import com.rentmanager.dao.UserManagementDao;
import com.rentmanager.entity.database.Login;

public class CustomUserIdTester {
	public static void main(String[] args) {
		Login login = new Login();
		login.setHostelId("SSHHYDTEL0001");
		login.setPassword("5f4dcc3b5aa765d61d8327deb882cf99");
		login.setLastLoginDtm(new Date());
		login.setRoleId(2);
		UserManagementDao dao=new UserManagementDao();
		System.out.println("---------------------------------------------------"+dao.saveEntity(login));

	}
}
