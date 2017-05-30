package com.rentmanager.tester;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.rentmanager.entity.Module;

public class JsonFileRead {
	public static void main(String[] args) throws org.json.simple.parser.ParseException {
		JSONParser parser = new JSONParser();
		String fileName = "module.json";
		ClassLoader classLoader = new JsonFileRead().getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());

		//File is found
		System.out.println("File Found : " + file.exists());

		try {

			Object obj = parser.parse(new FileReader(file));

			JSONObject jsonObject = (JSONObject) obj;
			System.out.println(jsonObject);

			// loop array
			JSONArray lang = (JSONArray) jsonObject.get("admin");
			@SuppressWarnings("unused")
			List<Module> moduleList=new LinkedList<Module>();
			for(int i=0;i<lang.size();i++){
				System.out.println(lang.get(i));
				Module module=new Gson().fromJson(lang.get(i).toString(), Module.class);
				System.out.println(module.getModuleLink());
				System.out.println(module.getModuleName());
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
