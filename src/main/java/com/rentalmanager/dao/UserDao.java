package com.rentalmanager.dao;

import java.util.HashMap;

import com.rentalmanager.entity.database.RoleMst;
import com.rentalmanager.entity.database.Users;

public class UserDao {
	private GenericDao dao;

	public UserDao() {
		dao = new GenericDao();
	}

	public Users getUser(String email) {
		HashMap<String, Object> filter = new HashMap<String, Object>();
		filter.put("email", email);
		filter.put("active", Boolean.TRUE);
		return dao.getEntityByProperty(filter, Users.class);
	}

	public RoleMst getRole(Integer roleId) {
		HashMap<String, Object> filter = new HashMap<String, Object>();
		filter.put("roleId", roleId);
		return dao.getEntityByProperty(filter, RoleMst.class);
	}

}
