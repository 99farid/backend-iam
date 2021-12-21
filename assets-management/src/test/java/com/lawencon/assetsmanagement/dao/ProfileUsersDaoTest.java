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
import com.lawencon.assetsmanagement.model.ProfileUsers;
import com.lawencon.assetsmanagement.model.Roles;
import com.lawencon.assetsmanagement.model.Users;
import com.lawencon.base.ConnHandler;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class ProfileUsersDaoTest {
	@Autowired
	UsersDao usersDao;
	
	@Autowired
	CompaniesDao companiesDao;
	
	@Autowired
	EmployeesDao employeesDao;
	
	@Autowired
	ProfileUsersDao profileUsersDao;
	
	@Autowired
	RolesDao rolesDao;
	
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
		employee = employeesDao.saveOrUpdate(employee);
		
		Roles role = new Roles();
		role.setCode("adm");
		role.setCreatedBy("1");
		role.setIsActive(true);
		role.setRoleName("ADMIN");
		role = rolesDao.saveOrUpdate(role);
		
		Users user = new Users();
		user.setCreatedBy("1");
		user.setEmail("kurnia@gmail.com");
		user.setIsActive(true);
		user.setPass("100persen");
		user.setRole(role);
		user = usersDao.saveOrUpdate(user);
		
		ProfileUsers profile = new ProfileUsers();
		profile.setCreatedBy("1");
		profile.setEmployee(employee);
		profile.setIsActive(true);
		profile.setUser(user);
		
		ProfileUsers result = profileUsersDao.saveOrUpdate(profile);
		idInserted = result.getId();
		ConnHandler.commit();
		assertNotNull(result.getId());

	}

	@Test
	@Order(2)
	public void shouldSuccessGetById() throws Exception {
		
		ProfileUsers result = profileUsersDao.findById(idInserted);
		
		assertEquals(result.getId(), idInserted);
	}

//	@Test
//	@Order(3)
	public void shouldSuccesUpdate() throws Exception{
		
	}
	@Test
	@Order(3)
	public void shouldSuccessFindAll() throws Exception{
		List<ProfileUsers> result = profileUsersDao.findAll();
		
		assertEquals(result.size(), 1);
	}

	@Test
	@Order(4)
	public void shouldSuccesDelete() throws Exception{
		ConnHandler.begin();
		Boolean result = profileUsersDao.removeById(idInserted);
		ConnHandler.commit();
		assertEquals(result, true);
	}
	
	
}
