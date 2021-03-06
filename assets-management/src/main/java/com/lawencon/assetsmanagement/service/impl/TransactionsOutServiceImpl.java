package com.lawencon.assetsmanagement.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.assetsmanagement.constant.ActivityTrack;
import com.lawencon.assetsmanagement.constant.HeaderCode;
import com.lawencon.assetsmanagement.constant.ResponseMsg;
import com.lawencon.assetsmanagement.dao.AssetsDao;
import com.lawencon.assetsmanagement.dao.DetailTransactionsOutDao;
import com.lawencon.assetsmanagement.dao.EmployeesDao;
import com.lawencon.assetsmanagement.dao.GeneralTemplateDao;
import com.lawencon.assetsmanagement.dao.LocationsDao;
import com.lawencon.assetsmanagement.dao.ProfileUsersDao;
import com.lawencon.assetsmanagement.dao.StatusAssetsDao;
import com.lawencon.assetsmanagement.dao.TrackActivityDao;
import com.lawencon.assetsmanagement.dao.TransactionsOutDao;
import com.lawencon.assetsmanagement.dao.UsersDao;
import com.lawencon.assetsmanagement.dto.InsertResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.SendResEmailDto;
import com.lawencon.assetsmanagement.dto.transactionsout.FindAllForPdfTrxOutDto;
import com.lawencon.assetsmanagement.dto.transactionsout.FindAllResFilterByIdEmployeeDto;
import com.lawencon.assetsmanagement.dto.transactionsout.FindAllResFilterByIdGeneralItemDto;
import com.lawencon.assetsmanagement.dto.transactionsout.FindAllResFilterByIdLocationDto;
import com.lawencon.assetsmanagement.dto.transactionsout.FindAllResTransactionsOutDto;
import com.lawencon.assetsmanagement.dto.transactionsout.FindByIdResTransactionsOutDto;
import com.lawencon.assetsmanagement.dto.transactionsout.InsertReqDataDetailTransactionsOutDto;
import com.lawencon.assetsmanagement.dto.transactionsout.InsertReqDataTransactionsOutDto;
import com.lawencon.assetsmanagement.email.EmailHandler;
import com.lawencon.assetsmanagement.helper.EmailModel;
import com.lawencon.assetsmanagement.model.Assets;
import com.lawencon.assetsmanagement.model.DetailTransactionsOut;
import com.lawencon.assetsmanagement.model.Employees;
import com.lawencon.assetsmanagement.model.GeneralTemplate;
import com.lawencon.assetsmanagement.model.Locations;
import com.lawencon.assetsmanagement.model.ProfileUsers;
import com.lawencon.assetsmanagement.model.StatusAssets;
import com.lawencon.assetsmanagement.model.TrackActivity;
import com.lawencon.assetsmanagement.model.TransactionsOut;
import com.lawencon.assetsmanagement.model.Users;
import com.lawencon.assetsmanagement.service.TransactionsOutService;
import com.lawencon.assetsmanagement.util.TemplateEmailUtil;

@Service
public class TransactionsOutServiceImpl extends BaseIamServiceImpl implements TransactionsOutService {

	@Autowired
	private TransactionsOutDao transactionsOutDao;

	@Autowired
	private EmployeesDao employeesDao;

	@Autowired
	private LocationsDao locationsDao;

	@Autowired
	private AssetsDao assetsDao;

	@Autowired
	private StatusAssetsDao statusAssetsDao;

	@Autowired
	private DetailTransactionsOutDao detailTransactionsOutDao;

	@Autowired
	private TrackActivityDao trackDao;
	
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
	public FindAllResTransactionsOutDto findAll() throws Exception {
		FindAllResTransactionsOutDto result = new FindAllResTransactionsOutDto();

		List<TransactionsOut> data = transactionsOutDao.findAll();

		result.setData(data);
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
			} else if (data.getIdEmployee() == null && data.getIdLocation() != null
					&& data.getIdGeneralItem() == null) {
				Locations locations = locationsDao.findById(data.getIdLocation());
				transactionsOut.setLocation(locations);
			} else if (data.getIdEmployee() == null && data.getIdLocation() == null
					&& data.getIdGeneralItem() != null) {
				Assets generalItem = assetsDao.findById(data.getIdGeneralItem());
				transactionsOut.setGeneralItem(generalItem);
			}

			transactionsOut.setCheckOutDate(LocalDate.now());
			transactionsOut.setCreatedBy(getIdAuth());
			transactionsOut.setIsActive(true);

			begin();
			TransactionsOut transactionsOutSave = transactionsOutDao.saveOrUpdate(transactionsOut);
			for (InsertReqDataDetailTransactionsOutDto detailTransactionsOutId : data.getDataDetail()) {
				DetailTransactionsOut detailTransactionsOut = new DetailTransactionsOut();
				detailTransactionsOut.setTransactionOut(transactionsOutSave);

				Assets assets = new Assets();
				assets.setId(detailTransactionsOutId.getIdAsset());
				detailTransactionsOut.setAsset(assets);

				DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate dueDate = LocalDate.parse(detailTransactionsOutId.getDueDate(), dtFormatter);
				detailTransactionsOut.setDueDate(dueDate);
				detailTransactionsOut.setCreatedBy(getIdAuth());
				detailTransactionsOut.setStatusEmail(false);
				detailTransactionsOut.setIsActive(true);
				detailTransactionsOutDao.saveOrUpdate(detailTransactionsOut);

				Assets updateAsset = assetsDao.findById(assets.getId());

				StatusAssets statusAsset = statusAssetsDao.findOnAssignStatus();

				updateAsset.setStatusAsset(statusAsset);
				updateAsset = assetsDao.saveOrUpdate(updateAsset);

				TrackActivity track = new TrackActivity();
				track.setCode(generateCodeTrack());
				track.setCodeTransaction(transactionsOutSave.getCode());
				track.setNameAsset(updateAsset.getItem().getDescription());
				track.setStatusAsset(updateAsset.getStatusAsset().getStatusAssetName());
				track.setActivity(ActivityTrack.UPDATE_ASSET.getName());
				track.setDateActivity(LocalDate.now());
				track.setCreatedBy(getIdAuth());

				trackDao.saveOrUpdate(track);
			}

			commit();

			insertResDataDto.setId(transactionsOutSave.getId());
			insertResDto.setData(insertResDataDto);
			insertResDto.setMsg(ResponseMsg.SUCCESS_INSERT.getMsg());

			return insertResDto;

		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	private String generateCode() throws Exception {
		return HeaderCode.TRANSACTIONOUT.getCode() + (transactionsOutDao.countData() + 1);
	}

	@Override
	public FindAllForPdfTrxOutDto findAllForPdf() throws Exception {
		FindAllForPdfTrxOutDto result = new FindAllForPdfTrxOutDto();
		result.setData(transactionsOutDao.findAllForPdf());
		result.setMsg(null);

		return result;
	}

	@Override
	public SendResEmailDto sendFileToEmail() throws Exception {
		SendResEmailDto send = new SendResEmailDto();

		Map<String, Object> map = new HashMap<>();
		map.put("company", "PT. Lawencon Internasional");

		FindAllForPdfTrxOutDto result = findAllForPdf();

		Users users = usersDao.findById(getIdAuth());
		
		ProfileUsers profile = profileUsersDao.findByUser(getIdAuth());
		
		EmailModel emailData = new EmailModel();
		emailData.setSubject("Notification Report");
		emailData.setTo(users.getEmail());
		GeneralTemplate template = templateDao.findByCode("SEND_REPORTS");
		Map<String, Object> mapReplace = templateEmailUtil.setKey("@user@", "@filename@").setValue(profile.getEmployee().getFullName(), "List Transaction Out").build();

		String text = templateEmailUtil.replacteTextTemplate(template.getDataTemplate(), mapReplace);
		emailData.setText(text);
		emailHandler.sendMailWithAttachmentJasper(emailData, result.getData(), "transactions-out", map);

		send.setMsg("Email Sent");
		return send;
	}

}
