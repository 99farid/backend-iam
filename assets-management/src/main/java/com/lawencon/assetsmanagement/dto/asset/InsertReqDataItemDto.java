package com.lawencon.assetsmanagement.dto.asset;

import java.math.BigDecimal;

public class InsertReqDataItemDto {
	private String description;
	private String idItemType;
	private String brand;
	private String serial;
	private BigDecimal price;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIdItemType() {
		return idItemType;
	}
	public void setIdItemType(String idItemType) {
		this.idItemType = idItemType;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
}
