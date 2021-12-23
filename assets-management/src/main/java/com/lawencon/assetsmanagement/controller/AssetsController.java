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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.SendResEmailDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.assets.CountAssetByStatusResAssetsDto;
import com.lawencon.assetsmanagement.dto.assets.CountAssetResAssetsDto;
import com.lawencon.assetsmanagement.dto.assets.FindAllFilterBySearchResAssetsDto;
import com.lawencon.assetsmanagement.dto.assets.FindAllFilterByTypeResAssetsDto;
import com.lawencon.assetsmanagement.dto.assets.FindAllForPdfAssetsExpiredDto;
import com.lawencon.assetsmanagement.dto.assets.FindAllResAssetsDto;
import com.lawencon.assetsmanagement.dto.assets.FindByIdResAssetsDto;
import com.lawencon.assetsmanagement.dto.assets.InsertReqDataAssetsDto;
import com.lawencon.assetsmanagement.model.Assets;
import com.lawencon.assetsmanagement.service.AssetsService;
import com.lawencon.util.JasperUtil;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("assets")
public class AssetsController extends BaseIamController{
	
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

	
	@PostMapping
	public ResponseEntity<?> insert (@RequestPart String data, @RequestPart MultipartFile display, @RequestPart(required = false) MultipartFile invoicePict ) throws Exception{
		InsertResDto result = assetsService.insert(convertToModel(data, InsertReqDataAssetsDto.class), display, invoicePict);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	@PostMapping("excel")
	public ResponseEntity<?> insertFromExcel(@RequestPart MultipartFile data ) throws Exception{
		InsertResDto result = assetsService.insertFromExcel(data);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<?> update (@RequestPart String data, @RequestPart MultipartFile display) throws Exception{
		UpdateResDto result = assetsService.update(convertToModel(data, Assets.class), display);
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
	public ResponseEntity<?> countAssetByStatus(@RequestParam("q") String code) throws Exception{
		CountAssetByStatusResAssetsDto result =   assetsService.countAssetByStatus(code);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping ("type")
	public ResponseEntity<?> findAllFilterByType(@RequestParam("q") String typeCode) throws Exception{
		FindAllFilterByTypeResAssetsDto result =   assetsService.findAllFilterByType(typeCode);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	@GetMapping ("search")
	public ResponseEntity<?> findAllFilterBySearch(@RequestParam("q") String input) throws Exception{
		FindAllFilterBySearchResAssetsDto result =   assetsService.findAllFilterBySearch(input);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

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
    
	@GetMapping("/send-pdf")
	public ResponseEntity<?> sendFileToEmail() throws Exception {
		SendResEmailDto result = assetsService.sendFileToEmail();
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
