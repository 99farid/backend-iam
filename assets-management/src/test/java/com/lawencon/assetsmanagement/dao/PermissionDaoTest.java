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

import com.lawencon.assetsmanagement.model.Permissions;
import com.lawencon.base.ConnHandler;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class PermissionDaoTest {
	@Autowired
	private PermissionsDao permissionDao;

	private String idInserted = "";

	@Test
	@Order(1)
	public void shouldSuccessInsert() throws Exception {
		ConnHandler.begin();
		Permissions permission = new Permissions();
		permission.setCode("CTR");
		permission.setCreatedBy("1");
		permission.setIsActive(true);
		permission.setPermissionName("Create Table Role");
		
		Permissions result = permissionDao.saveOrUpdate(permission);
		idInserted = result.getId();
		ConnHandler.commit();
		assertNotNull(result.getId());

	}

	@Test
	@Order(2)
	public void shouldSuccessGetById() throws Exception {
		
		Permissions result = permissionDao.findById(idInserted);
		
		assertEquals(result.getId(), idInserted);
	}

	@Test
	@Order(3)
	public void shouldSuccesUpdate() throws Exception{
		String name = "Insert Table Role";
		Permissions permission = permissionDao.findById(idInserted);
		permission.setPermissionName(name);
		permission.setUpdatedBy("1");
		ConnHandler.begin();
		Permissions result = permissionDao.saveOrUpdate(permission);
		ConnHandler.commit();
		assertEquals(result.getVersion(), 1);
		assertEquals(result.getPermissionName(), name);
		
	}
	@Test
	@Order(4)
	public void shouldSuccessFindAll() throws Exception{
		List<Permissions> result = permissionDao.findAll();
		
		assertEquals(result.size(), 1);
	}
	@Test
	@Order(5)
	public void shouldSuccesDelete() throws Exception{
		ConnHandler.begin();
		Boolean result = permissionDao.removeById(idInserted);
		ConnHandler.commit();
		assertEquals(result, true);
	}
	
}
