package com.lawencon.assetsmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.lawencon.base.BaseEntity;

@Entity
public class DetailTransactionIn extends BaseEntity{
	
	private static final long serialVersionUID = -8081228968172607094L;
	
	@Column(name = "id_transaction_in")
	private TransactionIn transactionIn;
	
	@ManyToOne
	@JoinColumn(name = "id_asset", nullable = false)
	private Asset asset;
	
	@ManyToOne
	@JoinColumn(name = "conditionAsset", nullable = false)
	private ConditionAsset conditionAsset;
	
	public TransactionIn getTransactionIn() {
		return transactionIn;
	}
	public void setTransactionIn(TransactionIn transactionIn) {
		this.transactionIn = transactionIn;
	}
	public Asset getAsset() {
		return asset;
	}
	public void setAsset(Asset asset) {
		this.asset = asset;
	}
	public ConditionAsset getConditionAsset() {
		return conditionAsset;
	}
	public void setConditionAsset(ConditionAsset conditionAsset) {
		this.conditionAsset = conditionAsset;
	}
	
	
}
