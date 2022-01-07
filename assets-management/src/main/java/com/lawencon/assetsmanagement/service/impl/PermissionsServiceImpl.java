package com.lawencon.assetsmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.assetsmanagement.constant.PermissionDeleteCode;
import com.lawencon.assetsmanagement.constant.ResponseMsg;
import com.lawencon.assetsmanagement.dao.PermissionsDao;
import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDataDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.permissions.FindAllResFilterByNameDto;
import com.lawencon.assetsmanagement.dto.permissions.FindAllResPemissionsDto;
import com.lawencon.assetsmanagement.dto.permissions.FindByIdResPermissionsDto;
import com.lawencon.assetsmanagement.exception.ValidationIamException;
import com.lawencon.assetsmanagement.model.Permissions;
import com.lawencon.assetsmanagement.model.RolePermissions;
import com.lawencon.assetsmanagement.service.PermissionsService;

@Service
public class PermissionsServiceImpl extends BaseIamServiceImpl implements PermissionsService {

	@Autowired
	private PermissionsDao permissionsDao;

	public FindAllResPemissionsDto findAll() throws Exception {
		FindAllResPemissionsDto result = new FindAllResPemissionsDto();
		result.setData(permissionsDao.findAll());
		result.setMsg(null);

		return result;
	}

	public FindByIdResPermissionsDto findById(String id) throws Exception {
		FindByIdResPermissionsDto result = new FindByIdResPermissionsDto();
		result.setData(permissionsDao.findById(id));
		result.setMsg(null);

		return result;
	}
	

	@Override
	public FindAllResFilterByNameDto findAllFilterByName(String input) throws Exception {
		FindAllResFilterByNameDto result = new FindAllResFilterByNameDto();
		result.setData(permissionsDao.findAllFilterByName(input));
		result.setMsg(null);
		
		return result;
	}

	@Override
	public InsertResDto insert(Permissions data) throws Exception {
		try {
			InsertResDto insertResDto = new InsertResDto();
			InsertResDataDto insertResDataDto = new InsertResDataDto();
			data.setCreatedBy(getIdAuth());
			begin();
			Permissions permissionsSave = permissionsDao.saveOrUpdate(data);
			commit();

			insertResDataDto.setId(permissionsSave.getId());
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
	public UpdateResDto update(Permissions data) throws Exception {
		try {
			UpdateResDto updateResDto = new UpdateResDto();
			UpdateResDataDto updateResDataDto = new UpdateResDataDto();
			data.setUpdatedBy(getIdAuth());
			begin();
			Permissions permissionsUpdate = permissionsDao.saveOrUpdate(data);
			commit();

			updateResDataDto.setVersion(permissionsUpdate.getVersion());
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
			List<RolePermissions> listPermission = getUserPermission();
			boolean isForbidden = true;
			for(RolePermissions i : listPermission) {
				if(i.getPermission().getCode().equals(PermissionDeleteCode.DELETE_PERMISSION.getCode())) {
					isForbidden = false;
				}
			}
			if(isForbidden) {
				throw new ValidationIamException("Invalid Permission");
			}
			begin();
			boolean resultDelete = permissionsDao.removeById(id);
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