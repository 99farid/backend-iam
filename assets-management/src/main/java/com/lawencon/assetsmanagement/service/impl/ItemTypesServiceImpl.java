package com.lawencon.assetsmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.assetsmanagement.dao.ItemTypesDao;
import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDataDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.itemtypes.FindAllResItemTypesDto;
import com.lawencon.assetsmanagement.dto.itemtypes.FindByIdResItemTypesDto;
import com.lawencon.assetsmanagement.model.ItemTypes;
import com.lawencon.assetsmanagement.service.ItemTypesService;
import com.lawencon.base.BaseServiceImpl;

@Service
public class ItemTypesServiceImpl extends BaseServiceImpl implements ItemTypesService{
	@Autowired	
	ItemTypesDao itemTypesDao;
	
	@Override
	public FindAllResItemTypesDto findAll() throws Exception {
		FindAllResItemTypesDto result = new FindAllResItemTypesDto();
		result.setData(itemTypesDao.findAll());
		result.setMsg(null);		
		return result;
	}

	@Override
	public FindByIdResItemTypesDto findById(String id) throws Exception {
		FindByIdResItemTypesDto result = new FindByIdResItemTypesDto();
		result.setData(itemTypesDao.findById(id));
		result.setMsg(null);
		return result;
	}

	@Override
	public InsertResDto insert(ItemTypes data) throws Exception {
		try {
			begin();
			ItemTypes type = itemTypesDao.saveOrUpdate(data);
			commit();
			InsertResDataDto dataResult = new InsertResDataDto();
			dataResult.setId(type.getId());
			
			InsertResDto result = new InsertResDto();
			result.setData(dataResult);
			result.setMsg("");
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		
	}

	@Override
	public UpdateResDto update(ItemTypes data) throws Exception {
		try {
			begin();
			ItemTypes type = itemTypesDao.saveOrUpdate(data);
			commit();
			UpdateResDataDto dataResult = new UpdateResDataDto();
			dataResult.setVersion(type.getVersion());
			
			UpdateResDto result = new UpdateResDto();
			result.setData(dataResult);
			result.setMsg("");
			
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
			begin();
			if (!itemTypesDao.removeById(id)) {
				result.setMsg("");
			}
			commit();
			result.setMsg("");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		
	}

}
