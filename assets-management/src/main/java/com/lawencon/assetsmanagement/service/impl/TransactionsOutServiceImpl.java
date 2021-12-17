package com.lawencon.assetsmanagement.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.assetsmanagement.dao.AssetsDao;
import com.lawencon.assetsmanagement.dao.DetailTransactionsOutDao;
import com.lawencon.assetsmanagement.dao.EmployeesDao;
import com.lawencon.assetsmanagement.dao.LocationsDao;
import com.lawencon.assetsmanagement.dao.TransactionsOutDao;
import com.lawencon.assetsmanagement.dto.InsertResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.transactionsout.FindAllResFilterByIdEmployeeDto;
import com.lawencon.assetsmanagement.dto.transactionsout.FindAllResFilterByIdGeneralItemDto;
import com.lawencon.assetsmanagement.dto.transactionsout.FindAllResFilterByIdLocationDto;
import com.lawencon.assetsmanagement.dto.transactionsout.FindAllResTransactionsOutDto;
import com.lawencon.assetsmanagement.dto.transactionsout.FindByIdResTransactionsOutDto;
import com.lawencon.assetsmanagement.dto.transactionsout.InsertReqDataDetailTransactionsOutDto;
import com.lawencon.assetsmanagement.dto.transactionsout.InsertReqDataTransactionsOutDto;
import com.lawencon.assetsmanagement.model.Assets;
import com.lawencon.assetsmanagement.model.DetailTransactionsOut;
import com.lawencon.assetsmanagement.model.Employees;
import com.lawencon.assetsmanagement.model.Locations;
import com.lawencon.assetsmanagement.model.TransactionsOut;
import com.lawencon.assetsmanagement.service.TransactionsOutService;
import com.lawencon.base.BaseServiceImpl;

@Service
public class TransactionsOutServiceImpl extends BaseServiceImpl implements TransactionsOutService {

	@Autowired
	private TransactionsOutDao transactionsOutDao;

	@Autowired
	private EmployeesDao employeesDao;

	@Autowired
	private LocationsDao locationsDao;

	@Autowired
	private AssetsDao assetsDao;

	@Autowired
	private DetailTransactionsOutDao detailTransactionsOutDao;

	@Override
	public FindAllResTransactionsOutDto findAll() throws Exception {
		FindAllResTransactionsOutDto result = new FindAllResTransactionsOutDto();
		result.setData(transactionsOutDao.findAll());
		result.setMsg(null);
		
		return result;
	}

	@Override
	public FindByIdResTransactionsOutDto findById(String id) throws Exception {
		FindByIdResTransactionsOutDto result = new FindByIdResTransactionsOutDto();
		result.setData(transactionsOutDao.findById(id));
		result.setMsg(null);
		
		return result;
	}

	@Override
	public FindAllResFilterByIdEmployeeDto findAllFilterByIdEmployee() throws Exception {
		FindAllResFilterByIdEmployeeDto result = new FindAllResFilterByIdEmployeeDto();
		result.setData(transactionsOutDao.findAllFilterByIdEmployee());
		result.setMsg(null);
		
		return result;
	}

	@Override
	public FindAllResFilterByIdLocationDto findAllFilterByIdLocation() throws Exception {
		FindAllResFilterByIdLocationDto result = new FindAllResFilterByIdLocationDto();
		result.setData(transactionsOutDao.findAllFilterByIdLocation());
		result.setMsg(null);
		
		return result;
	}

	@Override
	public FindAllResFilterByIdGeneralItemDto findAllFilterByIdGeneralItem() throws Exception {
		FindAllResFilterByIdGeneralItemDto result = new FindAllResFilterByIdGeneralItemDto();
		result.setData(transactionsOutDao.findAllFilterByIdGeneralItem());
		result.setMsg(null);

		return result;
	}
	
	@Override
	public InsertResDto insert(InsertReqDataTransactionsOutDto data) throws Exception {
		try {
			InsertResDto insertResDto = new InsertResDto();
			InsertResDataDto insertResDataDto = new InsertResDataDto();

			TransactionsOut transactionsOut = new TransactionsOut();
			transactionsOut.setCode(generateCode());

			if (data.getIdEmployee() != null && data.getIdLocation() == null && data.getIdGeneralItem() == null) {
				Employees employee = employeesDao.findById(data.getIdEmployee());
				transactionsOut.setEmployee(employee);
			} else if (data.getIdEmployee() == null && data.getIdLocation() != null && data.getIdGeneralItem() == null) {
				Locations locations = locationsDao.findById(data.getIdLocation());
				transactionsOut.setLocation(locations);
			} else if (data.getIdEmployee() == null && data.getIdLocation() == null && data.getIdGeneralItem() != null) {
				Assets generalItem = assetsDao.findById(data.getIdGeneralItem());
				transactionsOut.setGeneralItem(generalItem);
			}

			transactionsOut.setCreatedBy("....");
			transactionsOut.setIsActive(data.getIsActive());

			begin();
			TransactionsOut transactionsOutSave = transactionsOutDao.saveOrUpdate(transactionsOut);
			for (InsertReqDataDetailTransactionsOutDto detailTransactionsOutId : data.getDataDetail()) {
				DetailTransactionsOut detailTransactionsOut = new DetailTransactionsOut();
				
				Assets assets = new Assets();
				assets.setId(detailTransactionsOutId.getIdAsset());
				detailTransactionsOut.setAsset(assets);
				
				DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				LocalDate checkOutDate = LocalDate.parse(detailTransactionsOutId.getCheckOutDate(), dateTimeFormat);
				detailTransactionsOut.setCheckOutDate(checkOutDate);
				detailTransactionsOut.setIsActive(transactionsOut.getIsActive());
			
				detailTransactionsOutDao.saveOrUpdate(detailTransactionsOut);
				//findByIdAsset(id)
				//asset.setStatus(statusDao.getStatusOnAssign())
				//assetDao.saveOrUpdate();
			}

			commit();

			insertResDataDto.setId(transactionsOutSave.getId());
			insertResDto.setData(insertResDataDto);
			insertResDto.setMsg(".....");

			return insertResDto;
			
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	private String generateCode() {
		return null;
	}
}