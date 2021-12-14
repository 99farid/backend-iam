package com.lawencon.assetsmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.assetsmanagement.dao.ItemTypesDao;
import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDataDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.model.ItemTypes;
import com.lawencon.assetsmanagement.service.ItemTypesService;
import com.lawencon.base.BaseServiceImpl;

@Service
public class ItemTypesServiceImpl extends BaseServiceImpl implements ItemTypesService{
	@Autowired	
	ItemTypesDao itemTypesDao;
	
	@Override
	public List<ItemTypes> findAll() throws Exception {
		return itemTypesDao.findAll();
	}

	@Override
	public ItemTypes findById(String id) throws Exception {
		return itemTypesDao.findById(id);
	}

	@Override
	public InsertResDto insert(ItemTypes data) throws Exception {
		begin();
		ItemTypes type = itemTypesDao.saveOrUpdate(data);
		commit();
		InsertResDataDto dataResult = new InsertResDataDto();
		dataResult.setId(type.getId());
		
		InsertResDto result = new InsertResDto();
		result.setData(dataResult);
		result.setMsg("");
		
		return result;
	}

	@Override
	public UpdateResDto update(ItemTypes data) throws Exception {
		begin();
		ItemTypes type = itemTypesDao.saveOrUpdate(data);
		commit();
		UpdateResDataDto dataResult = new UpdateResDataDto();
		dataResult.setVersion(type.getVersion());
		
		UpdateResDto result = new UpdateResDto();
		result.setData(dataResult);
		result.setMsg("");
		
		return result;
	}

	@Override
	public DeleteResDataDto removeById(String id) throws Exception {
		DeleteResDataDto result = new DeleteResDataDto();
		begin();
		if (!itemTypesDao.removeById(id)) {
			result.setMsg("");
		}
		commit();
		result.setMsg("");
		return result;
	}

}
