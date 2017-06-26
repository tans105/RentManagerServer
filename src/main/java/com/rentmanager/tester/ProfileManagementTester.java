package com.rentmanager.tester;

import com.rentmanager.service.ProfileManagementService;

/**
 * @author : tanmay
 * @created : 28-May-2017
 */
public class ProfileManagementTester {
	public static void main(String[] args) {
		ProfileManagementService service=new ProfileManagementService("ADM0000000001");
		
		service.getGenericFormSchema("personalDetails","formschema/personal_details_schema_revamped.json");
		
	}
	
}
