package com.lawencon.assetsmanagement.controller.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.transactionsout.FindAllResFilterByIdEmployeeDto;
import com.lawencon.assetsmanagement.dto.transactionsout.FindAllResFilterByIdGeneralItemDto;
import com.lawencon.assetsmanagement.dto.transactionsout.FindAllResFilterByIdLocationDto;
import com.lawencon.assetsmanagement.dto.transactionsout.FindAllResTransactionsOutDto;
import com.lawencon.assetsmanagement.dto.transactionsout.FindByIdResTransactionsOutDto;
import com.lawencon.assetsmanagement.dto.transactionsout.InsertReqDataTransactionsOutDto;
import com.lawencon.assetsmanagement.service.TransactionsOutService;

@RestController
@RequestMapping("transactions-out")
public class TransactionsOutController {

	@Autowired
	private TransactionsOutService transactionsOutSerice;
	
	@GetMapping
	public ResponseEntity<?> findAll() throws Exception {
		FindAllResTransactionsOutDto result = transactionsOutSerice.findAll();
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable("id") String id) throws Exception {
		FindByIdResTransactionsOutDto result = transactionsOutSerice.findById(id);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("employee")
	public ResponseEntity<?> findAllFilterByIdEmployee() throws Exception {
		FindAllResFilterByIdEmployeeDto result = transactionsOutSerice.findAllFilterByIdEmployee();
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("location")
	public ResponseEntity<?> findAllFilterByIdLocation() throws Exception {
		FindAllResFilterByIdLocationDto result = transactionsOutSerice.findAllFilterByIdLocation();
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("general-item")
	public ResponseEntity<?> findAllFilterByIdGeneralItem() throws Exception {
		FindAllResFilterByIdGeneralItemDto result = transactionsOutSerice.findAllFilterByIdGeneralItem();
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody InsertReqDataTransactionsOutDto data) throws Exception {
		InsertResDto transOut = transactionsOutSerice.insert(data);
		
		return new ResponseEntity<>(transOut, HttpStatus.CREATED);
	}
}