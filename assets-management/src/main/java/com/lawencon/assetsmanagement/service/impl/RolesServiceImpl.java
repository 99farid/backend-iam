package com.lawencon.assetsmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.assetsmanagement.dao.PermissionsDao;
import com.lawencon.assetsmanagement.dao.RolePermissionsDao;
import com.lawencon.assetsmanagement.dao.RolesDao;
import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDataDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.roles.InsertReqDataRolesDto;
import com.lawencon.assetsmanagement.dto.roles.UpdateReqRolesDto;
import com.lawencon.assetsmanagement.model.Permissions;
import com.lawencon.assetsmanagement.model.RolePermissions;
import com.lawencon.assetsmanagement.model.Roles;
import com.lawencon.assetsmanagement.service.RolesService;
import com.lawencon.base.BaseServiceImpl;

@Service
public class RolesServiceImpl extends BaseServiceImpl implements RolesService {

	@Autowired
	private RolesDao rolesDao;
	
	@Autowired
	private RolePermissionsDao rolePermissionsDao;
	
	public List<Roles> findAll() throws Exception {
		return rolesDao.findAll();
	}
	
	public Roles findById(String id) throws Exception {
		return rolesDao.findById(id);
	}
	
	public InsertResDto insert(InsertReqDataRolesDto data) throws Exception {
		InsertResDto insertResDto = new InsertResDto();
		InsertResDataDto insertResDataDto = new InsertResDataDto();
		
		Roles role = new Roles();
		role.setCode(data.getCode());
		role.setRoleName(data.getRoleName());
		role.setCreatedBy(".....");
		role.setIsActive(data.getIsActive());
		
		begin();
		Roles rolesSave = rolesDao.saveOrUpdate(role);
	
		for(String permissionId: data.getIdPermission()) {
			RolePermissions rolepermission = new RolePermissions();
			rolepermission.setRole(rolesSave);
			
			Permissions permission = new Permissions();
			permission.setId(permissionId);
			rolepermission.setPermission(permission);
			
			rolePermissionsDao.saveOrUpdate(rolepermission);
		}
		
		commit();
		
		insertResDataDto.setId(rolesSave.getId());
		insertResDto.setData(insertResDataDto);
		insertResDto.setMsg("....");
		
		return insertResDto;
	}

	@Override
	public UpdateResDto update(UpdateReqRolesDto data) throws Exception {
		UpdateResDto updateResDto = new UpdateResDto();
		UpdateResDataDto updateResDataDto = new UpdateResDataDto();
		
		Roles role = rolesDao.findById(data.getId());
		
		role.setRoleName(data.getRoleName());
		role.setUpdatedBy(null);
		role.setIsActive(role.getIsActive());
		role.setVersion(data.getVersion());
		
		begin();
		Roles rolesUpdate = rolesDao.saveOrUpdate(role);
		commit();
		
		updateResDataDto.setVersion(rolesUpdate.getVersion());
		updateResDto.setData(updateResDataDto);
		updateResDto.setMsg(null);
		
		return updateResDto;
	}

	@Override
	public DeleteResDataDto removeById(String id) throws Exception {
		DeleteResDataDto deleteResDataDto = new DeleteResDataDto();
		
		begin();
		boolean resultDelete = rolesDao.removeById(id);
		commit();
		
		if (resultDelete) {
			deleteResDataDto.setMsg("");
		} else {
			deleteResDataDto.setMsg("");
		}
		
		return deleteResDataDto;
	}
	
}