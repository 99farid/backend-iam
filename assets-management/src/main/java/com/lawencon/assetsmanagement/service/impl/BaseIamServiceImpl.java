package com.lawencon.assetsmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.assetsmanagement.constant.ActivityTrack;
import com.lawencon.assetsmanagement.dao.TrackActivityDao;
import com.lawencon.assetsmanagement.security.AuthPrincipal;
import com.lawencon.base.BaseServiceImpl;

public class BaseIamServiceImpl extends BaseServiceImpl {

	protected AuthPrincipal authPrincipal;
	private TrackActivityDao trackDao;

	@Autowired
	public void setAuthPrincipal(AuthPrincipal authPrincipal) {
		this.authPrincipal = authPrincipal;
	}

	protected String getIdAuth() throws Exception {
		if (authPrincipal.getAuthentication() == null || authPrincipal.getAuthentication().getPrincipal() == null) {
			throw new Exception("invalid user");
		}
		return (String) authPrincipal.getAuthentication().getPrincipal();
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
