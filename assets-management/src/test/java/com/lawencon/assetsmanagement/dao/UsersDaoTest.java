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

import com.lawencon.assetsmanagement.model.Roles;
import com.lawencon.assetsmanagement.model.Users;
import com.lawencon.base.ConnHandler;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class UsersDaoTest {
	@Autowired
	private RolesDao rolesDao;
	
	@Autowired
	private UsersDao usersDao;

	private String idInserted = "";

	@Test
	@Order(1)
	public void shouldSuccessInsert() throws Exception {
		ConnHandler.begin();
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

		Users result = usersDao.saveOrUpdate(user);
		idInserted = result.getId();
		ConnHandler.commit();
		assertNotNull(result.getId());

	}

	@Test
	@Order(2)
	public void shouldSuccessGetById() throws Exception {
		
		Users result = usersDao.findById(idInserted);
		
		assertNotNull(result);
	}
	@Test
	@Order(2)
	public void shouldSuccessGetByEmail() throws Exception {
		
		Users result = usersDao.findByEmail("kurnia@gmail.com");
		
		assertNotNull(result);
	}


	@Test
	@Order(3)
	public void shouldSuccesUpdate() throws Exception{
		String pass = "newpass";
		Users user = usersDao.findById(idInserted);
		user.setPass(pass);
		user.setUpdatedBy("1");
		ConnHandler.begin();
		Users result = usersDao.saveOrUpdate(user);
		ConnHandler.commit();
		assertEquals(result.getVersion(), 1);
		assertEquals(result.getPass(), pass);
		
	}
	
	@Test
	@Order(4)
	public void shouldSuccessFindAll() throws Exception{
		List<Users> result = usersDao.findAll();
		
		assertEquals(result.size(), 1);
	}

	@Test
	@Order(5)
	public void shouldSuccesDelete() throws Exception{
		ConnHandler.begin();
		Boolean result = usersDao.removeById(idInserted);
		ConnHandler.commit();
		assertEquals(result, true);
	}

	
	

}
