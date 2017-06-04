package com.rentmanager.entity.dto;

import java.util.List;

import com.rentmanager.entity.GenericFormEntityBundle;
import com.rentmanager.entity.database.PersonalDetails;

/**
 * @author : tanmay
 * @created : 28-May-2017
 */
public class PersonalDetailsResponseDTO {
	private List numericFields;
	private List<GenericFormEntityBundle> formSchema;
	private PersonalDetails personalDetails;
	private Boolean success;
	private String responseMsg;

	public List<GenericFormEntityBundle> getFormSchema() {
		return formSchema;
	}

	public void setFormSchema(List<GenericFormEntityBundle> formSchema) {
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
