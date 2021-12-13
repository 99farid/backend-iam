package com.lawencon.assetsmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.lawencon.base.BaseEntity;

@Entity
public class DetailTransactionsIn extends BaseEntity{
	
	private static final long serialVersionUID = -8081228968172607094L;
	
	@Column(name = "id_transaction_in")
	private TransactionsIn transactionIn;
	
	@ManyToOne
	@JoinColumn(name = "id_asset", nullable = false)
	private Assets asset;
	
	@ManyToOne
	@JoinColumn(name = "conditionAsset", nullable = false)
	private ConditionAssets conditionAsset;
	
	public TransactionsIn getTransactionIn() {
		return transactionIn;
	}
	public void setTransactionIn(TransactionsIn transactionIn) {
		this.transactionIn = transactionIn;
	}
	public Assets getAsset() {
		return asset;
	}
	public void setAsset(Assets asset) {
		this.asset = asset;
	}
	public ConditionAssets getConditionAsset() {
		return conditionAsset;
	}
	public void setConditionAsset(ConditionAssets conditionAsset) {
		this.conditionAsset = conditionAsset;
	}
	
	
}
