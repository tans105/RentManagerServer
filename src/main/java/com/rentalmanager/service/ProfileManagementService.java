package com.rentalmanager.service;

import com.rentalmanager.dao.ProfileManagementDao;
import com.rentalmanager.entity.database.PersonalDetails;

/**
 * @author : tanmay
 * @created : 28-May-2017
 */
public class ProfileManagementService extends GenericService{
	private ProfileManagementDao dao;
	private String userId;

	public ProfileManagementService(String userId) {
		dao = new ProfileManagementDao();
		this.userId = userId;
	}

	public PersonalDetails getPersonalDetails() {
		return dao.getPersonalDetails(userId);
	}
	
	
}
