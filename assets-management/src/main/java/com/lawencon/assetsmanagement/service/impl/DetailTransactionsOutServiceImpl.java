package com.lawencon.assetsmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.assetsmanagement.dao.DetailTransactionsOutDao;
import com.lawencon.assetsmanagement.dao.EmployeesDao;
import com.lawencon.assetsmanagement.dao.UsersDao;
import com.lawencon.assetsmanagement.dto.detailtransactionsout.FindAllResDetailTransactionsOutDto;
import com.lawencon.assetsmanagement.dto.detailtransactionsout.FindByIdResDetailTransactionsOutDto;
import com.lawencon.assetsmanagement.dto.detailtransactionsout.FindByIdResHeaderDto;
import com.lawencon.assetsmanagement.email.EmailHandler;
import com.lawencon.assetsmanagement.model.DetailTransactionsOut;
import com.lawencon.assetsmanagement.model.Employees;
import com.lawencon.assetsmanagement.model.Users;
import com.lawencon.assetsmanagement.service.DetailTransactionsOutService;
import com.lawencon.base.BaseServiceImpl;

@Service
public class DetailTransactionsOutServiceImpl extends BaseServiceImpl implements DetailTransactionsOutService {

	@Autowired
	private DetailTransactionsOutDao detailTransactionsOutDao;
	
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private EmployeesDao employeesDao;
	
	@Autowired
	private EmailHandler emailHandler;
	
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

	@Override
	public void sendReportDueDate() throws Exception {
		List<DetailTransactionsOut> detailList = detailTransactionsOutDao.findAllForDueReport();
		if(detailList != null ) {
			for(DetailTransactionsOut detail : detailList) {
				if(detail.getTransactionOut().getEmployee() != null) {
					Employees reciever = employeesDao.findById(detail.getTransactionOut().getEmployee().getId());
					Users sender = usersDao.findById(detail.getCreatedBy());
					
					emailHandler.sendSimpleMessage(reciever.getEmail(), "Reminder Due Date Asset", detail.getDueDate().toString());
					
					emailHandler.sendSimpleMessage(sender.getEmail(), "Reminder Due Date Asset", detail.getDueDate().toString());
					Users system = usersDao.getSystem();
					detail.setUpdatedBy(system.getId());
					detail.setStatusEmail(true);
					
					begin();
					detailTransactionsOutDao.saveOrUpdate(detail);
					commit();
					
				}
				
			}
		}
		
	}
}