package com.lawencon.assetsmanagement.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
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
import com.lawencon.assetsmanagement.model.Items;
import com.lawencon.base.ConnHandler;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class ItemsDaoTest {

	@Autowired
	private ItemsDao itemsDao;

	@Autowired
	private ItemTypesDao typesDao;


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

		Items result = itemsDao.saveOrUpdate(item);
		idInserted = result.getId();
		ConnHandler.commit();
		assertNotNull(result.getId());

	}

	@Test
	@Order(2)
	public void shouldSuccessGetById() throws Exception {
		
		Items result = itemsDao.findById(idInserted);
		
		assertNotNull(result);
	}

	@Test
	@Order(3)
	public void shouldSuccesUpdate() throws Exception{
		String description = "Laptop";
		String brand = "Samsung";
		Items item = itemsDao.findById(idInserted);
		item.setDescription(description);
		item.setBrand(brand);
		item.setUpdatedBy("1");
		ConnHandler.begin();
		Items result = itemsDao.saveOrUpdate(item);
		ConnHandler.commit();
		assertEquals(result.getVersion(), 1);
		assertEquals(result.getDescription(), description);
		assertEquals(result.getBrand(), brand);
		
	}
	@Test
	@Order(4)
	public void shouldSuccessFindAll() throws Exception{
		List<Items> result = itemsDao.findAll();
		
		assertEquals(result.size(), 1);
	}
	@Test
	@Order(5)
	public void shouldSuccesGetTotalPrice() throws Exception{
		ConnHandler.begin();
		BigDecimal result = new BigDecimal(itemsDao.getTotalPrice().toString()) ;
		ConnHandler.commit();
		assertEquals(result, new BigDecimal(100000));
	}

	@Test
	@Order(6)
	public void shouldSuccesDelete() throws Exception{
		ConnHandler.begin();
		Boolean result = itemsDao.removeById(idInserted);
		ConnHandler.commit();
		assertEquals(result, true);
	}


}
