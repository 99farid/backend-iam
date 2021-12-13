package com.lawencon.assetsmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.lawencon.base.BaseEntity;

@Entity
public class Roles extends BaseEntity {

	private static final long serialVersionUID = -5590004788415187536L;

	@Column(length = 12, unique = true, nullable = false)
	private String code;
	
	@Column(name = "role_name", length = 64, nullable = false)
	private String roleName;
	
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
}