package com.lawencon.assetsmanagement.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.assetsmanagement.dao.GeneralTemplateDao;
import com.lawencon.assetsmanagement.dao.ProfileUsersDao;
import com.lawencon.assetsmanagement.dao.TrackActivityDao;
import com.lawencon.assetsmanagement.dao.UsersDao;
import com.lawencon.assetsmanagement.dto.SendResEmailDto;
import com.lawencon.assetsmanagement.dto.trackactivity.FindAllResTrackActivityDto;
import com.lawencon.assetsmanagement.dto.trackactivity.FindByIdResTrackActivityDto;
import com.lawencon.assetsmanagement.email.EmailHandler;
import com.lawencon.assetsmanagement.helper.EmailModel;
import com.lawencon.assetsmanagement.model.GeneralTemplate;
import com.lawencon.assetsmanagement.model.ProfileUsers;
import com.lawencon.assetsmanagement.model.Users;
import com.lawencon.assetsmanagement.service.TrackActivityService;
import com.lawencon.assetsmanagement.util.TemplateEmailUtil;

@Service
public class TrackActivityServiceImpl extends BaseIamServiceImpl implements TrackActivityService {

	@Autowired
	private TrackActivityDao trackActivityDao;
	
	@Autowired
	private EmailHandler emailHandler;
	
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private ProfileUsersDao profileUsersDao;
	
	@Autowired
	private GeneralTemplateDao templateDao;
	
	@Autowired
	private TemplateEmailUtil templateEmailUtil;
	
	@Override
	public FindAllResTrackActivityDto findAll() throws Exception {
		FindAllResTrackActivityDto result = new FindAllResTrackActivityDto();
		result.setData(trackActivityDao.findAll());
		result.setMsg(null);
		
		return result;
	}

	@Override
	public FindByIdResTrackActivityDto findById(String id) throws Exception {
		FindByIdResTrackActivityDto result = new FindByIdResTrackActivityDto();
		result.setData(trackActivityDao.findById(id));
		result.setMsg(null);
		
		return result;
	}

	@Override
	public SendResEmailDto sendFileToEmail() throws Exception {
	SendResEmailDto send = new SendResEmailDto();
		
		Map<String, Object> map = new HashMap<>();
		map.put("company", "PT. Lawencon Internasional");
        
		FindAllResTrackActivityDto result = findAll();
               
        Users users = usersDao.findById(getIdAuth());

		ProfileUsers profile = profileUsersDao.findByUser(getIdAuth());

		EmailModel emailData = new EmailModel();
		emailData.setSubject("Notification Report");
		emailData.setTo(users.getEmail());
		GeneralTemplate template = templateDao.findByCode("SEND_REPORTS");
		Map<String, Object> mapReplace = templateEmailUtil.setKey("@user@", "@filename@")
				.setValue(profile.getEmployee().getFullName(), "Track Activity").build();

		String text = templateEmailUtil.replacteTextTemplate(template.getDataTemplate(), mapReplace);
		emailData.setText(text);
		emailHandler.sendMailWithAttachmentJasper(emailData, result.getData(), "track-activity", map);
		
		send.setMsg("email sent");	
		return send;
	}
}
