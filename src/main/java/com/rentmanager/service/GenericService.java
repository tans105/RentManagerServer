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
import com.rentmanager.entity.GenericFormEntity;
import com.rentmanager.entity.GenericFormEntityBundle;
import com.rentmanager.entity.Module;
import com.rentmanager.tester.JsonFileRead;

/**
 * @author : tanmay
 * @created : 28-May-2017
 */
public class GenericService {
	private static final String STACK = "stack";
	private static final String SIZE = "size";

	public List<GenericFormEntityBundle> getGenericFormSchema(String moduleName, String jsonPath) {
		List<GenericFormEntityBundle> list = new LinkedList<GenericFormEntityBundle>();
		ClassLoader classLoader = new JsonFileRead().getClass().getClassLoader();
		File file = new File(classLoader.getResource(jsonPath).getFile());
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(new FileReader(file));
			JSONObject json = (JSONObject) obj;
			JSONObject moduleJson = (JSONObject) json.get(moduleName);
			Gson gson = new Gson();
			for (int i = 0; i < Integer.parseInt(moduleJson.get(SIZE).toString()); i++) {
				JSONArray arr = (JSONArray) moduleJson.get(STACK + i);
				List<GenericFormEntity> entityList = new LinkedList<GenericFormEntity>();
				for (int j = 0; j < arr.size(); j++) {
					entityList.add(gson.fromJson(arr.get(j).toString(), GenericFormEntity.class));
				}
				GenericFormEntityBundle bundle = new GenericFormEntityBundle();
				bundle.setSize(arr.size());
				bundle.setStack(entityList);
				list.add(bundle);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return list;
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
}
