package com.lawencon.assetsmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.assetsmanagement.dao.DetailTransactionsInDao;
import com.lawencon.assetsmanagement.dto.detailtransactionsin.FindAllResDetailTransactionInDto;
import com.lawencon.assetsmanagement.dto.detailtransactionsin.FindByIdHeaderResDetailTransactionInDto;
import com.lawencon.assetsmanagement.dto.detailtransactionsin.FindByIdResDetailTransactionInDto;
import com.lawencon.assetsmanagement.service.DetailTransactionsInService;
import com.lawencon.base.BaseServiceImpl;

@Service
public class DetailTransactionsInServiceImpl extends BaseServiceImpl implements DetailTransactionsInService{
	@Autowired
	DetailTransactionsInDao detailDao;

	@Override
	public FindAllResDetailTransactionInDto findAll() throws Exception {
		FindAllResDetailTransactionInDto result = new FindAllResDetailTransactionInDto();
		result.setData(detailDao.findAll());
		result.setMsg(null);
		
		return result;
	}

	@Override
	public FindByIdResDetailTransactionInDto findById(String id) throws Exception {
		FindByIdResDetailTransactionInDto result = new FindByIdResDetailTransactionInDto();
		result.setData(detailDao.findByIdHeader(id));
		result.setMsg(null);
		return result;
	}

	@Override
	public FindByIdHeaderResDetailTransactionInDto findByIdHeader(String idHeader) throws Exception {
		FindByIdHeaderResDetailTransactionInDto result = new FindByIdHeaderResDetailTransactionInDto();
		result.setData(detailDao.findByIdHeader(idHeader));
		result.setMsg(null);
		return result;
	}
}
