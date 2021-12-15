package com.lawencon.assetsmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.assetsmanagement.dao.DetailTransactionsInDao;
import com.lawencon.assetsmanagement.model.DetailTransactionsIn;
import com.lawencon.assetsmanagement.service.DetailTransactionsInService;
import com.lawencon.base.BaseServiceImpl;

@Service
public class DetailTransactionsInServiceImpl extends BaseServiceImpl implements DetailTransactionsInService{
	@Autowired
	DetailTransactionsInDao detailDao;

	@Override
	public List<DetailTransactionsIn> findAll() throws Exception {
		return detailDao.findAll();
	}

	@Override
	public DetailTransactionsIn findById(String id) throws Exception {
		return detailDao.findById(id);
	}

	@Override
	public List<DetailTransactionsIn> findByIdHeader(String idHeader) throws Exception {
		return detailDao.findByIdHeader(idHeader);
	}
}
