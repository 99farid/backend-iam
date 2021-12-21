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
import com.lawencon.assetsmanagement.model.Employees;
import com.lawencon.base.ConnHandler;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class EmployeesDaoTest {
	@Autowired
	private EmployeesDao employeesDao;

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
		
		Employees employee = new Employees();
		employee.setCompany(company);
		employee.setCreatedBy("1");
		employee.setDepartment("Human Resource");
		employee.setEmail("kurnia@gmail.com");
		employee.setFullName("Kurnia K");
		employee.setIsActive(true);
		employee.setNip("1112020");
		employee.setPhoneNo("081310405099");
		

		Employees result = employeesDao.saveOrUpdate(employee);
		idInserted = result.getId();
		ConnHandler.commit();
		assertNotNull(result.getId());

	}

	@Test
	@Order(2)
	public void shouldSuccessGetById() throws Exception {
		
		Employees result = employeesDao.findById(idInserted);
		
		assertEquals(result.getId(), idInserted);
	}

	@Test
	@Order(3)
	public void shouldSuccesUpdate() throws Exception{
		String name = "Hasan Kurnia";
		String depart = "Developer";
		Employees employees = employeesDao.findById(idInserted);
		employees.setFullName(name);
		employees.setDepartment(depart);
		employees.setUpdatedBy("1");
		ConnHandler.begin();
		Employees result = employeesDao.saveOrUpdate(employees);
		ConnHandler.commit();
		assertEquals(result.getVersion(), 1);
		assertEquals(result.getFullName(), name);
		assertEquals(result.getDepartment(), depart);
		
	}
	@Test
	@Order(4)
	public void shouldSuccessFindByNip() throws Exception{
		Employees result = employeesDao.findByNip("1112020");
	
		assertEquals(result.getId(), idInserted);
	}
	
	@Test
	@Order(5)
	public void shouldSuccessFindAll() throws Exception{
		List<Employees> result = employeesDao.findAll();
		
		assertEquals(result.size(), 1);
	}

	@Test
	@Order(6)
	public void shouldSuccesDelete() throws Exception{
		ConnHandler.begin();
		Boolean result = employeesDao.removeById(idInserted);
		ConnHandler.commit();
		assertEquals(result, true);
	}
	

}
