package com.rentalmanager.dao;

import java.util.HashMap;

import com.rentalmanager.entity.Users;

public class UserDao {
	private GenericDao dao;

	public UserDao() {
		dao = new GenericDao();
	}

	public Users getUser(String email) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		return dao.getEntityByProperty(map, Users.class);
	}

}
