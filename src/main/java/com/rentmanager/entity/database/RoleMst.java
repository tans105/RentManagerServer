package com.rentmanager.entity.database;
/**
 * 
 * @author tanmay
 *
 */
public class RoleMst {
	private Integer roleId;
	private String role;
	private String roleAbbr;
	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRoleAbbr() {
		return roleAbbr;
	}

	public void setRoleAbbr(String roleAbbr) {
		this.roleAbbr = roleAbbr;
	}
}
