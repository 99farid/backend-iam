package com.lawencon.assetsmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.files.FindByIdResFilesDto;
import com.lawencon.assetsmanagement.service.FilesService;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("files")
public class FilesController extends BaseIamController {
	@Autowired
	private FilesService filesService;
	
	
	
	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable("id") String id) throws Exception{
		FindByIdResFilesDto result = filesService.findById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	
	@DeleteMapping ("{id}")
	public ResponseEntity<?> removeById(@PathVariable("id") String id) throws Exception{
		DeleteResDataDto result =   filesService.removeById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("img/{id}")
    public ResponseEntity<byte[]> downloadImg(@PathVariable("id") String id) throws Exception, JRException {
    	FindByIdResFilesDto result = filesService.findById(id); 
        byte[] data = result.getData().getDataFile();
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=img-file."+result.getData().getExtention());

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_OCTET_STREAM).body(data);
    }
}
