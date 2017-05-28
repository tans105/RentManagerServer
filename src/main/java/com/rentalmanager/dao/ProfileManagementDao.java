package com.rentalmanager.dao;

import java.util.HashMap;

import com.rentalmanager.entity.database.PersonalDetails;

/**
 * @author : tanmay
 * @created : 28-May-2017
 */
public class ProfileManagementDao {
	private GenericDao dao;

	public ProfileManagementDao() {
		dao = new GenericDao();
	}

	public PersonalDetails getPersonalDetails(String userId) {
		HashMap<String, Object> filter = new HashMap<String, Object>();
		filter.put("userId", userId);
		return dao.getEntityByProperty(filter, PersonalDetails.class);
	}
}
