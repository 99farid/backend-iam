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

import com.lawencon.assetsmanagement.model.ConditionAssets;
import com.lawencon.assetsmanagement.model.StatusAssets;
import com.lawencon.base.ConnHandler;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class ConditionAssetsDaoTest {
	@Autowired
	private ConditionAssetsDao conditionDao;

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
		status = statusDao.saveOrUpdate(status);

		ConditionAssets condition = new ConditionAssets();
		condition.setCode("RDPL");
		condition.setConditionAssetName("Ready to Deploy");
		condition.setCreatedBy("1");
		condition.setIsActive(true);
		condition.setStatusAsset(status);
		

		ConditionAssets result = conditionDao.saveOrUpdate(condition);
		idInserted = result.getId();
		ConnHandler.commit();
		assertNotNull(result.getId());

	}

	@Test
	@Order(2)
	public void shouldSuccessGetById() throws Exception {
		
		ConditionAssets result = conditionDao.findById(idInserted);
		
		assertEquals(result.getId(), idInserted);
	}

	@Test
	@Order(3)
	public void shouldSuccesUpdate() throws Exception{
		String newName = "Ready";
		ConditionAssets condition = conditionDao.findById(idInserted);
		condition.setConditionAssetName(newName);
		condition.setUpdatedBy("1");
		ConnHandler.begin();
		ConditionAssets result = conditionDao.saveOrUpdate(condition);
		ConnHandler.commit();
		assertEquals(result.getVersion(), 1);
		assertEquals(result.getConditionAssetName(), newName);
		
	}
	@Test
	@Order(4)
	public void shouldSuccessFindAll() throws Exception{
		List<ConditionAssets> result = conditionDao.findAll();
		
		assertEquals(result.size(), 1);
	}
	
	@Test
	@Order(5)
	public void shouldSuccesDelete() throws Exception{
		ConnHandler.begin();
		Boolean result = conditionDao.removeById(idInserted);
		ConnHandler.commit();
		assertEquals(result, true);
	}

	


}
