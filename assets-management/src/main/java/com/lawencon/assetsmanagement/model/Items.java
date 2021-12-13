package com.lawencon.assetsmanagement.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.lawencon.base.BaseEntity;

@Entity
public class Items extends BaseEntity{
	
	private static final long serialVersionUID = 330696244128438788L;

	@Column(length = 32, nullable = false)
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "id_item_type", nullable = false)
	private ItemTypes itemType;
	
	@Column(length = 32, nullable = false)
	private String brand;
	
	@Column(length = 32, nullable = false)
	private String serial;
	
	@Column
	private BigDecimal price;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ItemTypes getItemType() {
		return itemType;
	}

	public void setItemType(ItemTypes itemType) {
		this.itemType = itemType;
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
