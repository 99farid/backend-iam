package com.lawencon.assetsmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.lawencon.base.BaseEntity;

@Entity
public class Permissions extends BaseEntity {

	private static final long serialVersionUID = -5330899124288656065L;

	@Column(length = 12, unique = true, nullable = false)
	private String code;
	
	@Column(length = 64, nullable = false)
	private String permissionName;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
}