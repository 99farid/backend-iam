package com.lawencon.assetsmanagement.model;

import com.lawencon.base.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "profile_user")
public class ProfileUser extends BaseEntity {

	private static final long serialVersionUID = 199051703932493253L;

	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false)
	private Users idUser;
	
	@ManyToOne
	@JoinColumn(name = "id_employee", nullable = false)
	private Employee idEmployee;
	
	@ManyToOne
	@JoinColumn(name = "id_profile_pict", nullable = false)
	private File idProfilePict;
	
	public Users setIdUser() {
		return idUser;
	}
	
	public void getIdUser(Users idUser) {
		this.idUser = idUser;
	}
	
	public Employee setIdEmplyoee() {
		return idEmployee;
	}
	
	public void getIdEmployee(Employee idEmployee) {
		this.idEmployee = idEmployee;
	}
	
	public File setIdProfilePict() {
		return idProfilePict;
	}
	
	public void getIdProfilePict(File idProfilePict) {
		this.idProfilePict = idProfilePict;
	}
}