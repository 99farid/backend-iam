package com.lawencon.assetsmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.lawencon.base.BaseEntity;

@Entity
public class Companies extends BaseEntity {

	private static final long serialVersionUID = 220723702658573381L;

	@Column(length = 36, unique= true, nullable = false)
	private String code;
	
	@Column(length = 64, nullable = false)
	private String companyName;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}