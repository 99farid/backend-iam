package com.lawencon.assetsmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.assetsmanagement.dao.DetailTransactionsOutDao;
import com.lawencon.assetsmanagement.dto.detailtransactionsout.FindAllResDetailTransactionsOutDto;
import com.lawencon.assetsmanagement.dto.detailtransactionsout.FindByIdResDetailTransactionsOutDto;
import com.lawencon.assetsmanagement.dto.detailtransactionsout.FindByIdResHeaderDto;
import com.lawencon.assetsmanagement.service.DetailTransactionsOutService;
import com.lawencon.base.BaseServiceImpl;

@Service
public class DetailTransactionsOutServiceImpl extends BaseServiceImpl implements DetailTransactionsOutService {

	@Autowired
	private DetailTransactionsOutDao detailTransactionsOutDao;


	@Override
	public FindAllResDetailTransactionsOutDto findAll() throws Exception {
		FindAllResDetailTransactionsOutDto result = new FindAllResDetailTransactionsOutDto();
		result.setData( detailTransactionsOutDao.findAll());
		result.setMsg(null);	
		
		return result;
	}

	@Override
	public FindByIdResDetailTransactionsOutDto findById(String id) throws Exception {
		FindByIdResDetailTransactionsOutDto result = new FindByIdResDetailTransactionsOutDto();
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