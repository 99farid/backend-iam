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
	
	private static final long serialVersionUID = -6377906156681298363L;

	@Column(length = 32, unique = true, nullable = false)
	private String code;
	
	@ManyToOne
	@JoinColumn(name = "id_employee", nullable = false)
	private Employees employee;
	
	@ManyToOne
	@JoinColumn(name = "id_location", nullable = false)
	private Locations location;
	
	@ManyToOne
	@JoinColumn(name = "id_general_item", nullable = false)
	private Assets generalItem;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Employees getEmployee() {
		return employee;
	}

	public void setEmployee(Employees employee) {
		this.employee = employee;
	}

	public Locations getLocation() {
		return location;
	}

	public void setLocation(Locations location) {
		this.location = location;
	}

	public Assets getGeneralItem() {
		return generalItem;
	}

	public void setGeneralItem(Assets generalItem) {
		this.generalItem = generalItem;
	}
}
