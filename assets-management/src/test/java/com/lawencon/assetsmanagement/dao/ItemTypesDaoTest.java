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

import com.lawencon.assetsmanagement.model.ItemTypes;
import com.lawencon.base.ConnHandler;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class ItemTypesDaoTest {

	@Autowired
	private ItemTypesDao typesDao;


	private String idInserted = "";

	@Test
	@Order(1)
	public void shouldSuccessInsert() throws Exception {
		ConnHandler.begin();
		ItemTypes types = new ItemTypes();
		types.setCode("GNL");
		types.setItemTypeName("GENERAL");
		types.setCreatedBy("1");
		types.setIsActive(true);


		ItemTypes result = typesDao.saveOrUpdate(types);
		idInserted = result.getId();
		ConnHandler.commit();
		assertNotNull(result.getId());

	}

	@Test
	@Order(2)
	public void shouldSuccessGetById() throws Exception {
		
		ItemTypes result = typesDao.findById(idInserted);
		
		assertNotNull(result);
	}

	@Test
	@Order(3)
	public void shouldSuccesUpdate() throws Exception{
		String name = "General";
		ItemTypes types = typesDao.findById(idInserted);
		types.setItemTypeName(name);
		types.setUpdatedBy("1");
		ConnHandler.begin();
		ItemTypes result = typesDao.saveOrUpdate(types);
		ConnHandler.commit();
		assertEquals(result.getVersion(), 1);
		assertEquals(result.getItemTypeName(), name);
		
	}

	@Test
	@Order(4)
	public void shouldSuccessFindAll() throws Exception{
		List<ItemTypes> result = typesDao.findAll();
		
		assertEquals(result.size(), 1);
	}
	@Test
	@Order(5)
	public void shouldSuccessFindAllFilterByCode() throws Exception{
		List<ItemTypes> result = typesDao.findAllFilterByCode("GNL");
		
		assertEquals(result.size(), 1);
	}

	@Test
	@Order(6)
	public void shouldSuccesDelete() throws Exception{
		ConnHandler.begin();
		Boolean result = typesDao.removeById(idInserted);
		ConnHandler.commit();
		assertEquals(result, true);
	}

	
	
}
