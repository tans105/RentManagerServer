package com.rentalmanager.tester;

import java.util.List;

import com.rentalmanager.entity.GenericFormEntity;
import com.rentalmanager.service.ProfileManagementService;

/**
 * @author : tanmay
 * @created : 28-May-2017
 */
public class ProfileManagementTester {
	public static void main(String[] args) {
		ProfileManagementService service=new ProfileManagementService("ADM0000000001");
		
		List<GenericFormEntity> list=service.getFormSchema("personalDetails","personal_details_schema.json");
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).getEditBy()[1]);
		}
	}
	
}
