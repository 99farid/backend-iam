package com.lawencon.assetsmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.assetsmanagement.constant.ResponseMsg;
import com.lawencon.assetsmanagement.dao.FilesDao;
import com.lawencon.assetsmanagement.dao.ProfileUsersDao;
import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDataDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.profileusers.FindAllResProfileUsersDto;
import com.lawencon.assetsmanagement.dto.profileusers.FindByIdResProfileUsersDto;
import com.lawencon.assetsmanagement.dto.profileusers.FindByResUserIdDto;
import com.lawencon.assetsmanagement.dto.profileusers.InsertReqDataProfileUsersDto;
import com.lawencon.assetsmanagement.model.Employees;
import com.lawencon.assetsmanagement.model.Files;
import com.lawencon.assetsmanagement.model.ProfileUsers;
import com.lawencon.assetsmanagement.model.Users;
import com.lawencon.assetsmanagement.service.ProfileUsersService;

@Service
public class ProfileUsersServiceImpl extends BaseIamServiceImpl implements ProfileUsersService {

	@Autowired
	private ProfileUsersDao profileUsersDao;
	
	@Autowired
	private FilesDao filesDao;
	
	public FindAllResProfileUsersDto findAll() throws Exception {
		FindAllResProfileUsersDto result = new FindAllResProfileUsersDto();
		result.setData(profileUsersDao.findAll());
		result.setMsg(null);
		
		return result;
	}

	@Override
	public FindByIdResProfileUsersDto findById(String id) throws Exception {
		FindByIdResProfileUsersDto result = new FindByIdResProfileUsersDto();
		result.setData(profileUsersDao.findById(id));
		result.setMsg(null);
		
		return result;
	}
	
	@Override
	public FindByResUserIdDto findByUser(String userId) throws Exception {
		FindByResUserIdDto result = new FindByResUserIdDto();
		result.setData(profileUsersDao.findByUser(userId));
		result.setMsg(null);
		
		return result;
	}
	

	@Override
	public InsertResDto insert(InsertReqDataProfileUsersDto data, MultipartFile file) throws Exception {
		try {
			InsertResDto insertResDto = new InsertResDto();
			InsertResDataDto insertResDataDto = new InsertResDataDto();
			
			String extention = file.getOriginalFilename();
			extention = extention.substring(extention.lastIndexOf(".")+1, extention.length());
			
			Files fileInsert = new Files();
			fileInsert.setDataFile(file.getBytes());
			fileInsert.setExtention(extention);
			fileInsert.setCreatedBy(getIdAuth());
			
			
			begin();
			Files filesSave = new Files();
			filesSave = filesDao.saveOrUpdate(fileInsert);
			ProfileUsers profileUsers = new ProfileUsers();
			
			Users users = new Users();
			users.setId(data.getIdUser());
			
			Employees employees = new Employees();
			employees.setId(data.getIdEmployee());
			
			profileUsers.setUser(users);
			profileUsers.setEmployee(employees);
			profileUsers.setProfilePict(filesSave);
			profileUsers.setCreatedBy(getIdAuth());
			profileUsers.setIsActive(data.getIsActive());
			
			ProfileUsers profileUsersSave = profileUsersDao.saveOrUpdate(profileUsers);
			commit();
			
			insertResDataDto.setId(profileUsersSave.getId());
			insertResDto.setData(insertResDataDto);
			insertResDto.setMsg(ResponseMsg.SUCCESS_INSERT.getMsg());
			
			return insertResDto;
			
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	@Override
	public UpdateResDto update(ProfileUsers data, MultipartFile file ) throws Exception {
		try {
			UpdateResDto updateResDto = new UpdateResDto();
			UpdateResDataDto updateResDataDto = new UpdateResDataDto();
			data.setUpdatedBy(getIdAuth());
			begin();
			if(file != null) {
				String extention = file.getOriginalFilename();
				extention = extention.substring(extention.lastIndexOf(".")+1, extention.length());
				
				Files fileInsert = new Files();
				fileInsert.setDataFile(file.getBytes());
				fileInsert.setExtention(extention);
				fileInsert.setCreatedBy(getIdAuth());
				fileInsert = filesDao.saveOrUpdate(fileInsert);
				data.setProfilePict(fileInsert);
			}
			ProfileUsers profileUsersUpdate = profileUsersDao.saveOrUpdate(data);
			commit();
			
			updateResDataDto.setVersion(profileUsersUpdate.getVersion());
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
			boolean resultDelete = profileUsersDao.removeById(id);
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
}