package com.lawencon.assetsmanagement.service;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.users.FindAllResUsersDto;
import com.lawencon.assetsmanagement.dto.users.FindByIdResUsersDto;
import com.lawencon.assetsmanagement.dto.users.FindByResEmailDto;
import com.lawencon.assetsmanagement.model.Users;

public interface UsersService {

	FindAllResUsersDto findAll() throws Exception;
	
	FindByIdResUsersDto findById(String id) throws Exception;
	
	FindByResEmailDto findByEmail (String email) throws Exception;
	
	InsertResDto insert(Users data) throws Exception;
	
	UpdateResDto update(Users data) throws Exception;
	
	DeleteResDataDto removeById(String id) throws Exception;
}
