package com.lawencon.assetsmanagement.service;

import java.util.List;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.profileusers.InsertReqDataProfileUsersDto;
import com.lawencon.assetsmanagement.model.ProfileUsers;

public interface ProfileUsersService {

	List<ProfileUsers> findAll() throws Exception;
	
	ProfileUsers findById(String id) throws Exception;
	
	InsertResDto insert(InsertReqDataProfileUsersDto data) throws Exception;
	
	UpdateResDto update(ProfileUsers data)throws Exception;
	
	DeleteResDataDto removeById(String id) throws Exception;
}
