package com.lawencon.assetsmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.profileusers.FindAllResProfileUsersDto;
import com.lawencon.assetsmanagement.dto.profileusers.FindByIdResProfileUsersDto;
import com.lawencon.assetsmanagement.dto.profileusers.FindByResUserIdDto;
import com.lawencon.assetsmanagement.dto.profileusers.InsertReqDataProfileUsersDto;
import com.lawencon.assetsmanagement.model.ProfileUsers;
import com.lawencon.assetsmanagement.service.ProfileUsersService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("profile-users")
public class ProfileUserController extends BaseIamController{

	@Autowired
	private ProfileUsersService profileUsersService;
	
	@GetMapping
	@ApiResponse(responseCode = "200", description = "Successfuly Get Data", content = @Content(schema = @Schema (implementation = FindAllResProfileUsersDto.class)))
	public ResponseEntity<?> findAll() throws Exception {
		FindAllResProfileUsersDto result = profileUsersService.findAll();
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	@ApiResponse(responseCode = "200", description = "Successfuly Get Data", content = @Content(schema = @Schema (implementation = FindByIdResProfileUsersDto.class)))
	public ResponseEntity<?> findById(@PathVariable("id") String id) throws Exception {
		FindByIdResProfileUsersDto result = profileUsersService.findById(id);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("user")
	@ApiResponse(responseCode = "200", description = "Successfuly Get Data", content = @Content(schema = @Schema (implementation = FindByResUserIdDto.class)))
	public ResponseEntity<?> findByUserId() throws Exception {
		FindByResUserIdDto result = profileUsersService.findByUser();
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping
	@ApiResponse(responseCode = "201", description = "Successfuly Insert Data", content = @Content(schema = @Schema (implementation = InsertResDto.class)))
	public ResponseEntity<?> insert(@RequestPart String data, @RequestPart MultipartFile file) throws Exception {
		InsertResDto profileUsers = profileUsersService.insert(new ObjectMapper().readValue(data, InsertReqDataProfileUsersDto.class), file);
		
		return new ResponseEntity<>(profileUsers, HttpStatus.CREATED);
	}
	
	@PutMapping
	@ApiResponse(responseCode = "200", description = "Successfuly Update Data", content = @Content(schema = @Schema (implementation = UpdateResDto.class)))
	public ResponseEntity<?> update(@RequestPart String data, @RequestPart(required = false) MultipartFile file) throws Exception {
		UpdateResDto profileUsers = profileUsersService.update(convertToModel(data, ProfileUsers.class), file);
		
		return new ResponseEntity<>(profileUsers, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	@ApiResponse(responseCode = "200", description = "Successfuly Delete Data", content = @Content(schema = @Schema (implementation = DeleteResDataDto.class)))
	public ResponseEntity<?> removeById(@PathVariable("id") String id) throws Exception {
		DeleteResDataDto result = profileUsersService.removeById(id);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}