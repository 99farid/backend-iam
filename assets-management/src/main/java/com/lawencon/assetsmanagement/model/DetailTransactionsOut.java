package com.lawencon.assetsmanagement.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "detail_transaction_out")
public class DetailTransactionsOut extends BaseEntity{

	private static final long serialVersionUID = -4495416002246685185L;

	@ManyToOne
	@JoinColumn(name = "id_transaction_out", nullable = false)
	private TransactionsOut transactionOut;
	
	@ManyToOne
	@JoinColumn(name = "id_asset", nullable = false)
	private Assets asset;

	@Column(name = "check_out_date", nullable = false)
	private LocalDate checkOutDate;

	public TransactionsOut getTransactionOut() {
		return transactionOut;
	}

	public void setTransactionOut(TransactionsOut transactionOut) {
		this.transactionOut = transactionOut;
	}

	public Assets getAsset() {
		return asset;
	}

	public void setAsset(Assets asset) {
		this.asset = asset;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
}