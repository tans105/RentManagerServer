package com.rentmanager.tester;

import com.rentmanager.dao.ProfileManagementDao;

/**
 * @author : tanmay
 * @created : 06-Jun-2017
 */
public class DaoTester {

	public static void main(String[] args) {
		ProfileManagementDao dao=new ProfileManagementDao();
		dao.getStateMst();
	}
}
