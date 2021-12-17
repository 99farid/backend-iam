package com.lawencon.assetsmanagement.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "track_activity")
public class TrackActivity extends BaseEntity{

	private static final long serialVersionUID = 1811027604150109025L;

	@Column(length = 32, unique = true, nullable = false)
	private String code;
	
	@Column(length = 64,  nullable = false)
	private String nameAsset;
	
	@Column(length = 64,  nullable = false)   
	private String statusAsset;
	
	@Column(length = 64,  nullable = false)
	private String activity;
	
	@Column(name="code_transaction", length = 32)
	private String codeTransaction;
	
	@Column(name="date_activity", nullable = false)
	private LocalDate dateActivity;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNameAsset() {
		return nameAsset;
	}

	public void setNameAsset(String nameAsset) {
		this.nameAsset = nameAsset;
	}

	public String getStatusAsset() {
		return statusAsset;
	}

	public void setStatusAsset(String statusAsset) {
		this.statusAsset = statusAsset;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getCodeTransaction() {
		return codeTransaction;
	}

	public void setCodeTransaction(String codeTransaction) {
		this.codeTransaction = codeTransaction;
	}

	public LocalDate getDateActivity() {
		return dateActivity;
	}

	public void setDateActivity(LocalDate dateActivity) {
		this.dateActivity = dateActivity;
	}
}