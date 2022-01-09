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
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.roles.FindAllResRolesDto;
import com.lawencon.assetsmanagement.dto.roles.FindByIdResRolesDto;
import com.lawencon.assetsmanagement.dto.roles.InsertReqDataRolesDto;
import com.lawencon.assetsmanagement.dto.roles.UpdateReqRolesDto;
import com.lawencon.assetsmanagement.service.RolesService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("roles")
public class RolesController extends BaseIamController{

	@Autowired
	private RolesService rolesService;
	
	@GetMapping
	@ApiResponse(responseCode = "200", description = "Successfuly Get Data", content = @Content(schema = @Schema (implementation = FindAllResRolesDto.class)))
	public ResponseEntity<?> findAll() throws Exception {
		FindAllResRolesDto result = rolesService.findAll();
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	@ApiResponse(responseCode = "200", description = "Successfuly Get Data", content = @Content(schema = @Schema (implementation = FindByIdResRolesDto.class)))
	public ResponseEntity<?> findById(@PathVariable("id") String id) throws Exception {
		FindByIdResRolesDto result = rolesService.findById(id);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping
	@ApiResponse(responseCode = "201", description = "Successfuly Insert Data", content = @Content(schema = @Schema (implementation = InsertResDto.class)))
	public ResponseEntity<?> insert(@RequestBody InsertReqDataRolesDto data) throws Exception {
		InsertResDto roles = rolesService.insert(data);
		
		return new ResponseEntity<>(roles, HttpStatus.CREATED);
	}
	
	@PutMapping
	@ApiResponse(responseCode = "200", description = "Successfuly Update Data", content = @Content(schema = @Schema (implementation = UpdateResDto.class)))
	public ResponseEntity<?> update(@RequestBody UpdateReqRolesDto data) throws Exception {
		UpdateResDto roles = rolesService.update(data);
		
		return new ResponseEntity<>(roles, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	@ApiResponse(responseCode = "200", description = "Successfuly Delete Data", content = @Content(schema = @Schema (implementation = DeleteResDataDto.class)))
	public ResponseEntity<?> removeById(@PathVariable("id") String id) throws Exception {
		DeleteResDataDto result = rolesService.removeById(id);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
