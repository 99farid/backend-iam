package com.lawencon.assetsmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.lawencon.base.BaseEntity;

@Entity
public class Employees extends BaseEntity{

	private static final long serialVersionUID = 7600468914814562970L;

	@ManyToOne
	@JoinColumn(name = "id_company", nullable = false)
	private Companies company;
	
	@Column(length = 32, unique = true, nullable = false)
	private String nip;
	
	@Column(name = "full_name", length = 64, nullable = false)
	private String fullName;
	
	@Column(length = 13, nullable = false)
	private String phoneNo;
	
	@Column(length = 32, nullable = false)
	private String department;

	public Companies getCompany() {
		return company;
	}

	public void setCompany(Companies company) {
		this.company = company;
	}

	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
}