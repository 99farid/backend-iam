package com.lawencon.assetsmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.assetsmanagement.dao.ItemsDao;
import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDataDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.model.Items;
import com.lawencon.assetsmanagement.service.ItemsService;
import com.lawencon.base.BaseServiceImpl;

@Service
public class ItemsServiceImpl extends BaseServiceImpl implements ItemsService {

	@Autowired
	private ItemsDao itemsDao;
	
	@Override
	public List<Items> findAll() throws Exception {
		return itemsDao.findAll();
	}

	@Override
	public Items findById(String id) throws Exception {
		return itemsDao.findById(id);
	}

	@Override
	public InsertResDto insert(Items data) throws Exception {
		begin();
		Items item =  itemsDao.saveOrUpdate(data);
		commit();
		
		InsertResDataDto dataResult = new InsertResDataDto();
		dataResult.setId(item.getId());
		
		InsertResDto result = new InsertResDto();
		result.setData(dataResult);
		result.setMsg("");
		
		return result;
	}

	@Override
	public UpdateResDto update(Items data) throws Exception {
		begin();
		Items item =  itemsDao.saveOrUpdate(data);
		commit();
		
		UpdateResDataDto dataResult = new UpdateResDataDto();
		dataResult.setVersion(item.getVersion());
		
		UpdateResDto result = new UpdateResDto();
		result.setData(dataResult);
		result.setMsg("");
		
		return result;
	}

	@Override
	public DeleteResDataDto removeById(String id) throws Exception {
		DeleteResDataDto result = new DeleteResDataDto();
		begin();
		if (!itemsDao.removeById(id)) {
			result.setMsg("");
		}
		commit();
		result.setMsg("");
		return result;
	}

}
