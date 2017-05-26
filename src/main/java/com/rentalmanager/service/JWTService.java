package com.rentalmanager.service;

import java.util.HashMap;

import com.rentalmanager.dao.GenericDao;
import com.rentalmanager.entity.database.Login;

public class JWTService {
	private String userId;

	public JWTService(String userId) {
		this.userId = userId;
	}

	public Boolean verifyJWT(String token) {
		GenericDao dao = new GenericDao();
		HashMap<String, Object> filter = new HashMap<String, Object>();
		filter.put("userId", userId);
		filter.put("active", Boolean.TRUE);
		Login login = dao.getEntityByProperty(filter, Login.class);
		return token.equals(login.getToken());
	}
}
