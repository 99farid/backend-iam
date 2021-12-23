package com.lawencon.assetsmanagement.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lawencon.assetsmanagement.constant.ResponseMsg;
import com.lawencon.assetsmanagement.dao.UsersDao;
import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDataDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.users.FindAllResUsersDto;
import com.lawencon.assetsmanagement.dto.users.FindByIdResUsersDto;
import com.lawencon.assetsmanagement.dto.users.FindByResEmailDto;
import com.lawencon.assetsmanagement.email.EmailHandler;
import com.lawencon.assetsmanagement.exception.ValidationIamException;
import com.lawencon.assetsmanagement.model.Users;
import com.lawencon.assetsmanagement.service.UsersService;

@Service
public class UsersServiceImpl extends BaseIamServiceImpl implements UsersService {

	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private BCryptPasswordEncoder bCryptEncoder;
	
	@Autowired
	private EmailHandler emailHandler;
	
	public FindAllResUsersDto findAll() throws Exception {
		FindAllResUsersDto result = new FindAllResUsersDto();
		result.setData(usersDao.findAll());
		result.setMsg(null);
		return result;
	}
	
	public FindByIdResUsersDto findById(String id) throws Exception {
		FindByIdResUsersDto result = new FindByIdResUsersDto();
		result.setData(usersDao.findById(id));
		result.setMsg(null);
		
		return result;
	}

	@Override
	public FindByResEmailDto findByEmail(String email) throws Exception {
		FindByResEmailDto result = new FindByResEmailDto();
		result.setData(usersDao.findByEmail(email));
		result.setMsg(null);
		
		return result;
	}
	private String generatePassword() {
		int upperBound = 99999;
		int lowerBound = 10000;
		int number = lowerBound + (int)(Math.random() * ((upperBound - lowerBound) + 1));
		return String.valueOf(number);
	}
	private void validationInsert(Users data) throws Exception{
		if(data.getId() != null) {
			throw new ValidationIamException("User already used");
		}
		if(data.getEmail() != null) {
			Users user = usersDao.findByEmail(data.getEmail());
			if(user != null) {
				throw new ValidationIamException("Email already used");
			}
		}
		if(data.getIsActive() == null || data.getEmail() == null || data.getRole() == null) {
			throw new ValidationIamException("Data is not complete");
		}
	}
	@Override
	public InsertResDto insert(Users data) throws Exception {
		try {
			validationInsert(data);
			InsertResDto insertResDto = new InsertResDto();
			InsertResDataDto insertResDataDto = new InsertResDataDto();
			String newPass = generatePassword();
			String encodePass = bCryptEncoder.encode(newPass);
			data.setPass(encodePass);
			data.setCreatedBy("1");
			begin();
			Users usersSave = usersDao.saveOrUpdate(data);
			commit();

			emailHandler.sendSimpleMessage(data.getEmail(), "Your new password", newPass);
			insertResDataDto.setId(usersSave.getId());
			insertResDto.setData(insertResDataDto);
			insertResDto.setMsg(ResponseMsg.SUCCESS_INSERT.getMsg());
			
			return insertResDto;
			
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
	private void validationUpdate(Users data) throws Exception{
		if(data.getId() == null) {
			throw new ValidationIamException("User not found");
		}else {
			Users user = usersDao.findById(data.getId());
			if(user == null) {
				throw new ValidationIamException("User not found");
			}
		}
		
			
	}
	@Override
	public UpdateResDto update(Users data) throws Exception {
		try {
			validationUpdate(data);
			UpdateResDto updateResDto = new UpdateResDto();
			UpdateResDataDto updateResDataDto = new UpdateResDataDto();
			data.setUpdatedBy(getIdAuth());
			begin();
			Users usersUpdate = usersDao.saveOrUpdate(data);
			commit();
			
			updateResDataDto.setVersion(usersUpdate.getVersion());
			updateResDto.setData(updateResDataDto);
			updateResDto.setMsg(ResponseMsg.SUCCESS_UPDATE.getMsg());
			
			return updateResDto;
			
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
	private void validationUpdatePassword(String data) {
		if(data == null) {
			throw new ValidationIamException("New Password not found");
		}
	}
	@Override
	public UpdateResDto updatePassword(String data) throws Exception {
		try {
			validationUpdatePassword(data);
			UpdateResDto updateResDto = new UpdateResDto();
			UpdateResDataDto updateResDataDto = new UpdateResDataDto();
			Users usersUpdate = usersDao.findById(getIdAuth());
			String password  = bCryptEncoder.encode(data);
			usersUpdate.setPass(password);
			usersUpdate.setUpdatedBy(getIdAuth());
			begin();
			usersUpdate = usersDao.saveOrUpdate(usersUpdate);
			commit();
			
			updateResDataDto.setVersion(usersUpdate.getVersion());
			updateResDto.setData(updateResDataDto);
			updateResDto.setMsg(ResponseMsg.SUCCESS_UPDATE.getMsg());
			
			return updateResDto;
			
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	@Override
	public DeleteResDataDto removeById(String id) throws Exception {
		try {
			DeleteResDataDto deleteResDataDto = new DeleteResDataDto();
			
			begin();
			boolean resultDelete = usersDao.removeById(id);
			commit();
			
			if (resultDelete) {
				deleteResDataDto.setMsg(ResponseMsg.SUCCESS_DELETE.getMsg());
			} else {
				deleteResDataDto.setMsg(ResponseMsg.FAILED_DELETE.getMsg());
			}

			return deleteResDataDto;
			
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Users result = null;
		try {
			result = findByEmail(email).getData();
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException(e.getMessage());
		}
		
		return new User(result.getEmail(), result.getPass(), new ArrayList<>());
	}
}