package com.lawencon.assetsmanagement.controller.transactions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.transactionsin.FindAllForPdfTrxInDto;
import com.lawencon.assetsmanagement.dto.transactionsin.FindAllResTransactionsInDto;
import com.lawencon.assetsmanagement.dto.transactionsin.FindByIdResTransactionsInDto;
import com.lawencon.assetsmanagement.dto.transactionsin.InsertReqDataHeaderTransactionsInDto;
import com.lawencon.assetsmanagement.service.TransactionsInService;
import com.lawencon.util.JasperUtil;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("transactions-in")
public class TransactionsInController {
	@Autowired
	private TransactionsInService transactionInService;
	
	@GetMapping
	public ResponseEntity<?> findAll() throws Exception{
		FindAllResTransactionsInDto result = transactionInService.findAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable("id") String id) throws Exception{
		FindByIdResTransactionsInDto result = transactionInService.findById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> insert (@RequestBody InsertReqDataHeaderTransactionsInDto data) throws Exception{
		InsertResDto result = transactionInService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	
	@GetMapping("/pdf")
    public ResponseEntity<byte[]> generatePdf() throws Exception, JRException {
    	Map<String, Object> map = new HashMap<>();
		map.put("company", "PT. Lawencon Internasional");
        
		FindAllForPdfTrxInDto result = transactionInService.findAllForPdf();
        byte[] data = JasperUtil.responseToByteArray(result.getData(), "transactions-in", map);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=transactions-in.pdf");
        headers.setContentType(MediaType.APPLICATION_PDF);

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
    }
}
