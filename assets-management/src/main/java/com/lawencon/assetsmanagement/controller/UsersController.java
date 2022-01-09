package com.lawencon.assetsmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.statusassets.FindByIdResStatusAsstesDto;
import com.lawencon.assetsmanagement.dto.users.FindAllResUsersDto;
import com.lawencon.assetsmanagement.dto.users.FindByIdResUsersDto;
import com.lawencon.assetsmanagement.model.Users;
import com.lawencon.assetsmanagement.service.UsersService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("users")
public class UsersController extends BaseIamController{

	@Autowired
	private UsersService usersService;
	
	@GetMapping
	@ApiResponse(responseCode = "200", description = "Successfuly Get Data", content = @Content(schema = @Schema (implementation = FindAllResUsersDto.class)))
	public ResponseEntity<?> findAll() throws Exception {
		FindAllResUsersDto result = usersService.findAll();
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	@ApiResponse(responseCode = "200", description = "Successfuly Get Data", content = @Content(schema = @Schema (implementation = FindByIdResUsersDto.class)))
	public ResponseEntity<?> findById(@PathVariable("id") String id) throws Exception {
		FindByIdResUsersDto result = usersService.findById(id);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping
	@ApiResponse(responseCode = "201", description = "Successfuly Insert Data", content = @Content(schema = @Schema (implementation = InsertResDto.class)))
	public ResponseEntity<?> insert(@RequestBody Users data) throws Exception {
		InsertResDto users = usersService.insert(data);
		
		return new ResponseEntity<>(users, HttpStatus.OK);	
	}
	
	@PutMapping
	@ApiResponse(responseCode = "200", description = "Successfuly Update Data", content = @Content(schema = @Schema (implementation = UpdateResDto.class)))
	public ResponseEntity<?> update(@RequestBody Users data) throws Exception {
		UpdateResDto users = usersService.update(data);
		
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@PatchMapping("password")
	@ApiResponse(responseCode = "200", description = "Successfuly Change Data", content = @Content(schema = @Schema (implementation = UpdateResDto.class)))
	public ResponseEntity<?> updatePassword(@RequestBody String data) throws Exception {
		
		UpdateResDto users = usersService.updatePassword(data);
		
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	@ApiResponse(responseCode = "200", description = "Successfuly Delete Data", content = @Content(schema = @Schema (implementation = DeleteResDataDto.class)))
	public ResponseEntity<?> removeById(@PathVariable("id") String id) throws Exception {
		DeleteResDataDto result = usersService.removeById(id);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}