package com.lawencon.assetsmanagement.dto.roles;

import java.util.List;

public class InsertReqDataRolesDto {

	private String code;

	private String roleName;
	
	private List<String> idPermission;

	private Boolean isActive;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<String> getIdPermission() {
		return idPermission;
	}

	public void setIdPermission(List<String> idPermission) {
		this.idPermission = idPermission;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
