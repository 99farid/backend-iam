package com.lawencon.assetsmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.lawencon.base.BaseEntity;

@Entity
public class Files extends BaseEntity{

	private static final long serialVersionUID = 4049987320330733803L;

	@Column()
	private byte[] dataFile;

	@Column()
	private String extention;
	
	public byte[] getDataFile() {
		return dataFile;
	}

	public void setDataFile(byte[] dataFile) {
		this.dataFile = dataFile;
	}

	public String getExtention() {
		return extention;
	}

	public void setExtention(String extention) {
		this.extention = extention;
	}
	
	
	
}
