package com.lawencon.assetsmanagement.controller.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.assetsmanagement.dto.detailtransactionsout.FindAllResDetailTransactionsOutDto;
import com.lawencon.assetsmanagement.dto.detailtransactionsout.FindByIdResDetailTransactionsOutDto;
import com.lawencon.assetsmanagement.service.DetailTransactionsOutService;

@RestController
@RequestMapping("detail-transactions-out")
public class DetailTransactionsOutController {

	@Autowired
	private DetailTransactionsOutService detailTransactionsOutService;
	
	@GetMapping
	public ResponseEntity<?> findAll() throws Exception {
		FindAllResDetailTransactionsOutDto result = detailTransactionsOutService.findAll();
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable("id") String id) throws Exception {
		FindByIdResDetailTransactionsOutDto result = detailTransactionsOutService.findById(id);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("header/{idHeader}")
	public ResponseEntity<?> findByIdHeader(@PathVariable("idHeader") String idHeader) throws Exception {
		FindByIdResDetailTransactionsOutDto result = detailTransactionsOutService.findById(idHeader);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}