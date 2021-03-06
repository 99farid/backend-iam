package com.lawencon.assetsmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.assetsmanagement.constant.PermissionDeleteCode;
import com.lawencon.assetsmanagement.constant.ResponseMsg;
import com.lawencon.assetsmanagement.dao.RolePermissionsDao;
import com.lawencon.assetsmanagement.dao.RolesDao;
import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDataDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.roles.FindAllResRolesDto;
import com.lawencon.assetsmanagement.dto.roles.FindByIdResRolesDto;
import com.lawencon.assetsmanagement.dto.roles.InsertReqDataRolesDto;
import com.lawencon.assetsmanagement.dto.roles.UpdateReqRolesDto;
import com.lawencon.assetsmanagement.exception.ValidationIamException;
import com.lawencon.assetsmanagement.model.Permissions;
import com.lawencon.assetsmanagement.model.RolePermissions;
import com.lawencon.assetsmanagement.model.Roles;
import com.lawencon.assetsmanagement.service.RolesService;

@Service
public class RolesServiceImpl extends BaseIamServiceImpl implements RolesService {

	@Autowired
	private RolesDao rolesDao;
	
	@Autowired
	private RolePermissionsDao rolePermissionsDao;
	
	public FindAllResRolesDto findAll() throws Exception {
		FindAllResRolesDto result = new FindAllResRolesDto();
		result.setData(rolesDao.findAll());
		result.setMsg(null);
		
		return result;
	}
	
	public FindByIdResRolesDto findById(String id) throws Exception {
		FindByIdResRolesDto result = new FindByIdResRolesDto();
		result.setData(rolesDao.findById(id));
		result.setMsg(null);
		
		return result;
	}
	
	public InsertResDto insert(InsertReqDataRolesDto data) throws Exception {
		try {
			InsertResDto insertResDto = new InsertResDto();
			InsertResDataDto insertResDataDto = new InsertResDataDto();
			
			Roles role = new Roles();
			role.setCode(data.getCode());
			role.setRoleName(data.getRoleName());
			role.setCreatedBy(getIdAuth());
			role.setIsActive(true);
		
			begin();
			Roles rolesSave = rolesDao.saveOrUpdate(role);
		
			for(String permissionId: data.getIdPermission()) {
				RolePermissions rolepermission = new RolePermissions();
				rolepermission.setRole(rolesSave);
				rolepermission.setCreatedBy(getIdAuth());
				rolepermission.setIsActive(true);
				
				Permissions permission = new Permissions();
				permission.setId(permissionId);
				rolepermission.setPermission(permission);
				
				rolePermissionsDao.saveOrUpdate(rolepermission);
			}
			
			commit();
			
			insertResDataDto.setId(rolesSave.getId());
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
	public UpdateResDto update(UpdateReqRolesDto data) throws Exception {
		try {
			UpdateResDto updateResDto = new UpdateResDto();
			UpdateResDataDto updateResDataDto = new UpdateResDataDto();
				
			Roles role = rolesDao.findById(data.getId());
			
			role.setRoleName(data.getRoleName());
			role.setUpdatedBy(getIdAuth());
			role.setIsActive(role.getIsActive());
			role.setVersion(data.getVersion());
			
			begin();
			Roles rolesUpdate = rolesDao.saveOrUpdate(role);
			commit();
			
			updateResDataDto.setVersion(rolesUpdate.getVersion());
			updateResDto.setData(updateResDataDto);
			updateResDto.setMsg(null);
			
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
				if(i.getPermission().getCode().equals(PermissionDeleteCode.DELETE_ROLE.getCode())) {
					isForbidden = false;
				}
			}
			if(isForbidden) {
				throw new ValidationIamException("Invalid Permission");
			}
			begin();
			boolean resultDelete = rolesDao.removeById(id);
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