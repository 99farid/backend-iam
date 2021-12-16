package com.lawencon.assetsmanagement.controller.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lawencon.assetsmanagement.dto.detailtransactionsin.FindAllResDetailTransactionInDto;
import com.lawencon.assetsmanagement.dto.detailtransactionsin.FindByIdHeaderResDetailTransactionInDto;
import com.lawencon.assetsmanagement.dto.detailtransactionsin.FindByIdResDetailTransactionInDto;
import com.lawencon.assetsmanagement.service.DetailTransactionsInService;

public class DetailTransactionsInController {
	@Autowired
	private DetailTransactionsInService detailInService;
	
	@GetMapping
	public ResponseEntity<?> findAll() throws Exception{
		FindAllResDetailTransactionInDto result = detailInService.findAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable("id") String id) throws Exception{
		FindByIdResDetailTransactionInDto result = detailInService.findById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("header/{id}")
	public ResponseEntity<?> findByIdHeader(@PathVariable("id") String id) throws Exception{
		FindByIdHeaderResDetailTransactionInDto result = detailInService.findByIdHeader(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
