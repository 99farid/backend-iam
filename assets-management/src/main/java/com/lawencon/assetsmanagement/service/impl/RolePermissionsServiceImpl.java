package com.lawencon.assetsmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.assetsmanagement.dao.RolePermissionsDao;
import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDataDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.model.RolePermissions;
import com.lawencon.assetsmanagement.service.RolePermissionsService;
import com.lawencon.base.BaseServiceImpl;

@Service
public class RolePermissionsServiceImpl extends BaseServiceImpl implements RolePermissionsService {

	@Autowired
	private RolePermissionsDao permissionsDetailDao;
	
	@Override
	public List<RolePermissions> findAll() throws Exception {
		return permissionsDetailDao.findAll();
	}

	@Override
	public RolePermissions findById(String id) throws Exception {
		return permissionsDetailDao.findById(id);
	}

//	@Override
//	public UpdateResDto update(RolePermissions data) throws Exception {
//		UpdateResDto updateResDto = new UpdateResDto();
//		UpdateResDataDto updateResDataDto = new UpdateResDataDto();
//		
//		
//		
//		return updateResDto;
//	}

	@Override
	public DeleteResDataDto removeById(String id) throws Exception {
		DeleteResDataDto deleteResDataDto = new DeleteResDataDto();
		
		begin();
		boolean resultDelete = permissionsDetailDao.removeById(id);
		commit();
		
		if (resultDelete) {
			deleteResDataDto.setMsg("");
		} else {
			deleteResDataDto.setMsg("");
		}
		
		return deleteResDataDto;
	}

	
}
