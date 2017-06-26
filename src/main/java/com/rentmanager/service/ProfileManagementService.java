package com.rentmanager.service;

import java.util.ArrayList;
import java.util.List;

import com.rentmanager.dao.GenericDao;
import com.rentmanager.dao.ProfileManagementDao;
import com.rentmanager.entity.SelectListData;
import com.rentmanager.entity.database.PersonalDetails;
import com.rentmanager.entity.database.RoleMst;

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

	public String saveOrUpdateProfile(PersonalDetails pd) {
		return dao.saveOrUpdateProfile(pd);
	}

	public List<Object> getStateMst() {
		return dao.getStateMst();
	}

	public List<Object> getIdproofMst() {
		return dao.getIdProofMst();
	}

	public List<SelectListData> getRoleSelectList() {
		List<String> attributes = new ArrayList<String>();
		attributes.add("roleId");
		attributes.add("role");
		return new GenericDao().getSelectListData(RoleMst.class, attributes, null);
	}

}
