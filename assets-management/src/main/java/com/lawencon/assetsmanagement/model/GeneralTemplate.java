package com.lawencon.assetsmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.lawencon.base.BaseEntity;

@Entity
public class GeneralTemplate extends BaseEntity{

	private static final long serialVersionUID = -3931456555445221186L;

	@Column(nullable = false, length = 36, unique = true)
	private String code;

	@Column(name = "data_template", nullable =  false)
	private String dataTemplate;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDataTemplate() {
		return dataTemplate;
	}

	public void setDataTemplate(String dataTemplate) {
		this.dataTemplate = dataTemplate;
	}
	
	
	
}
