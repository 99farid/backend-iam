package com.lawencon.assetsmanagement.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.lawencon.base.BaseEntity;

@Entity
public class PermissionsDetail extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "id_role", nullable = false)
	private Roles idRole;
	
	@ManyToOne
	@JoinColumn(name = "id_permission", nullable= false)
	private Permissions idPermission;
	
	public Roles setIdRole() {
		return idRole;
	}
	
	public void getIdRole(Roles idRole) {
		this.idRole = idRole;
	}
	
	public Permissions setIdPermission() {
		return idPermission;
	}
	
	public void getPermission(Permissions idPermission) {
		this.idPermission = idPermission;
	}
}