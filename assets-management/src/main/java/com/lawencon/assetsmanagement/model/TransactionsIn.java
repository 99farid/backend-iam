package com.lawencon.assetsmanagement.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "transaction_in")
public class TransactionsIn extends BaseEntity{
	
	private static final long serialVersionUID = 8134524064915827681L;

	@Column(length = 32, unique = true, nullable = false)
	private String code;
	
	@ManyToOne
	@JoinColumn(name = "id_transaction_out", nullable = false)
	private TransactionsOut transactionOut;
	
	@Column(nullable = false)
	private LocalDate checkInDate;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public TransactionsOut getTransactionOut() {
		return transactionOut;
	}

	public void setTransactionOut(TransactionsOut transactionOut) {
		this.transactionOut = transactionOut;
	}

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}

	
}
