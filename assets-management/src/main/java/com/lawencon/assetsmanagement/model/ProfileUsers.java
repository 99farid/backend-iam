package com.lawencon.assetsmanagement.model;

import com.lawencon.base.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "profile_user")
public class ProfileUsers extends BaseEntity {

	private static final long serialVersionUID = 199051703932493253L;

	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false)
	private Users idUser;
	
	@ManyToOne
	@JoinColumn(name = "id_employee", nullable = false)
	private Employees idEmployee;
	
	@ManyToOne
	@JoinColumn(name = "id_profile_pict", nullable = false)
	private Files idProfilePict;
	
	
	public Users setIdUser() {
		return idUser;
	}
	
	public void getIdUser(Users idUser) {
		this.idUser = idUser;
	}
	
	public Employees setIdEmplyoee() {
		return idEmployee;
	}
	
	public void getIdEmployee(Employees idEmployee) {
		this.idEmployee = idEmployee;
	}
	
	public Files setIdProfilePict() {
		return idProfilePict;
	}
	
	public void getIdProfilePict(Files idProfilePict) {
		this.idProfilePict = idProfilePict;
	}
}