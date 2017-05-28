package com.rentalmanager.service;

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
import com.rentalmanager.entity.GenericFormEntity;
import com.rentalmanager.tester.JsonFileRead;

/**
 * @author : tanmay
 * @created : 28-May-2017
 */
public class GenericService {

	public List<GenericFormEntity> getFormSchema(String moduleName, String jsonPath) {
		List<GenericFormEntity> list = new LinkedList<GenericFormEntity>();
		ClassLoader classLoader = new JsonFileRead().getClass().getClassLoader();
		File file = new File(classLoader.getResource(jsonPath).getFile());
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(new FileReader(file));
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray roleArray = (JSONArray) jsonObject.get(moduleName);
			for (int i = 0; i < roleArray.size(); i++) {
				GenericFormEntity entity=new Gson().fromJson(roleArray.get(i).toString(), GenericFormEntity.class);
				list.add(entity);
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
}
