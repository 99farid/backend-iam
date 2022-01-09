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
import com.lawencon.assetsmanagement.dto.conditionassets.FindAllResConditionAssetsDto;
import com.lawencon.assetsmanagement.dto.conditionassets.FindByIdResConditionAssetsDto;
import com.lawencon.assetsmanagement.model.ConditionAssets;
import com.lawencon.assetsmanagement.service.ConditionAssetsService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


@RestController
@RequestMapping("condition-assets")
public class ConditionAssetsController extends BaseIamController{

	@Autowired
	private ConditionAssetsService conditonService;
	
	@GetMapping
	@ApiResponse(responseCode = "200", description = "Successfuly Get Data", content = @Content(schema = @Schema (implementation = FindAllResConditionAssetsDto.class)))
	public ResponseEntity<?> findAll() throws Exception{
		FindAllResConditionAssetsDto result = conditonService.findAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	@ApiResponse(responseCode = "200", description = "Successfuly Get Data", content = @Content(schema = @Schema (implementation = FindByIdResConditionAssetsDto.class)))
	public ResponseEntity<?> findById(@PathVariable("id") String id) throws Exception{
		FindByIdResConditionAssetsDto result = conditonService.findById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping
	@ApiResponse(responseCode = "201", description = "Successfuly Insert Data", content = @Content(schema = @Schema (implementation = InsertResDto.class)))
	public ResponseEntity<?> insert (@RequestBody ConditionAssets data) throws Exception{
		InsertResDto result = conditonService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	
	@PutMapping
	@ApiResponse(responseCode = "200", description = "Successfuly Update Data", content = @Content(schema = @Schema (implementation = UpdateResDto.class)))
	public ResponseEntity<?> update (@RequestBody ConditionAssets data) throws Exception{
		UpdateResDto result = conditonService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping ("{id}")
	@ApiResponse(responseCode = "200", description = "Successfuly Delete Data", content = @Content(schema = @Schema (implementation = DeleteResDataDto.class)))
	public ResponseEntity<?> removeById(@PathVariable("id") String id) throws Exception{
		DeleteResDataDto result =   conditonService.removeById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
