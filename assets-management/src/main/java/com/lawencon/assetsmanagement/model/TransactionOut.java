package com.lawencon.assetsmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "transaction_out")
public class TransactionOut extends BaseEntity {

	private static final long serialVersionUID = 1259288568796069056L;
	
	@Column(length = 32, unique = true, nullable = false)
	private String code;
	
	@ManyToOne
	@JoinColumn(name = "id_employee", nullable = false)
	private Employee idEmployee;
	
	@ManyToOne
	@JoinColumn(name = "id_location", nullable = false)
	private Location idLocation;
	
	@ManyToOne
	@JoinColumn(name = "id_general_item", nullable = false)
	private Asset idGeneralItem;
	
	public String setCode() {
		return code;
	}
	
	public void getCode(String code) {
		this.code = code;	
	}
	
	public Employee setIdEmployee() {
		return idEmployee;
	}
	
	public void getIdEmployee(Employee idEmployee) {
		this.idEmployee = idEmployee;
	}
	
	public Location setIdLocation() {
		return idLocation;
	}
	
	public void getIdLocation(Location idLocation) {
		this.idLocation = idLocation;
	}
	
	public Asset setIdGeneralItem() {
		return idGeneralItem;
	}
	
	public void getIdGeneralItem(Asset idGeneralItem) {
		this.idGeneralItem = idGeneralItem;
	}
}