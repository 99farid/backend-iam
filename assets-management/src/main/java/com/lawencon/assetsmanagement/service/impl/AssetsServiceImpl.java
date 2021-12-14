package com.lawencon.assetsmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.assetsmanagement.dao.AssetsDao;
import com.lawencon.assetsmanagement.dao.CompaniesDao;
import com.lawencon.assetsmanagement.dao.FilesDao;
import com.lawencon.assetsmanagement.dao.InvoicesDao;
import com.lawencon.assetsmanagement.dao.ItemTypesDao;
import com.lawencon.assetsmanagement.dao.ItemsDao;
import com.lawencon.assetsmanagement.dao.StatusAssetsDao;
import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDataDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.assets.InsertReqDataAssetsDto;
import com.lawencon.assetsmanagement.model.Assets;
import com.lawencon.assetsmanagement.model.Companies;
import com.lawencon.assetsmanagement.model.Files;
import com.lawencon.assetsmanagement.model.Invoices;
import com.lawencon.assetsmanagement.model.ItemTypes;
import com.lawencon.assetsmanagement.model.Items;
import com.lawencon.assetsmanagement.model.StatusAssets;
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

	@Override
	public List<Assets> findAll() throws Exception {
		return assetsDao.findAll();
	}

	@Override
	public Assets findById(String id) throws Exception {
		return assetsDao.findById(id);
	}

	@Override
	public InsertResDto insert(InsertReqDataAssetsDto data) throws Exception {
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

			Files invoicePict = new Files();
			invoicePict.setDataFile(data.getInvoice().getInvoicePict().getDataFile());
			invoicePict.setExtention(data.getInvoice().getInvoicePict().getExtention());
			invoicePict.setCreatedBy("1");
			invoicePict.setIsActive(true);
			invoicePict = filesDao.saveOrUpdate(invoicePict);

			invoice.setInvoicePict(invoicePict);
			invoice.setIsActive(true);
			invoice = invoicesDao.saveOrUpdate(invoice);
		}
		asset.setInvoice(invoice);

		StatusAssets status = statusAssetsDao.findById(data.getIdStatusAsset());
		asset.setStatusAsset(status);

		asset.setCreatedBy("1L");
		asset.setExpiredDate(data.getExpiredDate());

		Files display = new Files();
		display.setDataFile(data.getDisplay().getDataFile());
		display.setExtention(data.getDisplay().getExtention());
		display.setCreatedBy("1");
		display.setIsActive(true);

		display = filesDao.saveOrUpdate(display);

		asset.setDisplay(display);

		asset = assetsDao.saveOrUpdate(asset);
		commit();

		InsertResDataDto dataResult = new InsertResDataDto();
		dataResult.setId(asset.getId());

		InsertResDto result = new InsertResDto();
		result.setData(dataResult);
		result.setMsg("");

		return result;
	}

	@Override
	public UpdateResDto update(Assets data) throws Exception {
		begin();
		Assets asset = assetsDao.saveOrUpdate(data);
		commit();
		UpdateResDataDto dataResult = new UpdateResDataDto();
		dataResult.setVersion(asset.getVersion());

		UpdateResDto result = new UpdateResDto();
		result.setData(dataResult);
		result.setMsg("");

		return result;
	}

	@Override
	public DeleteResDataDto removeById(String id) throws Exception {
		DeleteResDataDto result = new DeleteResDataDto();
		begin();
		if (!assetsDao.removeById(id)) {
			result.setMsg("");
		}
		result.setMsg("");
		commit();
		return result;
	}

}
