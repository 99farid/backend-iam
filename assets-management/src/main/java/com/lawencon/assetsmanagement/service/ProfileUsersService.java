package com.lawencon.assetsmanagement.service;

import org.springframework.web.multipart.MultipartFile;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.profileusers.FindAllResProfileUsersDto;
import com.lawencon.assetsmanagement.dto.profileusers.FindByIdResProfileUsersDto;
import com.lawencon.assetsmanagement.dto.profileusers.FindByResUserIdDto;
import com.lawencon.assetsmanagement.dto.profileusers.InsertReqDataProfileUsersDto;
import com.lawencon.assetsmanagement.model.ProfileUsers;

public interface ProfileUsersService {

	FindAllResProfileUsersDto findAll() throws Exception;
	
	FindByIdResProfileUsersDto findById(String id) throws Exception;
	
	FindByResUserIdDto findByUser() throws Exception;
	
	InsertResDto insert(InsertReqDataProfileUsersDto data, MultipartFile profilePict) throws Exception;
	
	UpdateResDto update(ProfileUsers data, MultipartFile profilePict)throws Exception;
	
	DeleteResDataDto removeById(String id) throws Exception;
}