package com.rentmanager.entity.database;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * @author tanmay
 *
 */
public class Login implements Serializable{
	
	private static final long serialVersionUID = -9001198124094210159L;
	private String userId;
	private String hostelId;
	private String password;
	private String token;
	private Date lastLoginDtm;
	private Integer roleId;
	private Boolean active;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getHostelId() {
		return hostelId;
	}

	public void setHostelId(String hostelId) {
		this.hostelId = hostelId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getLastLoginDtm() {
		return lastLoginDtm;
	}

	public void setLastLoginDtm(Date lastLoginDtm) {
		this.lastLoginDtm = lastLoginDtm;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
