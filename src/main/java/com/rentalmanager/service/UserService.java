package com.rentalmanager.service;

import com.rentalmanager.dao.UserDao;
import com.rentalmanager.entity.Users;

public class UserService {
	private UserDao dao;

	public UserService() {
		dao = new UserDao();
	}

	public Users getUser(String email) {
		return dao.getUser(email);
	}
}