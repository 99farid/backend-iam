package com.lawencon.assetsmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.lawencon.base.BaseEntity;

@Entity
public class Permissions extends BaseEntity {
	
	@Column(length = 12, unique = true, nullable = false)
	private String code;
	
	@Column(length = 64, nullable = false)
	private String permissionName;
	
	public String setCode() {
		return code;
	}
	
	public void getCode(String code) {
		this.code = code;
	}
	
	public String setPermissionName() {
		return permissionName;
	}
	
	public void getPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}	
}