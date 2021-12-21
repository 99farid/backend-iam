package com.lawencon.assetsmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.assetsmanagement.constant.HeaderCode;
import com.lawencon.assetsmanagement.dao.AssetsDao;
import com.lawencon.assetsmanagement.dao.ConditionAssetsDao;
import com.lawencon.assetsmanagement.dao.DetailTransactionsInDao;
import com.lawencon.assetsmanagement.dao.StatusAssetsDao;
import com.lawencon.assetsmanagement.dao.TransactionsInDao;
import com.lawencon.assetsmanagement.dao.TransactionsOutDao;
import com.lawencon.assetsmanagement.dto.InsertResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.transactionsin.FindAllForPdfTrxInDto;
import com.lawencon.assetsmanagement.dto.transactionsin.FindAllResTransactionsInDto;
import com.lawencon.assetsmanagement.dto.transactionsin.FindByIdResTransactionsInDto;
import com.lawencon.assetsmanagement.dto.transactionsin.InsertReqDataDetailTransactionsInDto;
import com.lawencon.assetsmanagement.dto.transactionsin.InsertReqDataHeaderTransactionsInDto;
import com.lawencon.assetsmanagement.model.Assets;
import com.lawencon.assetsmanagement.model.ConditionAssets;
import com.lawencon.assetsmanagement.model.DetailTransactionsIn;
import com.lawencon.assetsmanagement.model.TransactionsIn;
import com.lawencon.assetsmanagement.model.TransactionsOut;
import com.lawencon.assetsmanagement.service.TransactionsInService;
import com.lawencon.base.BaseServiceImpl;

@Service
public class TransactionsInServiceImpl extends BaseServiceImpl implements TransactionsInService {
	
	@Autowired
	TransactionsInDao transactionsInDao;
	
	@Autowired
	DetailTransactionsInDao detailDao;
	
	@Autowired
	TransactionsOutDao transactionsOutDao;
	
	@Autowired
	AssetsDao assetsDao;
	
	@Autowired
	ConditionAssetsDao conditonDao;
	
	@Autowired
	StatusAssetsDao statusDao;
	
	@Override
	public FindAllResTransactionsInDto findAll() throws Exception {
		FindAllResTransactionsInDto result = new FindAllResTransactionsInDto();
		result.setData(transactionsInDao.findAll());
		result.setMsg(null);

		return result;
	}

	@Override
	public FindByIdResTransactionsInDto findById(String id) throws Exception {
		FindByIdResTransactionsInDto result = new FindByIdResTransactionsInDto();
		result.setData(transactionsInDao.findById(id));
		result.setMsg(null);
		return result;
	}

	@Override
	public InsertResDto insert(InsertReqDataHeaderTransactionsInDto data) throws Exception {
		try {
			TransactionsIn header = new TransactionsIn();
			TransactionsOut out = transactionsOutDao.findById(data.getIdTransactionOut());
			header.setTransactionOut(out);
			header.setCheckInDate(data.getCheckInDate());
			header.setCode(generateCode());
			header.setCreatedBy("1");
			header.setIsActive(true);
			begin();
			header = transactionsInDao.saveOrUpdate(header);
			
			for (InsertReqDataDetailTransactionsInDto detailInsert : data.getDetailData()) {
				DetailTransactionsIn detail = new DetailTransactionsIn();
				Assets asset = new Assets();
				asset.setId(detailInsert.getIdAsset());
				
				ConditionAssets conditon = new ConditionAssets();
				conditon.setId(detailInsert.getIdConditionAsset());
				
				detail.setAsset(asset);
				detail.setConditionAsset(conditon);
				detail.setCreatedBy("1");
				detail.setIsActive(true);
				detail.setTransactionIn(header);
				detail = detailDao.saveOrUpdate(detail);
				//update status asset
				
				Assets updateAssets = assetsDao.findById(detailInsert.getIdAsset());
				ConditionAssets newCondition = conditonDao.findById(detailInsert.getIdConditionAsset());
				updateAssets.setStatusAsset(statusDao.findById(newCondition.getStatusAsset().getId()));
				updateAssets = assetsDao.saveOrUpdate(updateAssets);
			}
			commit();
			InsertResDataDto dataResult = new InsertResDataDto();
			dataResult.setId(header.getId());
			
			InsertResDto result = new InsertResDto();
			result.setData(dataResult);
			result.setMsg("");
			
			return result;
		}catch(Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
			
		
	}
	
	private String generateCode() throws Exception{
		return HeaderCode.TRANSACTIONIN.getCode() + (transactionsInDao.countData() + 1);
	}

	@Override
	public FindAllForPdfTrxInDto findAllForPdf() throws Exception {
		FindAllForPdfTrxInDto result = new FindAllForPdfTrxInDto();
		result.setData(transactionsInDao.findAllForPdf());
		result.setMsg(null);
		
		return result;
	}

	
}
