package com.lawencon.assetsmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.lawencon.base.BaseEntity;

@Entity
public class ConditionAssets extends BaseEntity{
	
	private static final long serialVersionUID = 8802233957966662402L;

	@Column(length = 12, unique = true, nullable = false)
	private String code;
	
	@ManyToOne
	@JoinColumn(name = "id_status_asset", nullable = false)
	private StatusAssets statusAsset;
	
	@Column(length = 32, nullable = false)
	private String conditionAssetName;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public StatusAssets getStatusAsset() {
		return statusAsset;
	}

	public void setStatusAsset(StatusAssets statusAsset) {
		this.statusAsset = statusAsset;
	}

	public String getConditionAssetName() {
		return conditionAssetName;
	}

	public void setConditionAssetName(String conditionAssetName) {
		this.conditionAssetName = conditionAssetName;
	}

	
	
	
	
}
