package com.lawencon.assetsmanagement.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.lawencon.base.BaseEntity;

@Entity
public class Users extends BaseEntity {

	private static final long serialVersionUID = -3585717457715598973L;

	@ManyToOne
	@JoinColumn(name = "id_role", nullable = false)
	private Roles role;
	
	@Column(length = 32, unique = true, nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String pass;

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
}