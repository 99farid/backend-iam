package com.lawencon.assetsmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.lawencon.base.BaseEntity;

@Entity
public class ItemTypes extends BaseEntity{
	
	private static final long serialVersionUID = 2055751440309761435L;

	@Column(length = 32, unique = true, nullable = false)
	private String code;
	
	@Column(length = 32, nullable = false)
	private String itemTypeName;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getItemTypeName() {
		return itemTypeName;
	}

	public void setItemTypeName(String itemTypeName) {
		this.itemTypeName = itemTypeName;
	}
	
	
}
