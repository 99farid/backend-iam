package com.lawencon.assetsmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.assetsmanagement.constant.PermissionDeleteCode;
import com.lawencon.assetsmanagement.constant.ResponseMsg;
import com.lawencon.assetsmanagement.dao.StatusAssetsDao;
import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDataDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.statusassets.FindAllFilterBySearchResStatusAsstesDto;
import com.lawencon.assetsmanagement.dto.statusassets.FindAllForNewAssetResStatusAsstesDto;
import com.lawencon.assetsmanagement.dto.statusassets.FindAllResStatusAsstesDto;
import com.lawencon.assetsmanagement.dto.statusassets.FindByIdResStatusAsstesDto;
import com.lawencon.assetsmanagement.exception.ValidationIamException;
import com.lawencon.assetsmanagement.model.RolePermissions;
import com.lawencon.assetsmanagement.model.StatusAssets;
import com.lawencon.assetsmanagement.service.StatusAssetsService;

@Service
public class StatusAssetsServiceImpl extends BaseIamServiceImpl implements StatusAssetsService {
	
	@Autowired
	StatusAssetsDao statusAssetsDao;
	
	@Override
	public FindAllResStatusAsstesDto findAll() throws Exception {
		FindAllResStatusAsstesDto result = new FindAllResStatusAsstesDto();
		result.setData(statusAssetsDao.findAll());
		result.setMsg(null);
		return result;
	}

	@Override
	public FindByIdResStatusAsstesDto findById(String id) throws Exception {
		FindByIdResStatusAsstesDto result = new FindByIdResStatusAsstesDto();
		result.setData(statusAssetsDao.findById(id));
		result.setMsg(null);				
		return result;
	}

	@Override
	public InsertResDto insert(StatusAssets data) throws Exception {
		try {
			data.setCreatedBy(getIdAuth());
			begin();
			StatusAssets status = statusAssetsDao.saveOrUpdate(data);
			commit();
			
			InsertResDataDto dataResult = new InsertResDataDto();
			dataResult.setId(status.getId());
			
			InsertResDto result = new InsertResDto();
			result.setData(dataResult);
			result.setMsg(ResponseMsg.SUCCESS_INSERT.getMsg());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		
	}

	@Override
	public UpdateResDto update(StatusAssets data) throws Exception {
		try {
			data.setUpdatedBy(getIdAuth());
			begin();
			StatusAssets status = statusAssetsDao.saveOrUpdate(data);
			commit();
			
			UpdateResDataDto dataResult = new UpdateResDataDto();
			dataResult.setVersion(status.getVersion());
			
			UpdateResDto result = new UpdateResDto();
			result.setData(dataResult);
			result.setMsg(ResponseMsg.SUCCESS_UPDATE.getMsg());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		
	}

	@Override
	public DeleteResDataDto removeById(String id) throws Exception {
		try {
			DeleteResDataDto result = new DeleteResDataDto();
			List<RolePermissions> listPermission = getUserPermission();
			boolean isForbidden = true;
			for(RolePermissions i : listPermission) {
				if(i.getPermission().getCode().equals(PermissionDeleteCode.DELETE_STATUS.getCode())) {
					isForbidden = false;
				}
			}
			if(isForbidden) {
				throw new ValidationIamException("Invalid Permission");
			}
			begin();
			if (!statusAssetsDao.removeById(id)) {
				result.setMsg(ResponseMsg.FAILED_DELETE.getMsg());
			}
			commit();
			result.setMsg(ResponseMsg.SUCCESS_DELETE.getMsg());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		
	}

	@Override
	public FindAllFilterBySearchResStatusAsstesDto findAllFilterBySearch(String input) throws Exception {
		FindAllFilterBySearchResStatusAsstesDto result = new FindAllFilterBySearchResStatusAsstesDto();
		result.setData(statusAssetsDao.findAllFilterBySearch(input));
		result.setMsg(null);
		return result;
	}

	@Override
	public FindAllForNewAssetResStatusAsstesDto findAllForNewAsset() throws Exception {
		FindAllForNewAssetResStatusAsstesDto result = new FindAllForNewAssetResStatusAsstesDto();
		result.setData(this.statusAssetsDao.findAllForNewAsset());
		result.setMsg(null);
		return result;
	}

}
