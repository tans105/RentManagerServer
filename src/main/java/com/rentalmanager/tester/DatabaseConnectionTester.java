package com.rentalmanager.tester;

import java.util.HashMap;
import java.util.Map;

import com.rentalmanager.dao.GenericDao;
import com.rentalmanager.entity.database.Users;

public class DatabaseConnectionTester {
	public static void main(String[] args) {
		GenericDao dao=new GenericDao();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("email", "tanmayawasthi105@gmail.com");
		System.out.println(dao.getEntityByProperty(map, Users.class).getPassword());
		
	}
}
