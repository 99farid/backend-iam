package com.lawencon.assetsmanagement.dto.files;

public class InsertReqDataFilesDto {
	private byte[] dataFile;
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
