package com.lawencon.assetsmanagement.dto.profileusers;

import com.lawencon.assetsmanagement.dto.files.InsertReqDataFilesDto;

public class InsertReqDataProfileUsersDto {

	private String idEmployee;
	
	private InsertReqDataFilesDto idProfilePict;
	
	private Boolean isActive;

	public String getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(String idEmployee) {
		this.idEmployee = idEmployee;
	}

	public InsertReqDataFilesDto getIdProfilePict() {
		return idProfilePict;
	}

	public void setIdProfilePict(InsertReqDataFilesDto idProfilePict) {
		this.idProfilePict = idProfilePict;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}