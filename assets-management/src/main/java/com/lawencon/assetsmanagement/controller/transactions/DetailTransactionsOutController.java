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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.assetsmanagement.dto.SendResEmailDto;
import com.lawencon.assetsmanagement.dto.detailtransactionsout.FindAllForPdfTrxExpiredDto;
import com.lawencon.assetsmanagement.dto.detailtransactionsout.FindAllResDetailTransactionsOutDto;
import com.lawencon.assetsmanagement.dto.detailtransactionsout.FindByIdResDetailTransactionsOutDto;
import com.lawencon.assetsmanagement.service.DetailTransactionsOutService;
import com.lawencon.util.JasperUtil;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("detail-transactions-out")
public class DetailTransactionsOutController {

	@Autowired
	private DetailTransactionsOutService detailTransactionsOutService;
	
	@GetMapping
	public ResponseEntity<?> findAll() throws Exception {
		FindAllResDetailTransactionsOutDto result = detailTransactionsOutService.findAll();
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable("id") String id) throws Exception {
		FindByIdResDetailTransactionsOutDto result = detailTransactionsOutService.findById(id);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("header/{idHeader}")
	public ResponseEntity<?> findByIdHeader(@PathVariable("idHeader") String idHeader) throws Exception {
		FindByIdResDetailTransactionsOutDto result = detailTransactionsOutService.findById(idHeader);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/pdf")
    public ResponseEntity<byte[]> generatePdf() throws Exception, JRException {
    	Map<String, Object> map = new HashMap<>();
		map.put("company", "PT. Lawencon Internasional");
        
        FindAllForPdfTrxExpiredDto result = detailTransactionsOutService.findAllForPdf();
        byte[] data = JasperUtil.responseToByteArray(result.getData(), "transaction-expired", map);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=transaction-expired.pdf");
        headers.setContentType(MediaType.APPLICATION_PDF);

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
    }
    
    @GetMapping("/send-pdf")
	public ResponseEntity<?> sendFileToEmail() throws Exception {
		SendResEmailDto result = detailTransactionsOutService.sendFileToEmail();
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}