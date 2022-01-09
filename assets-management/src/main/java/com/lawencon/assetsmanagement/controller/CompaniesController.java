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
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.companies.FindAllFilterBySearchResCompaniesDto;
import com.lawencon.assetsmanagement.dto.companies.FindAllResCompaniesDto;
import com.lawencon.assetsmanagement.dto.companies.FindByIdResCompaniesDto;
import com.lawencon.assetsmanagement.model.Companies;
import com.lawencon.assetsmanagement.service.CompaniesService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("companies")
public class CompaniesController extends BaseIamController{
	
	@Autowired
	private CompaniesService companiesService;
	
	@GetMapping
	@ApiResponse(responseCode = "200", description = "Successfuly Get Data", content = @Content(schema = @Schema (implementation = FindAllResCompaniesDto.class)))
	public ResponseEntity<?> findAll() throws Exception {
		FindAllResCompaniesDto result = companiesService.findAll();
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	@ApiResponse(responseCode = "200", description = "Successfuly Get Data", content = @Content(schema = @Schema (implementation = FindByIdResCompaniesDto.class)))
	public ResponseEntity<?> findById(@PathVariable("id") String id) throws Exception {
		FindByIdResCompaniesDto result = companiesService.findById(id);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	@GetMapping("search")
	@ApiResponse(responseCode = "200", description = "Successfuly Get Data", content = @Content(schema = @Schema (implementation = FindAllResCompaniesDto.class)))
	public ResponseEntity<?> findAllFilterBySearch(@RequestParam("query") String input) throws Exception {
		FindAllFilterBySearchResCompaniesDto result = companiesService.findAllFilterBySearch(input);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping
	@ApiResponse(responseCode = "201", description = "Successfuly Insert Data", content = @Content(schema = @Schema (implementation = InsertResDto.class)))
	public ResponseEntity<?> insert(@RequestBody Companies data) throws Exception {
		InsertResDto companies = companiesService.insert(data);
		
		return new ResponseEntity<>(companies, HttpStatus.CREATED);
	}
	
	@PutMapping
	@ApiResponse(responseCode = "200", description = "Successfuly Update Data", content = @Content(schema = @Schema (implementation = UpdateResDto.class)))
	public ResponseEntity<?> update(@RequestBody Companies data) throws Exception {
		UpdateResDto companies = companiesService.update(data);
		
		return new ResponseEntity<>(companies, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	@ApiResponse(responseCode = "200", description = "Successfuly Delete Data", content = @Content(schema = @Schema (implementation = DeleteResDataDto.class)))
	public ResponseEntity<?> removeById(@PathVariable("id") String id) throws Exception {
		DeleteResDataDto result = companiesService.removeById(id);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
