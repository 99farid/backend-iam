package com.lawencon.assetsmanagement.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.assetsmanagement.dao.DetailTransactionsOutDao;
import com.lawencon.assetsmanagement.dao.EmployeesDao;
import com.lawencon.assetsmanagement.dao.UsersDao;
import com.lawencon.assetsmanagement.dto.SendResEmailDto;
import com.lawencon.assetsmanagement.dto.detailtransactionsout.FindAllForPdfTrxExpiredDto;
import com.lawencon.assetsmanagement.dto.detailtransactionsout.FindAllResDetailTransactionsOutDto;
import com.lawencon.assetsmanagement.dto.detailtransactionsout.FindByIdResDetailTransactionsOutDto;
import com.lawencon.assetsmanagement.dto.detailtransactionsout.FindByIdResHeaderDto;
import com.lawencon.assetsmanagement.email.EmailHandler;
import com.lawencon.assetsmanagement.helper.EmailModel;
import com.lawencon.assetsmanagement.model.DetailTransactionsOut;
import com.lawencon.assetsmanagement.model.Employees;
import com.lawencon.assetsmanagement.model.Users;
import com.lawencon.assetsmanagement.service.DetailTransactionsOutService;

@Service
public class DetailTransactionsOutServiceImpl extends BaseIamServiceImpl implements DetailTransactionsOutService {

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
//					
//					emailHandler.sendEmail(reciever.getEmail(), "Reminder Due Date Asset", detail.getDueDate().toString());
//					
//					emailHandler.sendEmail(sender.getEmail(), "Reminder Due Date Asset", detail.getDueDate().toString());
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
	public FindAllForPdfTrxExpiredDto findAllForPdf() throws Exception {
		FindAllForPdfTrxExpiredDto result = new FindAllForPdfTrxExpiredDto();
		result.setData(detailTransactionsOutDao.findAllForPdf());
		result.setMsg(null);
		
		return result;
	}

	@Override
	public SendResEmailDto sendFileToEmail() throws Exception {
		SendResEmailDto send = new SendResEmailDto();
		
		Map<String, Object> map = new HashMap<>();
		map.put("company", "PT. Lawencon Internasional");
        
		FindAllForPdfTrxExpiredDto result = findAllForPdf();
        
        EmailModel email = new EmailModel();
        
        Users users = usersDao.findById(getIdAuth());
        email.setTo(users.getEmail());
        email.setSubject("Report Transaction Due Date");
        email.setText("This is report notification to inform you about report transaction due date");
     
		emailHandler.sendMailWithAttachmentJasper(email, result.getData(), "transaction-expired", map);
		
		send.setMsg("email sent");	
		return send;
	}

	@Override
	public FindByIdResHeaderDto findByIdHeaderForCheckIn(String idHeader) throws Exception {
		FindByIdResHeaderDto result = new FindByIdResHeaderDto();
		result.setData(detailTransactionsOutDao.findByIdHeaderForCheckIn(idHeader));
		result.setMsg(null);
		
		return result;
	}
	
}