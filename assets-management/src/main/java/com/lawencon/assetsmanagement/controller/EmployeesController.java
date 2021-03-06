package com.lawencon.assetsmanagement.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.employees.FindAllResEmployeesDto;
import com.lawencon.assetsmanagement.dto.employees.FindByIdResEmployeesDto;
import com.lawencon.assetsmanagement.dto.employees.FindByResNipDto;
import com.lawencon.assetsmanagement.model.Employees;
import com.lawencon.assetsmanagement.service.EmployeesService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("employees")
public class EmployeesController extends BaseIamController{

	@Autowired
	private EmployeesService employeesService;
	
	@Autowired
	private Executor executor;
	
	@GetMapping
	@ApiResponse(responseCode = "200", description = "Successfuly Get Data", content = @Content(schema = @Schema (implementation = FindAllResEmployeesDto.class)))
	public ResponseEntity<?> findAll() throws Exception {
		FindAllResEmployeesDto result = employeesService.findAll();
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	@ApiResponse(responseCode = "200", description = "Successfuly Get Data", content = @Content(schema = @Schema (implementation = FindByIdResEmployeesDto.class)))
	public ResponseEntity<?> findById(@PathVariable("id") String id) throws Exception {
		FindByIdResEmployeesDto result = employeesService.findById(id);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("nip")
	@ApiResponse(responseCode = "200", description = "Successfuly Get Data", content = @Content(schema = @Schema (implementation = FindByResNipDto.class)))
	public ResponseEntity<?> findByNip(@RequestParam("q") String nip) throws Exception {
		FindByResNipDto result = employeesService.findByNip(nip);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping
	@ApiResponse(responseCode = "201", description = "Successfuly Insert Data", content = @Content(schema = @Schema (implementation = InsertResDto.class)))
	public ResponseEntity<?> insert(@RequestBody Employees data) throws Exception {
		InsertResDto employees = employeesService.insert(data);
		
		return new ResponseEntity<>(employees, HttpStatus.CREATED);
	}
	
	@PostMapping("excel")
	@ApiResponse(responseCode = "200", description = "Successfuly Insert Data", content = @Content(schema = @Schema (implementation = InsertResDto.class)))
	public CompletableFuture<?> insertExcel(@RequestBody MultipartFile data) throws Exception {
		return CompletableFuture.supplyAsync(() ->{
			InsertResDto result = new InsertResDto();
			try {
				result = employeesService.insertExcel(data);
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new CompletionException(e);
			}
			return new ResponseEntity<>(result, HttpStatus.OK);
		}, executor);	
		
	}
	
	@GetMapping("excel")
	@ApiResponse(responseCode = "200", description = "Successfuly Get Data")
    public ResponseEntity<byte[]> generateExcel() throws Exception, JRException {
    	
        byte[] data = employeesService.downloadTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=template-data.xls");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_OCTET_STREAM).body(data);
    }
	
	@PutMapping
	@ApiResponse(responseCode = "201", description = "Successfuly Update Data", content = @Content(schema = @Schema (implementation = UpdateResDto.class)))
	public ResponseEntity<?> update(@RequestBody Employees data) throws Exception {
		UpdateResDto employees = employeesService.update(data);
		
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	@ApiResponse(responseCode = "200", description = "Successfuly Delete Data", content = @Content(schema = @Schema (implementation = DeleteResDataDto.class)))
	public ResponseEntity<?> removeById(@PathVariable("id") String id) throws Exception {
		DeleteResDataDto result = employeesService.removeById(id);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}