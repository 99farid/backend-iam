package com.lawencon.assetsmanagement.email;

import java.util.Collection;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.lawencon.assetsmanagement.helper.EmailModel;
import com.lawencon.util.JasperUtil;

@Component
//@Profile("kkk")
public class EmailHandler {
	@Autowired
    protected JavaMailSender mailSender;

    @Async
    public void sendEmail(EmailModel data) throws Exception{


        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("i.assetsmanagementlawencon@gmail.com");
        helper.setTo(data.getTo());
        helper.setSubject(data.getSubject());
        helper.setText(data.getText(),true);
       
        mailSender.send(message);
    }
    
    public void sendMailWithAttachmentJasper(EmailModel email, Collection<?> datas, 
    		String jrxmlName, Map<String, Object> mapParams) throws Exception {


        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        
        ByteArrayResource attachment = new ByteArrayResource(JasperUtil.responseToByteArray(datas, jrxmlName, mapParams));

        helper.setFrom("noreply@baeldung.com");
        helper.setTo(email.getTo());
        helper.setSubject(email.getSubject());
        helper.setText(email.getText(),true);
        helper.addAttachment(jrxmlName + ".pdf", attachment);
        mailSender.send(message);
    	
   }
    
}
