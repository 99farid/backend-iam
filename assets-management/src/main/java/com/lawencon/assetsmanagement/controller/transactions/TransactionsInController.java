package com.lawencon.assetsmanagement.controller.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.transactionsin.FindAllResTransactionsInDto;
import com.lawencon.assetsmanagement.dto.transactionsin.FindByIdResTransactionsInDto;
import com.lawencon.assetsmanagement.dto.transactionsin.InsertReqDataHeaderTransactionsInDto;
import com.lawencon.assetsmanagement.service.TransactionsInService;

public class TransactionsInController {
	@Autowired
	private TransactionsInService transactionInService;
	
	@GetMapping
	public ResponseEntity<?> findAll() throws Exception{
		FindAllResTransactionsInDto result = transactionInService.findAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable("id") String id) throws Exception{
		FindByIdResTransactionsInDto result = transactionInService.findById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> insert (@RequestBody InsertReqDataHeaderTransactionsInDto data) throws Exception{
		InsertResDto result = transactionInService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
}
