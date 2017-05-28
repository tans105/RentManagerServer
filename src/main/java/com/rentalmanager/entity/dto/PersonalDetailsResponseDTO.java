package com.rentalmanager.entity.dto;

import java.util.List;

import com.rentalmanager.entity.GenericFormEntity;
import com.rentalmanager.entity.database.PersonalDetails;

/**
 * @author : tanmay
 * @created : 28-May-2017
 */
public class PersonalDetailsResponseDTO {
	private List<GenericFormEntity> formSchema;
	private PersonalDetails personalDetails;
	private Boolean success;
	private String responseMsg;

	public List<GenericFormEntity> getFormSchema() {
		return formSchema;
	}

	public void setFormSchema(List<GenericFormEntity> formSchema) {
		this.formSchema = formSchema;
	}

	public PersonalDetails getPersonalDetails() {
		return personalDetails;
	}

	public void setPersonalDetails(PersonalDetails personalDetails) {
		this.personalDetails = personalDetails;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

}
