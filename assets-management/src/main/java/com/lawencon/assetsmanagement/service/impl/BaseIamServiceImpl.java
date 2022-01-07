package com.lawencon.assetsmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.assetsmanagement.constant.ActivityTrack;
import com.lawencon.assetsmanagement.dao.RolePermissionsDao;
import com.lawencon.assetsmanagement.dao.TrackActivityDao;
import com.lawencon.assetsmanagement.dao.UsersDao;
import com.lawencon.assetsmanagement.model.RolePermissions;
import com.lawencon.assetsmanagement.model.Users;
import com.lawencon.assetsmanagement.security.AuthPrincipal;
import com.lawencon.base.BaseServiceImpl;

public class BaseIamServiceImpl extends BaseServiceImpl {

	protected AuthPrincipal authPrincipal;
	private TrackActivityDao trackDao;
	private UsersDao userDao;
	RolePermissionsDao rolePermissionDao;

	@Autowired
	public void setAuthPrincipal(AuthPrincipal authPrincipal, TrackActivityDao trackDao, 
			UsersDao userDao, RolePermissionsDao rolePermissionDao) {
		this.authPrincipal = authPrincipal;
		this.trackDao = trackDao;
		this.userDao = userDao;
		this.rolePermissionDao = rolePermissionDao;
	}

	protected String getIdAuth() throws Exception {
		if (authPrincipal.getAuthentication() == null || authPrincipal.getAuthentication().getPrincipal() == null) {
			throw new Exception("Invalid user");
		}
		return (String) authPrincipal.getAuthentication().getPrincipal();
	}
	
	protected Users getUserData() throws Exception {
		return this.userDao.findById(getIdAuth());
	}
	
	protected List<RolePermissions> getUserPermission() throws Exception {
		Users user = getUserData();
		
		
		return this.rolePermissionDao.findAllFilterByRole(user.getRole().getId());
	}

	protected String generateCodeTrack() {
		try {
			return (ActivityTrack.CODE.getName() + (trackDao.countData() + 1));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
