package com.lawencon.assetsmanagement.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.lawencon.assetsmanagement.model.Invoices;
import com.lawencon.base.ConnHandler;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class InvoicesDaoTest {

	@Autowired
	private InvoicesDao invoicesDao;

	private String idInserted = "";

	@Test
	@Order(1)
	public void shouldSuccessInsert() throws Exception {
		ConnHandler.begin();


		Invoices invoice = new Invoices();
		invoice.setCode("C0022");
		invoice.setCreatedBy("1");
		invoice.setIsActive(true);
		invoice.setPurchaseDate(LocalDate.now());
		invoice.setTotalPrice(new BigDecimal(2000000000));

		Invoices result = invoicesDao.saveOrUpdate(invoice);
		idInserted = result.getId();
		ConnHandler.commit();
		assertNotNull(result.getId());

	}

	@Test
	@Order(2)
	public void shouldSuccessGetById() throws Exception {
		
		Invoices result = invoicesDao.findById(idInserted);
		
		assertNotNull(result);
	}

	@Test
	@Order(3)
	public void shouldSuccesUpdate() throws Exception{
		BigDecimal totalPrice = new BigDecimal(1123000);
		Invoices invoice = invoicesDao.findById(idInserted);
		invoice.setTotalPrice(totalPrice);
		invoice.setUpdatedBy("1");
		ConnHandler.begin();
		Invoices result = invoicesDao.saveOrUpdate(invoice);
		ConnHandler.commit();
		assertEquals(result.getVersion(), 1);
		assertEquals(result.getTotalPrice(), totalPrice);
		
	}
	@Test
	@Order(4)
	public void shouldSuccessFindAll() throws Exception{
		List<Invoices> result = invoicesDao.findAll();
		
		assertEquals(result.size(), 1);
	}
	@Test
	@Order(5)
	public void shouldSuccessFindAllFilterByCode() throws Exception{
		List<Invoices> result = invoicesDao.findAllFilterByCode("C0022");
		
		assertEquals(result.size(), 1);
	}

	@Test
	@Order(6)
	public void shouldSuccesDelete() throws Exception{
		ConnHandler.begin();
		Boolean result = invoicesDao.removeById(idInserted);
		ConnHandler.commit();
		assertEquals(result, true);
	}
	

}
