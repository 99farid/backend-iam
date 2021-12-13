package com.lawencon.assetsmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "transaction_out")
public class TransactionsOut extends BaseEntity {

	private static final long serialVersionUID = 1259288568796069056L;
	
	@Column(length = 32, unique = true, nullable = false)
	private String code;
	
	@ManyToOne
	@JoinColumn(name = "id_employee", nullable = false)
	private Employees idEmployee;
	
	@ManyToOne
	@JoinColumn(name = "id_location", nullable = false)
	private Locations idLocation;
	
	@ManyToOne
	@JoinColumn(name = "id_general_item", nullable = false)
	private Assets idGeneralItem;
	
	public String setCode() {
		return code;
	}
	
	public void getCode(String code) {
		this.code = code;	
	}
	
	public Employees setIdEmployee() {
		return idEmployee;
	}
	
	public void getIdEmployee(Employees idEmployee) {
		this.idEmployee = idEmployee;
	}
	
	public Locations setIdLocation() {
		return idLocation;
	}
	
	public void getIdLocation(Locations idLocation) {
		this.idLocation = idLocation;
	}
	
	public Assets setIdGeneralItem() {
		return idGeneralItem;
	}
	
	public void getIdGeneralItem(Assets idGeneralItem) {
		this.idGeneralItem = idGeneralItem;
	}
}
