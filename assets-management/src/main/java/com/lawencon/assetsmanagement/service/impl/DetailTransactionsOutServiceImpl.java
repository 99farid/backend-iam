package com.lawencon.assetsmanagement.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.assetsmanagement.dao.DetailTransactionsOutDao;
import com.lawencon.assetsmanagement.dao.UsersDao;
import com.lawencon.assetsmanagement.dto.SendResEmailDto;
import com.lawencon.assetsmanagement.dto.detailtransactionsout.FindAllForPdfTrxExpiredDto;
import com.lawencon.assetsmanagement.dto.detailtransactionsout.FindAllResDetailTransactionsOutDto;
import com.lawencon.assetsmanagement.dto.detailtransactionsout.FindByIdResDetailTransactionsOutDto;
import com.lawencon.assetsmanagement.dto.detailtransactionsout.FindByIdResHeaderDto;
import com.lawencon.assetsmanagement.email.EmailHandler;
import com.lawencon.assetsmanagement.email.EmailModel;
import com.lawencon.assetsmanagement.model.Users;
import com.lawencon.assetsmanagement.service.DetailTransactionsOutService;

@Service
public class DetailTransactionsOutServiceImpl extends BaseIamServiceImpl implements DetailTransactionsOutService {

	@Autowired
	private DetailTransactionsOutDao detailTransactionsOutDao;
	
	@Autowired
	private EmailHandler emailHandler;
	
	@Autowired
	private UsersDao usersDao;

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
	
}