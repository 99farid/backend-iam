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
import com.lawencon.base.ConnHandler;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class RolesDaoTest {
	@Autowired
	RolesDao rolesDao;

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
		
		Roles result = rolesDao.saveOrUpdate(role);
		idInserted = result.getId();
		ConnHandler.commit();
		assertNotNull(result.getId());

	}

	@Test
	@Order(2)
	public void shouldSuccessGetById() throws Exception {
		
		Roles result = rolesDao.findById(idInserted);
		
		assertNotNull(result);
	}
	@Test
	@Order(2)
	public void shouldSuccessGetByCOde() throws Exception {
		
		Roles result = rolesDao.findByCode("adm");
		
		assertNotNull(result);
	}

	@Test
	@Order(3)
	public void shouldSuccesUpdate() throws Exception{
		String name = "Admin";
		Roles role= rolesDao.findById(idInserted);
		role.setRoleName(name);
		role.setUpdatedBy("1");
		ConnHandler.begin();
		Roles result = rolesDao.saveOrUpdate(role);
		ConnHandler.commit();
		assertEquals(result.getVersion(), 1);
		assertEquals(result.getRoleName(), name);
		
	}
	@Test
	@Order(4)
	public void shouldSuccessFindAll() throws Exception{
		List<Roles> result = rolesDao.findAll();
		
		assertEquals(result.size(), 1);
	}

	@Test
	@Order(5)
	public void shouldSuccesDelete() throws Exception{
		ConnHandler.begin();
		Boolean result = rolesDao.removeById(idInserted);
		ConnHandler.commit();
		assertEquals(result, true);
	}


}
