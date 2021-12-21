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

import com.lawencon.assetsmanagement.model.Companies;
import com.lawencon.base.ConnHandler;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class CompaniesDaoTest {

	@Autowired
	private CompaniesDao companiesDao;

	private String idInserted = "";

	@Test
	@Order(1)
	public void shouldSuccessInsert() throws Exception {
		ConnHandler.begin();
		

		Companies company = new Companies();
		company.setCode("LWN");
		company.setCompanyName("LAWENCON");
		company.setCreatedBy("1");
		company.setIsActive(true);

		Companies result = companiesDao.saveOrUpdate(company);
		idInserted = result.getId();
		ConnHandler.commit();
		assertNotNull(result.getId());

	}

	@Test
	@Order(2)
	public void shouldSuccessGetById() throws Exception {
		
		Companies result = companiesDao.findById(idInserted);
		
		assertEquals(result.getId(), idInserted);
	}

	@Test
	@Order(3)
	public void shouldSuccesUpdate() throws Exception{
		String newName = "Linov";
		Companies company = companiesDao.findById(idInserted);
		company.setCompanyName(newName);
		company.setUpdatedBy("1");
		ConnHandler.begin();
		Companies result = companiesDao.saveOrUpdate(company);
		ConnHandler.commit();
		assertEquals(result.getVersion(), 1);
		assertEquals(result.getCompanyName(), newName);
		
	}
	@Test
	@Order(4)
	public void shouldSuccessFindAll() throws Exception{
		List<Companies> result = companiesDao.findAll();
		
		assertEquals(result.size(), 1);
	}
	@Test
	@Order(5)
	public void shouldSuccesfindAllFilterByCode() throws Exception{
		List<Companies> result = companiesDao.findAllFilterByCode("LWN");
		
		assertEquals(result.size(), 1);
	}
	
	@Test
	@Order(6)
	public void shouldSuccesDelete() throws Exception{
		ConnHandler.begin();
		Boolean result = companiesDao.removeById(idInserted);
		ConnHandler.commit();
		assertEquals(result, true);
	}

	

}
