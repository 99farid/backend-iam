package com.lawencon.assetsmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.assetsmanagement.dao.PermissionsDao;
import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDataDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.model.Companies;
import com.lawencon.assetsmanagement.model.Permissions;
import com.lawencon.assetsmanagement.service.PermissionsService;
import com.lawencon.base.BaseServiceImpl;

@Service
public class PermissionsServiceImpl extends BaseServiceImpl implements PermissionsService {

	@Autowired
	private PermissionsDao permissionsDao;

	public List<Permissions> findAll() throws Exception {
		return permissionsDao.findAll();
	}

	public Permissions findById(String id) throws Exception {
		return permissionsDao.findById(id);
	}

	@Override
	public InsertResDto insert(Permissions data) throws Exception {
		InsertResDto insertResDto = new InsertResDto();
		InsertResDataDto insertResDataDto = new InsertResDataDto();
		
		begin();
		Permissions permissionsSave = permissionsDao.saveOrUpdate(data);
		commit();
		
		insertResDataDto.setId(permissionsSave.getId());
		insertResDto.setData(insertResDataDto);
		insertResDto.setMsg(null);

		return insertResDto;
	}

	@Override
	public UpdateResDto update(Permissions data) throws Exception {
		UpdateResDto updateResDto = new UpdateResDto();
		UpdateResDataDto updateResDataDto = new UpdateResDataDto();

		begin();
		Permissions permissionsUpdate = permissionsDao.saveOrUpdate(data);
		commit();

		updateResDataDto.setVersion(permissionsUpdate.getVersion());
		updateResDto.setData(updateResDataDto);
		updateResDto.setMsg(".....");

		return updateResDto;
	}

	@Override
	public DeleteResDataDto removeById(String id) throws Exception {
		DeleteResDataDto deleteResDataDto = new DeleteResDataDto();
		
		begin();
		boolean resultDelete = permissionsDao.removeById(id);
		commit();
		
		if (resultDelete) {
			deleteResDataDto.setMsg("");
		} else {
			deleteResDataDto.setMsg("");
		}

		return deleteResDataDto;
	}

}