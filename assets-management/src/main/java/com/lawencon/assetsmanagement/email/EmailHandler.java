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

import com.lawencon.util.JasperUtil;

@Component
public class EmailHandler {
	@Autowired
    protected JavaMailSender mailSender;

    @Async
    public void sendSimpleMessage(String to, String subject, String text) throws Exception{


        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("noreply@baeldung.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText("<b>"+text+"</b>",true);
       
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
        helper.setText("<b>"+email.getText()+"</b>",true);
        helper.addAttachment(jrxmlName + ".pdf", attachment);
        mailSender.send(message);
    	
   }
    
}
