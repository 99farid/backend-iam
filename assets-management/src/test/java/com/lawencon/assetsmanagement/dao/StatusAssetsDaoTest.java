package com.lawencon.assetsmanagement.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.lawencon.assetsmanagement.model.StatusAssets;
import com.lawencon.base.ConnHandler;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class StatusAssetsDaoTest {
	@Autowired
	private StatusAssetsDao statusDao;


	private String idInserted = "";

	@Test
	@Order(1)
	public void shouldSuccessInsert() throws Exception {
		ConnHandler.begin();

		StatusAssets status = new StatusAssets();
		status.setCode("DPL");
		status.setCreatedBy("1");
		status.setIsActive(true);
		status.setStatusAssetName("DEPLOYABLE");


		StatusAssets result = statusDao.saveOrUpdate(status);
		idInserted = result.getId();
		ConnHandler.commit();
		assertNotNull(result.getId());

	}

	@Test
	@Order(2)
	public void shouldSuccessGetById() throws Exception {
		
		StatusAssets result = statusDao.findById(idInserted);
		
		assertEquals(result.getId(), idInserted);
	}
	
	@Test
	@Order(3)
	public void shouldSuccessGetByCode() throws Exception {
		
		StatusAssets result = statusDao.findByCode("DPL");
		
		assertEquals(result.getId(), idInserted);
	}

	@Test
	@Order(3)
	public void shouldSuccesUpdate() throws Exception{
		String name = "Delpoyable";
		StatusAssets status = statusDao.findById(idInserted);
		status.setStatusAssetName(name);
		status.setUpdatedBy("1");
		ConnHandler.begin();
		StatusAssets result = statusDao.saveOrUpdate(status);
		ConnHandler.commit();
		assertEquals(result.getVersion(), 1);
		assertEquals(result.getStatusAssetName(), name);
		
	}
	@Test
	@Order(4)
	public void shouldSuccessFindAll() throws Exception{
		List<StatusAssets> result = statusDao.findAll();
		
		assertEquals(result.size(), 1);
	}
	@Test
	@Order(4)
	public void shouldSuccessFindAllFilterBySearch() throws Exception{
		List<StatusAssets> result = statusDao.findAllFilterBySearch("DPL");
		
		assertEquals(result.size(), 1);
	}
	@Test
	@Order(5)
	public void shouldSuccesDelete() throws Exception{
		ConnHandler.begin();
		Boolean result = statusDao.removeById(idInserted);
		ConnHandler.commit();
		assertEquals(result, true);
	}



}
