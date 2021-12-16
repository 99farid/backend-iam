package com.lawencon.assetsmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.lawencon.base.BaseEntity;

@Entity
public class Locations extends BaseEntity{
	
	private static final long serialVersionUID = -3902967015421009810L;

	@Column(length = 12, unique = true, nullable = false)
	private String code;
	
	@ManyToOne
	@JoinColumn(name = "id_company",nullable = false)
	private Companies company;
	
	@Column(name = "locations_name" ,length = 32, nullable = false)
	private String locationName;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Companies getCompany() {
		return company;
	}

	public void setCompany(Companies company) {
		this.company = company;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	
	
	
}
