package com.rentmanager.entity;

import com.rentmanager.entity.database.PersonalDetails;

/**
 * @author : tanmay
 * @created : 06-Jun-2017
 */
public class NewUser {
	private String roleId;
	private PersonalDetails personalDetails;

	public PersonalDetails getPersonalDetails() {
		return personalDetails;
	}

	public void setPersonalDetails(PersonalDetails personalDetails) {
		this.personalDetails = personalDetails;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
}
