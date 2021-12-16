package com.lawencon.assetsmanagement.model;

import com.lawencon.base.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "profile_users")
public class ProfileUsers extends BaseEntity {

	private static final long serialVersionUID = -5803795226717112984L;

	@OneToOne
	@JoinColumn(name = "id_user", unique = true, nullable = false)
	private Users user;
	
	@OneToOne
	@JoinColumn(name = "id_employee", unique = true, nullable = false)
	private Employees employee;
	
	@ManyToOne
	@JoinColumn(name = "id_profile_pict")
	private Files profilePict;

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Employees getEmployee() {
		return employee;
	}

	public void setEmployee(Employees employee) {
		this.employee = employee;
	}

	public Files getProfilePict() {
		return profilePict;
	}

	public void setProfilePict(Files profilePict) {
		this.profilePict = profilePict;
	}
}