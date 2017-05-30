package com.rentmanager.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.rentmanager.constants.Constants;
import com.rentmanager.dao.GenericDao;
import com.rentmanager.dao.LoginDao;
import com.rentmanager.entity.Module;
import com.rentmanager.entity.database.HostelMst;
import com.rentmanager.entity.database.Login;
import com.rentmanager.entity.database.PersonalDetails;
import com.rentmanager.entity.database.RoleMst;
import com.rentmanager.entity.database.Users;
import com.rentmanager.tester.JsonFileRead;
/**
 * 
 * @author tanmay
 *
 */
public class LoginService {
	private LoginDao dao;

	public LoginService() {
		dao = new LoginDao();
	}

	public Users getUser(String email) {
		return dao.getUser(email);
	}

	public RoleMst getRole(Integer roleId) {
		return dao.getRole(roleId);
	}

	public List<Module> readModuleList(Integer roleId) {
		List<Module> moduleList=new LinkedList<Module>();
		ClassLoader classLoader = new JsonFileRead().getClass().getClassLoader();
		File file = new File(classLoader.getResource(Constants.MODULE_JSON_PATH).getFile());
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(new FileReader(file));
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray roleArray = (roleId == 1) ? (JSONArray) jsonObject.get(Constants.ROLE_ADMIN) : (JSONArray) jsonObject.get(Constants.ROLE_TENANT);
			for(int i=0;i<roleArray.size();i++){
				Module module=new Gson().fromJson(roleArray.get(i).toString(), Module.class);
				moduleList.add(module);
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

	public Login getLogin(String userId) {
		return dao.getLogin(userId);
	}

	public PersonalDetails getPersonalDetails(String userId) {
		return dao.getPersonalDetails(userId);
	}

	public HostelMst getHostelDetails(String hostelId) {
		return dao.getHostelDetails(hostelId);
	}

	public void updateLoginDetails(Login userProfile) {
		new GenericDao().saveOrUpdateEntity(userProfile);
	}
}
