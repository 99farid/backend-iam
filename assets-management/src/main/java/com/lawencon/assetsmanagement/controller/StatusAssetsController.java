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
import com.lawencon.assetsmanagement.dto.statusassets.FindAllFilterBySearchResStatusAsstesDto;
import com.lawencon.assetsmanagement.dto.statusassets.FindAllForNewAssetResStatusAsstesDto;
import com.lawencon.assetsmanagement.dto.statusassets.FindAllResStatusAsstesDto;
import com.lawencon.assetsmanagement.dto.statusassets.FindByIdResStatusAsstesDto;
import com.lawencon.assetsmanagement.model.StatusAssets;
import com.lawencon.assetsmanagement.service.StatusAssetsService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("status-assets")
public class StatusAssetsController extends BaseIamController{
	@Autowired
	private StatusAssetsService statusService;
	
	@GetMapping
	@ApiResponse(responseCode = "200", description = "Successfuly Get Data", content = @Content(schema = @Schema (implementation = FindAllResStatusAsstesDto.class)))
	public ResponseEntity<?> findAll() throws Exception{
		FindAllResStatusAsstesDto result = statusService.findAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	@GetMapping("for-new-asset")
	@ApiResponse(responseCode = "200", description = "Successfuly Get Data", content = @Content(schema = @Schema (implementation = FindAllForNewAssetResStatusAsstesDto.class)))
	public ResponseEntity<?> findAllForNewAsset() throws Exception{
		FindAllForNewAssetResStatusAsstesDto result = statusService.findAllForNewAsset();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	@ApiResponse(responseCode = "200", description = "Successfuly Get Data", content = @Content(schema = @Schema (implementation = FindByIdResStatusAsstesDto.class)))
	public ResponseEntity<?> findById(@PathVariable("id") String id) throws Exception{
		FindByIdResStatusAsstesDto result = statusService.findById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("search")
	@ApiResponse(responseCode = "200", description = "Successfuly Get Data", content = @Content(schema = @Schema (implementation = FindAllFilterBySearchResStatusAsstesDto.class)))
	public ResponseEntity<?> findAllFilterBySearch(@RequestParam("query") String input) throws Exception{
		FindAllFilterBySearchResStatusAsstesDto result = statusService.findAllFilterBySearch(input);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping
	@ApiResponse(responseCode = "201", description = "Successfuly Insert Data", content = @Content(schema = @Schema (implementation = InsertResDto.class)))
	public ResponseEntity<?> insert (@RequestBody StatusAssets data) throws Exception{
		InsertResDto result = statusService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	
	@PutMapping
	@ApiResponse(responseCode = "200", description = "Successfuly Update Data", content = @Content(schema = @Schema (implementation = UpdateResDto.class)))
	public ResponseEntity<?> update (@RequestBody StatusAssets data) throws Exception{
		UpdateResDto result = statusService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping ("{id}")
	@ApiResponse(responseCode = "200", description = "Successfuly Delete Data", content = @Content(schema = @Schema (implementation = DeleteResDataDto.class)))
	public ResponseEntity<?> removeById(@PathVariable("id") String id) throws Exception{
		DeleteResDataDto result =   statusService.removeById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
