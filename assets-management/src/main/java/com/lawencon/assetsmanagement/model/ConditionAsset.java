package com.lawencon.assetsmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "condition_asset")
public class ConditionAsset extends BaseEntity{
	
	private static final long serialVersionUID = 8802233957966662402L;

	@Column(length = 12, unique = true, nullable = false)
	private String code;
	
	@ManyToOne
	@JoinColumn(name = "id_status_asset", nullable = false)
	private StatusAsset statusAsset;
	
	@Column(length = 32, nullable = false)
	private String conditionAssetName;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public StatusAsset getStatusAsset() {
		return statusAsset;
	}

	public void setStatusAsset(StatusAsset statusAsset) {
		this.statusAsset = statusAsset;
	}

	public String getConditionAssetName() {
		return conditionAssetName;
	}

	public void setConditionAssetName(String conditionAssetName) {
		this.conditionAssetName = conditionAssetName;
	}

	
	
	
	
}
