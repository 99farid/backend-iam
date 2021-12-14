package com.lawencon.assetsmanagement.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "role_permissions")
public class RolePermissions extends BaseEntity {

	private static final long serialVersionUID = 5542835391266620043L;

	@ManyToOne
	@JoinColumn(name = "id_role", nullable = false)
	private Roles role;
	
	@ManyToOne
	@JoinColumn(name = "id_permission", nullable = false)
	private Permissions permission;

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public Permissions getPermission() {
		return permission;
	}

	public void setPermission(Permissions permission) {
		this.permission = permission;
	}
}