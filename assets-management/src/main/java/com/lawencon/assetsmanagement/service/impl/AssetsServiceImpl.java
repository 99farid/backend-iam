package com.lawencon.assetsmanagement.service.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.assetsmanagement.constant.ActivityTrack;
import com.lawencon.assetsmanagement.dao.AssetsDao;
import com.lawencon.assetsmanagement.dao.CompaniesDao;
import com.lawencon.assetsmanagement.dao.FilesDao;
import com.lawencon.assetsmanagement.dao.InvoicesDao;
import com.lawencon.assetsmanagement.dao.ItemTypesDao;
import com.lawencon.assetsmanagement.dao.ItemsDao;
import com.lawencon.assetsmanagement.dao.StatusAssetsDao;
import com.lawencon.assetsmanagement.dao.TrackActivityDao;
import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDataDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.assets.CountAssetByStatusResAssetsDto;
import com.lawencon.assetsmanagement.dto.assets.CountAssetResAssetsDto;
import com.lawencon.assetsmanagement.dto.assets.FindAllFilterBySearchResAssetsDto;
import com.lawencon.assetsmanagement.dto.assets.FindAllFilterByTypeResAssetsDto;
import com.lawencon.assetsmanagement.dto.assets.FindAllForPdfAssetsExpiredDto;
import com.lawencon.assetsmanagement.dto.assets.FindAllResAssetsDto;
import com.lawencon.assetsmanagement.dto.assets.FindByIdResAssetsDto;
import com.lawencon.assetsmanagement.dto.assets.InsertReqDataAssetsDto;
import com.lawencon.assetsmanagement.model.Assets;
import com.lawencon.assetsmanagement.model.Companies;
import com.lawencon.assetsmanagement.model.Files;
import com.lawencon.assetsmanagement.model.Invoices;
import com.lawencon.assetsmanagement.model.ItemTypes;
import com.lawencon.assetsmanagement.model.Items;
import com.lawencon.assetsmanagement.model.StatusAssets;
import com.lawencon.assetsmanagement.model.TrackActivity;
import com.lawencon.assetsmanagement.service.AssetsService;
import com.lawencon.base.BaseServiceImpl;

@Service
public class AssetsServiceImpl extends BaseServiceImpl implements AssetsService {

	@Autowired
	private AssetsDao assetsDao;

	@Autowired
	private CompaniesDao companiesDao;

	@Autowired
	private StatusAssetsDao statusAssetsDao;

	@Autowired
	private InvoicesDao invoicesDao;

	@Autowired
	private ItemsDao itemsDao;

	@Autowired
	private ItemTypesDao typeDao;

	@Autowired
	private FilesDao filesDao;
	
	@Autowired
	private TrackActivityDao trackActivityDao;

	@Override
	public FindAllResAssetsDto findAll() throws Exception {
		FindAllResAssetsDto result = new FindAllResAssetsDto();
		result.setData(assetsDao.findAll());
		result.setMsg(null);
		return result;
	}

	@Override
	public FindByIdResAssetsDto findById(String id) throws Exception {
		FindByIdResAssetsDto result = new FindByIdResAssetsDto();
		result.setData(assetsDao.findById(id));
		result.setMsg(null);
		return result;
	}

	@Override
	public InsertResDto insert(InsertReqDataAssetsDto data, MultipartFile display, MultipartFile invoicePict) throws Exception {
		try {
			Assets asset = new Assets();
			asset.setCode(data.getCode());

			Companies company = companiesDao.findById(data.getIdCompany());
			asset.setCompany(company);

			ItemTypes type = typeDao.findById(data.getItem().getIdItemType());

			Items item = new Items();
			item.setBrand(data.getItem().getBrand());
			item.setDescription(data.getItem().getDescription());
			item.setSerial(data.getItem().getSerial());
			item.setPrice(data.getItem().getPrice());
			item.setItemType(type);
			item.setCreatedBy("1");
			item.setIsActive(true);
			begin();
			item = itemsDao.saveOrUpdate(item);
			asset.setItem(item);
			Invoices invoice = new Invoices();
			if (data.getInvoice().getId() != null) {
				invoice = invoicesDao.findById(data.getInvoice().getId());
			} else {
				invoice.setCode(data.getInvoice().getCode());
				
				invoice.setPurchaseDate(data.getInvoice().getPurchaseDate());
				invoice.setTotalPrice(data.getInvoice().getTotalPrice());
				
				String extention = invoicePict.getOriginalFilename();
				extention = extention.substring(extention.lastIndexOf(".")+1, extention.length());
				
				Files newInvoicePict = new Files();
				newInvoicePict.setDataFile(invoicePict.getBytes());
				newInvoicePict.setExtention(extention);
				newInvoicePict.setCreatedBy("1");
				newInvoicePict.setIsActive(true);
				newInvoicePict = filesDao.saveOrUpdate(newInvoicePict);

				invoice.setInvoicePict(newInvoicePict);
				invoice.setIsActive(true);
				invoice.setCreatedBy("1");
				invoice = invoicesDao.saveOrUpdate(invoice);
			}
			asset.setInvoice(invoice);

			StatusAssets status = statusAssetsDao.findById(data.getIdStatusAsset());
			asset.setStatusAsset(status);

			asset.setCreatedBy("1");
			asset.setExpiredDate(data.getExpiredDate());

			
			String extention = display.getOriginalFilename();
			extention = extention.substring(extention.lastIndexOf(".")+1, extention.length());
			Files newDisplay = new Files();
			newDisplay.setDataFile(display.getBytes());
			newDisplay.setExtention(extention);
			newDisplay.setCreatedBy("1");
			newDisplay.setIsActive(true);

			newDisplay = filesDao.saveOrUpdate(newDisplay);

			asset.setDisplay(newDisplay);
			asset.setIsActive(true);
			asset = assetsDao.saveOrUpdate(asset);
			
			TrackActivity track = new TrackActivity();
			track.setCode("SAdwads");
			track.setNameAsset(data.getItem().getDescription());
			track.setStatusAsset(asset.getStatusAsset().getStatusAssetName());
			track.setActivity(ActivityTrack.INSERT_ASSET.getName());
			track.setDateActivity(LocalDate.now());
			track.setCreatedBy("1");
			track.setIsActive(true);
			
			trackActivityDao.saveOrUpdate(track);
			
			commit();

			InsertResDataDto dataResult = new InsertResDataDto();
			dataResult.setId(asset.getId());

			InsertResDto result = new InsertResDto();
			result.setData(dataResult);
			result.setMsg("");

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		
	}

	@Override
	public UpdateResDto update(Assets data) throws Exception {
		try {
			begin();
			Assets asset = assetsDao.saveOrUpdate(data);
			
			TrackActivity track = new TrackActivity();
			track.setCode(asset.getItem().getDescription());
			track.setStatusAsset(asset.getStatusAsset().getStatusAssetName());
			track.setActivity(ActivityTrack.UPDATE_ASSET.getName());
			track.setDateActivity(LocalDate.now());
			
			trackActivityDao.saveOrUpdate(track);
			commit();
			UpdateResDataDto dataResult = new UpdateResDataDto();
			dataResult.setVersion(asset.getVersion());

			UpdateResDto result = new UpdateResDto();
			result.setData(dataResult);
			result.setMsg("");

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		
	}

	@Override
	public DeleteResDataDto removeById(String id) throws Exception {
		try {
			DeleteResDataDto result = new DeleteResDataDto();
			begin();
			if (!assetsDao.removeById(id)) {
				result.setMsg("");
			}
			result.setMsg("");
			commit();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		
	}

	@Override
	public CountAssetResAssetsDto countAsset() throws Exception {
		CountAssetResAssetsDto result = new CountAssetResAssetsDto();
		result.setData(assetsDao.countAsset());
		result.setMsg(null);
		return result;
	}

	@Override
	public CountAssetByStatusResAssetsDto countAssetByStatus(String statusCode) throws Exception {
		CountAssetByStatusResAssetsDto result = new CountAssetByStatusResAssetsDto();
		result.setData(assetsDao.countAssetByStatus(statusCode));
		result.setMsg(null);
		return result;
	}

	@Override
	public FindAllFilterByTypeResAssetsDto findAllFilterByType(String typeCode) throws Exception {
		FindAllFilterByTypeResAssetsDto result = new FindAllFilterByTypeResAssetsDto();
		result.setData(assetsDao.findAllFilterByType(typeCode));
		result.setMsg(null);
		return result;
	}

	@Override
	public FindAllFilterBySearchResAssetsDto findAllFilterBySearch(String input) throws Exception {
		FindAllFilterBySearchResAssetsDto result = new FindAllFilterBySearchResAssetsDto();
		result.setData(assetsDao.findAllFilterBySearch(input));
		result.setMsg(null);
		return result;
	}

	@Override
	public FindAllForPdfAssetsExpiredDto findAllForPdf() throws Exception {
		FindAllForPdfAssetsExpiredDto result = new FindAllForPdfAssetsExpiredDto();
		result.setData(assetsDao.findAllForPdf());
		result.setMsg(null);
		
		return result;
	}

	
}
