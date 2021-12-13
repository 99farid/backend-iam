package com.lawencon.assetsmanagement.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "detail_transaction_out")
public class DetailTransactionOut extends BaseEntity{

	private static final long serialVersionUID = 5738129835522199612L;

	@JoinColumn(name = "id_transaction_out", nullable = false)
	private TransactionOut idTransactionOut;
	
	@JoinColumn(name = "id_asset", nullable = false)
	private Asset idAsset;

	@Column(name = "check_out_date", nullable = false)
	private LocalDate checkOutDate;
	
	
	public TransactionOut setIdTransactionOut() {
		return idTransactionOut;
	}
	
	public void getIdTransactionOut(TransactionOut idTransactionOut) {
		this.idTransactionOut = idTransactionOut;
	}
	
	public Asset setIdAsset() {
		return idAsset;
	}
	
	public void getIdAsset(Asset idAsset) {
		this.idAsset = idAsset;
	}
	
	public LocalDate setCheckOutDate() {
		return checkOutDate;
	}
	
	public void getCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
}