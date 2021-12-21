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
import com.lawencon.assetsmanagement.model.Locations;
import com.lawencon.base.ConnHandler;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class LocationsDaoTest {
	@Autowired
	private LocationsDao locationsDao;
	
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
		company = companiesDao.saveOrUpdate(company);

		Locations location = new Locations();
		location.setCode("R-001");
		location.setCompany(company);
		location.setCreatedBy("1");
		location.setIsActive(true);
		location.setLocationName("Ruang Meeting");
		
		Locations result = locationsDao.saveOrUpdate(location);
		idInserted = result.getId();
		ConnHandler.commit();
		assertNotNull(result.getId());

	}

	@Test
	@Order(2)
	public void shouldSuccessGetById() throws Exception {
		
		Locations result = locationsDao.findById(idInserted);
		
		assertNotNull(result);
	}

	@Test
	@Order(3)
	public void shouldSuccesUpdate() throws Exception{
		String name = "Ruang Rapat";
		Locations location = locationsDao.findById(idInserted);
		location.setLocationName(name);
		location.setUpdatedBy("1");
		ConnHandler.begin();
		Locations result = locationsDao.saveOrUpdate(location);
		ConnHandler.commit();
		assertEquals(result.getVersion(), 1);
		assertEquals(result.getLocationName(), name);
		
	}
	@Test
	@Order(4)
	public void shouldSuccessFindAll() throws Exception{
		List<Locations> result = locationsDao.findAll();
		
		assertEquals(result.size(), 1);
	}
	@Test
	@Order(5)
	public void shouldSuccesDelete() throws Exception{
		ConnHandler.begin();
		Boolean result = locationsDao.removeById(idInserted);
		ConnHandler.commit();
		assertEquals(result, true);
	}


}
