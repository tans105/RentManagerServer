package com.rentmanager.entity.dto;

import java.util.List;

import com.rentmanager.entity.Module;
/**
 * 
 * @author tanmay
 *
 */
public class GenericResponseDTO {
	private String token;
	private Boolean success;
	private String responseMsg;
	private List<Module> moduleList;

	public String getToken() {
		return token;
	}

	public List<Module> getModuleList() {
		return moduleList;
	}

	public void setModuleList(List<Module> moduleList) {
		this.moduleList = moduleList;
	}

	public void setToken(String token) {
		this.token = token;
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
