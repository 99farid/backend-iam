package com.lawencon.assetsmanagement.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.assetsmanagement.constant.ResponseMsg;
import com.lawencon.assetsmanagement.dao.ItemsDao;
import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDataDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.items.FindAllResItemsDto;
import com.lawencon.assetsmanagement.dto.items.FindByIdResItemsDto;
import com.lawencon.assetsmanagement.model.Items;
import com.lawencon.assetsmanagement.service.ItemsService;

@Service
public class ItemsServiceImpl extends BaseIamServiceImpl implements ItemsService {

	@Autowired
	private ItemsDao itemsDao;
	
	@Override
	public FindAllResItemsDto findAll() throws Exception {
		FindAllResItemsDto result = new FindAllResItemsDto();
		result.setData(itemsDao.findAll());
		result.setMsg(null);
		return result;
	}

	@Override
	public FindByIdResItemsDto findById(String id) throws Exception {
		FindByIdResItemsDto result = new FindByIdResItemsDto();
		result.setData(itemsDao.findById(id));
		result.setMsg(null);
		return result;
	}

	@Override
	public InsertResDto insert(Items data) throws Exception {
		try {
			data.setCreatedBy(getIdAuth());
			begin();
			Items item =  itemsDao.saveOrUpdate(data);
			commit();
			
			InsertResDataDto dataResult = new InsertResDataDto();
			dataResult.setId(item.getId());
			
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
	public UpdateResDto update(Items data) throws Exception {
		try {
			data.setUpdatedBy(getIdAuth());
			begin();
			Items item =  itemsDao.saveOrUpdate(data);
			commit();
			
			UpdateResDataDto dataResult = new UpdateResDataDto();
			dataResult.setVersion(item.getVersion());
			
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
			begin();
			if (!itemsDao.removeById(id)) {
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
	public BigDecimal getTotalPrice() throws Exception {
		return itemsDao.getTotalPrice();
	}

}
