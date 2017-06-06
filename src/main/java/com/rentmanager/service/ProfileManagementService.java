package com.rentmanager.service;

import java.util.List;

import com.rentmanager.dao.ProfileManagementDao;
import com.rentmanager.entity.database.PersonalDetails;

/**
 * @author : tanmay
 * @created : 28-May-2017
 */
public class ProfileManagementService extends GenericService {
	private ProfileManagementDao dao;
	private String userId;

	public ProfileManagementService(String userId) {
		dao = new ProfileManagementDao();
		this.userId = userId;
	}

	public PersonalDetails getPersonalDetails() {
		return dao.getPersonalDetails(userId);
	}

	public boolean saveOrUpdateProfile(PersonalDetails pd) {
		return dao.saveOrUpdateProfile(pd);
	}

	public List<String> getStateMst() {
		return dao.getStateMst();
	}

	public List<String> getIdproofMst() {
		return dao.getIdProofMst();
	}

}
