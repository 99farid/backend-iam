package com.lawencon.assetsmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.lawencon.base.BaseEntity;

@Entity
public class Employee extends BaseEntity{

	private static final long serialVersionUID = 7217564241483812487L;

	@ManyToOne
	@JoinColumn(name = "id_company", nullable = false)
	private Company idCompany;
	
	@Column(length = 32, unique = true, nullable = false)
	private String nip;
	
	@Column(name = "full_name", length = 64, nullable = false)
	private String fullName;
	
	@Column(length = 13, nullable = false)
	private String phoneNo;
	
	@Column(length = 32, nullable = false)
	private String department;

	public Company setIdCompany() {
		return idCompany;
	}
	
	public void getIdCompany(Company idCompany) {
		this.idCompany = idCompany;
	}
	
	public String setNip() {
		return nip;
	}
	
	public void getNip(String nip) {
		this.nip = nip;
	}
	
	public String setFullName() {
		return fullName;
	}
	
	public void getFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String setPhoneNo() {
		return phoneNo;
	}
	
	public void getPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	public String setDepartment() {
		return department;
	}
	
	public void getDepartment(String department) {
		this.department = department;
	}
}