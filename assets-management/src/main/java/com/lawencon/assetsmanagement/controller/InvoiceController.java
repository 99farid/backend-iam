package com.lawencon.assetsmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.invoices.FindAllFilterByCodeResInvoicesDto;
import com.lawencon.assetsmanagement.dto.invoices.FindAllResInvoicesDto;
import com.lawencon.assetsmanagement.dto.invoices.FindByIdResInvoicesDto;
import com.lawencon.assetsmanagement.model.Invoices;
import com.lawencon.assetsmanagement.service.InvoicesService;

@RestController
@RequestMapping("invoices")
public class InvoiceController extends BaseIamController{
	@Autowired
	private InvoicesService invoicesService;
	
	@GetMapping
	public ResponseEntity<?> findAll() throws Exception{
		FindAllResInvoicesDto result = invoicesService.findAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable("id") String id) throws Exception{
		FindByIdResInvoicesDto result = invoicesService.findById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("search")
	public ResponseEntity<?> findAllFilterByCode(@RequestParam("query") String code) throws Exception{
		FindAllFilterByCodeResInvoicesDto result = invoicesService.findAllFilterByCode(code);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> insert (@RequestPart String data, @RequestPart MultipartFile invoicePict) throws Exception{
		InsertResDto result = invoicesService.insert(convertToModel(data, Invoices.class), invoicePict);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<?> update (@RequestPart String data, @RequestPart(required = false) MultipartFile invoicePict ) throws Exception{
		UpdateResDto result = invoicesService.update(convertToModel(data, Invoices.class), invoicePict);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping ("{id}")
	public ResponseEntity<?> removeById(@PathVariable("id") String id) throws Exception{
		DeleteResDataDto result =   invoicesService.removeById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
