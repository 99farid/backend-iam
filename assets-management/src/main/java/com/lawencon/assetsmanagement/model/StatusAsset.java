package com.lawencon.assetsmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "status_asset")
public class StatusAsset extends BaseEntity{
	
	private static final long serialVersionUID = 7580712562063032430L;

	@Column(length = 12, unique = true, nullable = false)
	private String code;
	
	@Column(name = "status_asset_name",length = 32, nullable = false)
	private String statusAssetName;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatusAssetName() {
		return statusAssetName;
	}

	public void setStatusAssetName(String statusAssetName) {
		this.statusAssetName = statusAssetName;
	}

}
