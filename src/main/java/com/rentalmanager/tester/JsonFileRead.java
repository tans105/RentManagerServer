package com.rentalmanager.tester;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonFileRead {
	@SuppressWarnings("unchecked")
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
            JSONArray msg = (JSONArray) jsonObject.get("admin");
            Iterator<String> iterator = msg.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

	}
}
