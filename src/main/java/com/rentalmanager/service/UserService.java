package com.rentalmanager.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.rentalmanager.constants.Constants;
import com.rentalmanager.dao.UserDao;
import com.rentalmanager.entity.RoleMst;
import com.rentalmanager.entity.Users;
import com.rentalmanager.tester.JsonFileRead;

public class UserService {
	private UserDao dao;

	public UserService() {
		dao = new UserDao();
	}

	public Users getUser(String email) {
		return dao.getUser(email);
	}

	public RoleMst getRole(Integer roleId) {
		return dao.getRole(roleId);
	}

	public List<String> readModuleList(Integer roleId) {
		List<String> moduleList = new LinkedList<String>();
		ClassLoader classLoader = new JsonFileRead().getClass().getClassLoader();
		File file = new File(classLoader.getResource(Constants.MODULE_JSON_PATH).getFile());
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(new FileReader(file));
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray roleArray = (roleId == 1) ? (JSONArray) jsonObject.get(Constants.ROLE_ADMIN) : (JSONArray) jsonObject.get(Constants.ROLE_TENANT);
			@SuppressWarnings("unchecked")
			Iterator<String> iterator = roleArray.iterator();
			while (iterator.hasNext()) {
				moduleList.add(iterator.next());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return moduleList;
	}
}
