package com.lawencon.assetsmanagement.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lawencon.assetsmanagement.model.Assets;
import com.lawencon.assetsmanagement.model.Companies;
import com.lawencon.assetsmanagement.model.Invoices;
import com.lawencon.assetsmanagement.model.ItemTypes;
import com.lawencon.assetsmanagement.model.Items;
import com.lawencon.assetsmanagement.model.StatusAssets;
import com.lawencon.base.ConnHandler;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class AssetsDaoTest {
	@Autowired
	private AssetsDao assetsDao;

	@Autowired
	private ItemsDao itemsDao;

	@Autowired
	private ItemTypesDao typesDao;

	@Autowired
	private InvoicesDao invoicesDao;

	@Autowired
	private StatusAssetsDao statusDao;

	@Autowired
	private CompaniesDao companiesDao;

	private String idInserted = "";

	@Test
	@Order(1)
	public void shouldSuccessInsert() throws Exception {
		ConnHandler.begin();
		ItemTypes types = new ItemTypes();
		types.setCode("GNLpxS");
		types.setItemTypeName("GENERAL");
		types.setCreatedBy("1");
		types.setIsActive(true);
		types = typesDao.saveOrUpdate(types);

		Items item = new Items();
		item.setBrand("Asus");
		item.setCreatedBy("1");
		item.setDescription("Laptop Notebook");
		item.setIsActive(true);
		item.setItemType(types);
		item.setPrice(new BigDecimal(100000));
		item.setSerial("322A2");
		item = itemsDao.saveOrUpdate(item);

		Invoices invoice = new Invoices();
		invoice.setCode("C0022xAAxS");
		invoice.setCreatedBy("1");
		invoice.setIsActive(true);
		invoice.setPurchaseDate(LocalDate.now());
		invoice.setTotalPrice(new BigDecimal(2000000000));
		invoice = invoicesDao.saveOrUpdate(invoice);

		StatusAssets status = new StatusAssets();
		status.setCode("DPLxAAAxS");
		status.setCreatedBy("1");
		status.setIsActive(true);
		status.setStatusAssetName("DEPLOYABLE");
		status = statusDao.saveOrUpdate(status);

		Companies company = new Companies();
		company.setCode("LWNxAaAxS");
		company.setCompanyName("LAWENCON");
		company.setCreatedBy("1");
		company.setIsActive(true);
		company = companiesDao.saveOrUpdate(company);

		Assets asset = new Assets();
		asset.setCode("LWN-GNL-2xAAS");
		asset.setCompany(company);
		asset.setCreatedBy("1");
		asset.setInvoice(invoice);
		asset.setIsActive(true);
		asset.setItem(item);
		asset.setStatusAsset(status);

		Assets result = assetsDao.saveOrUpdate(asset);
		idInserted = result.getId();
		ConnHandler.commit();
		assertNotNull(result.getId());

	}

	@Test
	@Order(2)
	public void shouldSuccessGetById() throws Exception {
		
		Assets result = assetsDao.findById(idInserted);
		
		assertEquals(result.getId(), idInserted);
	}

	@Test
	@Order(3)
	public void shouldSuccesUpdate() throws Exception{
		LocalDate date = LocalDate.now();
		Assets asset = assetsDao.findById(idInserted);
		asset.setExpiredDate(date);
		asset.setUpdatedBy("1");
		ConnHandler.begin();
		Assets result = assetsDao.saveOrUpdate(asset);
		ConnHandler.commit();
		assertEquals(result.getVersion(), 1);
		assertEquals(result.getExpiredDate(), date);
		
	}

	@Test
	@Order(4)
	public void shouldSuccesDelete() throws Exception{
		ConnHandler.begin();
		Boolean result = assetsDao.removeById(idInserted);
		ConnHandler.commit();
		assertEquals(result, true);
	}

	@Test
	@Order(5)
	public void shouldSuccesCountAsset() throws Exception{
		Integer result = assetsDao.countAsset();
		
		assertEquals(result, 9);
	}

	@Test
	@Order(6)
	public void shouldSuccessCountAssetByStatus() throws Exception {
		Integer result = assetsDao.countAssetByStatus("SA1");
		
		assertEquals(result, 7);
	}

	@Test
	@Order(7)
	public void shouldSuccessFindAllFilterByType() throws Exception{
		List<Assets> result = assetsDao.findAllFilterByType("GNLx");
		
		assertEquals(result.size(), 1);
	}
	
	@Test
	@Order(8)
	public void shouldSuccessFindAllFilterBySearch() throws Exception{
		List<Assets> result = assetsDao.findAllFilterBySearch("Asus1");
	
		assertEquals(result.size(), 1);;
	}
	
	@Test
	@Order(9)
	public void shouldSuccessFindAll() throws Exception{
		List<Assets> result = assetsDao.findAll();
		
		assertEquals(result.size(), 9);
	}

}
