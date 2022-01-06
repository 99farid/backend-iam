package com.lawencon.assetsmanagement.service.impl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.assetsmanagement.constant.ActivityTrack;
import com.lawencon.assetsmanagement.constant.HeaderCode;
import com.lawencon.assetsmanagement.constant.ResponseMsg;
import com.lawencon.assetsmanagement.dao.AssetsDao;
import com.lawencon.assetsmanagement.dao.ConditionAssetsDao;
import com.lawencon.assetsmanagement.dao.DetailTransactionsInDao;
import com.lawencon.assetsmanagement.dao.GeneralTemplateDao;
import com.lawencon.assetsmanagement.dao.ProfileUsersDao;
import com.lawencon.assetsmanagement.dao.StatusAssetsDao;
import com.lawencon.assetsmanagement.dao.TrackActivityDao;
import com.lawencon.assetsmanagement.dao.TransactionsInDao;
import com.lawencon.assetsmanagement.dao.TransactionsOutDao;
import com.lawencon.assetsmanagement.dao.UsersDao;
import com.lawencon.assetsmanagement.dto.InsertResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.SendResEmailDto;
import com.lawencon.assetsmanagement.dto.transactionsin.FindAllForPdfTrxInDto;
import com.lawencon.assetsmanagement.dto.transactionsin.FindAllResTransactionsInDto;
import com.lawencon.assetsmanagement.dto.transactionsin.FindByIdResTransactionsInDto;
import com.lawencon.assetsmanagement.dto.transactionsin.InsertReqDataDetailTransactionsInDto;
import com.lawencon.assetsmanagement.dto.transactionsin.InsertReqDataHeaderTransactionsInDto;
import com.lawencon.assetsmanagement.email.EmailHandler;
import com.lawencon.assetsmanagement.helper.EmailModel;
import com.lawencon.assetsmanagement.model.Assets;
import com.lawencon.assetsmanagement.model.ConditionAssets;
import com.lawencon.assetsmanagement.model.DetailTransactionsIn;
import com.lawencon.assetsmanagement.model.GeneralTemplate;
import com.lawencon.assetsmanagement.model.ProfileUsers;
import com.lawencon.assetsmanagement.model.StatusAssets;
import com.lawencon.assetsmanagement.model.TrackActivity;
import com.lawencon.assetsmanagement.model.TransactionsIn;
import com.lawencon.assetsmanagement.model.TransactionsOut;
import com.lawencon.assetsmanagement.model.Users;
import com.lawencon.assetsmanagement.service.TransactionsInService;
import com.lawencon.assetsmanagement.util.TemplateEmailUtil;

@Service
public class TransactionsInServiceImpl extends BaseIamServiceImpl implements TransactionsInService {

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

	@Autowired
	TrackActivityDao trackDao;

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
			header.setCreatedBy(getIdAuth());
			header.setIsActive(true);
			header.setCheckInDate(LocalDate.now());

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
				detail.setCreatedBy(getIdAuth());
				detail.setIsActive(true);
				detail.setTransactionIn(header);
				detail = detailDao.saveOrUpdate(detail);

				Assets updateAssets = assetsDao.findById(detailInsert.getIdAsset());
				ConditionAssets newCondition = conditonDao.findById(detailInsert.getIdConditionAsset());
				StatusAssets newStatus = statusDao.findById(newCondition.getStatusAsset().getId());
				updateAssets.setStatusAsset(newStatus);
				updateAssets.setUpdatedBy(getIdAuth());
				updateAssets = assetsDao.saveOrUpdate(updateAssets);

				TrackActivity track = new TrackActivity();
				track.setCode(generateCodeTrack());
				track.setCodeTransaction(header.getCode());
				track.setNameAsset(updateAssets.getItem().getDescription());
				track.setStatusAsset(updateAssets.getStatusAsset().getStatusAssetName());
				track.setActivity(ActivityTrack.UPDATE_ASSET.getName());
				track.setDateActivity(LocalDate.now());
				track.setCreatedBy(getIdAuth());

				trackDao.saveOrUpdate(track);
			}
			commit();

			InsertResDataDto dataResult = new InsertResDataDto();
			dataResult.setId(header.getId());

			InsertResDto result = new InsertResDto();
			result.setData(dataResult);
			result.setMsg(ResponseMsg.SUCCESS_INSERT.getMsg());

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	private String generateCode() throws Exception {
		return HeaderCode.TRANSACTIONIN.getCode() + (transactionsInDao.countData() + 1);
	}

	@Override
	public FindAllForPdfTrxInDto findAllForPdf() throws Exception {
		FindAllForPdfTrxInDto result = new FindAllForPdfTrxInDto();
		result.setData(transactionsInDao.findAllForPdf());
		result.setMsg(null);

		return result;
	}

	@Override
	public SendResEmailDto sendFileToEmail() throws Exception {
		SendResEmailDto send = new SendResEmailDto();

		Map<String, Object> map = new HashMap<>();
		map.put("company", "PT. Lawencon Internasional");

		FindAllForPdfTrxInDto result = findAllForPdf();

		Users users = usersDao.findById(getIdAuth());

		ProfileUsers profile = profileUsersDao.findByUser(getIdAuth());

		EmailModel emailData = new EmailModel();
		emailData.setSubject("Notification Report");
		emailData.setTo(users.getEmail());
		GeneralTemplate template = templateDao.findByCode("SEND_REPORTS");
		Map<String, Object> mapReplace = templateEmailUtil.setKey("@user@", "@filename@")
				.setValue(profile.getEmployee().getFullName(), "List Transaction In").build();

		String text = templateEmailUtil.replacteTextTemplate(template.getDataTemplate(), mapReplace);
		emailData.setText(text);
		emailHandler.sendMailWithAttachmentJasper(emailData, result.getData(), "transactions-in", map);

		send.setMsg("email sent");
		return send;
	}

}
