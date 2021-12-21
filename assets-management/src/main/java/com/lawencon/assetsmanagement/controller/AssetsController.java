package com.lawencon.assetsmanagement.controller;

import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.assets.CountAssetByStatusResAssetsDto;
import com.lawencon.assetsmanagement.dto.assets.CountAssetResAssetsDto;
import com.lawencon.assetsmanagement.dto.assets.FindAllForPdfAssetsExpiredDto;
import com.lawencon.assetsmanagement.dto.assets.FindAllResAssetsDto;
import com.lawencon.assetsmanagement.dto.assets.FindByIdResAssetsDto;
import com.lawencon.assetsmanagement.dto.assets.InsertReqDataAssetsDto;
import com.lawencon.assetsmanagement.dto.trackactivity.FindAllResTrackActivityDto;
import com.lawencon.assetsmanagement.model.Assets;
import com.lawencon.assetsmanagement.service.AssetsService;
import com.lawencon.util.JasperUtil;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("assets")
public class AssetsController {
	
	@Autowired
	private AssetsService assetsService;
	
	@GetMapping
	public ResponseEntity<?> findAll() throws Exception{
		FindAllResAssetsDto result = assetsService.findAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable("id") String id) throws Exception{
		FindByIdResAssetsDto result = assetsService.findById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	private <T> T convertToModel (String src, Class<T> clazz) throws Exception{
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		return new ObjectMapper()
				.registerModule(javaTimeModule)
				.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
				.readValue(src, clazz);
	}
	
	@PostMapping
	public ResponseEntity<?> insert (@RequestPart String data, @RequestPart MultipartFile display, @RequestPart MultipartFile invoicePict ) throws Exception{
		InsertResDto result = assetsService.insert(convertToModel(data, InsertReqDataAssetsDto.class), display, invoicePict);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<?> update (@RequestBody Assets data) throws Exception{
		UpdateResDto result = assetsService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping ("{id}")
	public ResponseEntity<?> removeById(@PathVariable("id") String id) throws Exception{
		DeleteResDataDto result =   assetsService.removeById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping ("count")
	public ResponseEntity<?> countAsset() throws Exception{
		CountAssetResAssetsDto result =   assetsService.countAsset();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	@GetMapping ("count-by-status")
	public ResponseEntity<?> countAssetByStatus(@RequestParam String code) throws Exception{
		CountAssetByStatusResAssetsDto result =   assetsService.countAssetByStatus(code);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
//	public ResponseEntity<?> update(@RequestPart String data, MultipartFile file) throws Exception {
//		UpdateResDto profileUsers = assetsService.update(new ObjectMapper().readValue(data, ProfileUsers.class), file);
//		
//		return new ResponseEntity<>(profileUsers, HttpStatus.OK);
//	}

	@GetMapping(value = "pic/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getPic(@PathVariable("id") String id) throws Exception {
		return assetsService.findById(id).getData().getDisplay().getDataFile();
	}
	
	@GetMapping("/pdf")
    public ResponseEntity<byte[]> generatePdf() throws Exception, JRException {
    	Map<String, Object> map = new HashMap<>();
		map.put("company", "PT. Lawencon Internasional");
        
        FindAllForPdfAssetsExpiredDto result = assetsService.findAllForPdf();
        byte[] data = JasperUtil.responseToByteArray(result.getData(), "asset-expired", map);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=asset-expired.pdf");
        headers.setContentType(MediaType.APPLICATION_PDF);

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
    }
}
