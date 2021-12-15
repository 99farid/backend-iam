package com.lawencon.assetsmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.assetsmanagement.dao.DetailTransactionsOutDao;
import com.lawencon.assetsmanagement.dto.detailtransactionsout.FindAllDetailTransactionsOutDto;
import com.lawencon.assetsmanagement.dto.detailtransactionsout.FindByIdDetailTransactionsOutDto;
import com.lawencon.assetsmanagement.dto.detailtransactionsout.FindByIdResHeaderDto;
import com.lawencon.assetsmanagement.service.DetailTransactionsOutService;
import com.lawencon.base.BaseServiceImpl;

@Service
public class DetailTransactionsOutServiceImpl extends BaseServiceImpl implements DetailTransactionsOutService {

	@Autowired
	private DetailTransactionsOutDao detailTransactionsOutDao;


	@Override
	public FindAllDetailTransactionsOutDto findAll() throws Exception {
		FindAllDetailTransactionsOutDto result = new FindAllDetailTransactionsOutDto();
		result.setData( detailTransactionsOutDao.findAll());
		result.setMsg(null);	
		
		return result;
	}

	@Override
	public FindByIdDetailTransactionsOutDto findById(String id) throws Exception {
		FindByIdDetailTransactionsOutDto result = new FindByIdDetailTransactionsOutDto();
		result.setData(detailTransactionsOutDao.findById(id));
		result.setMsg(null);
		
		return result;
	}

	@Override
	public FindByIdResHeaderDto findByIdHeader(String idHeader) throws Exception {
		FindByIdResHeaderDto result = new FindByIdResHeaderDto();
		result.setData(detailTransactionsOutDao.findByIdHeader(idHeader));
		result.setMsg(null);
		
		return result;
	}
}