package com.lawencon.assetsmanagement.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "detail_transactions_out")
public class DetailTransactionsOut extends BaseEntity{

	private static final long serialVersionUID = -5753674000518808026L;

	@ManyToOne
	@JoinColumn(name = "id_transaction_out", nullable = false)
	private TransactionsOut transactionOut;
	
	@ManyToOne
	@JoinColumn(name = "id_asset", nullable = false)
	private Assets asset;

	@Column(name = "due_date", nullable = false)
	private LocalDate dueDate;
	
	@Column(name = "status_email", nullable = false)
	private Boolean statusEmail;

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

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public Boolean getStatusEmail() {
		return statusEmail;
	}

	public void setStatusEmail(Boolean statusEmail) {
		this.statusEmail = statusEmail;
	}
	
	
}