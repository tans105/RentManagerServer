package com.rentmanager.entity.dto;

import java.util.List;

import com.rentmanager.entity.GenericFormEntityBundle;
import com.rentmanager.entity.SelectListData;
import com.rentmanager.entity.Table;
import com.rentmanager.entity.database.PersonalDetails;

/**
 * @author : tanmay
 * @created : 06-Jun-2017
 */
public class UserManagementResponseDTO {
	private List<GenericFormEntityBundle> formSchema;
	private PersonalDetails personalDetails;
	private Boolean success;
	private String responseMsg;
	private String userId;
	private List<Object> stateMst;
	private List<Object> idProofMst;
	private List<SelectListData> roleMst;
	private Table table;

	public List<Object> getIdProofMst() {
		return idProofMst;
	}

	public void setIdProofMst(List<Object> idProofMst) {
		this.idProofMst = idProofMst;
	}

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

	public List<Object> getStateMst() {
		return stateMst;
	}

	public void setStateMst(List<Object> stateMst) {
		this.stateMst = stateMst;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<SelectListData> getRoleMst() {
		return roleMst;
	}

	public void setRoleMst(List<SelectListData> roleMst) {
		this.roleMst = roleMst;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	

}
