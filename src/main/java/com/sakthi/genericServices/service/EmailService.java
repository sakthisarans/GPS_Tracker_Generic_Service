package com.sakthi.genericServices.service;

import com.sakthi.genericServices.model.MailRequest;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService  {
    @Autowired
    JavaMailSender mailSender;
    public ResponseEntity<?> sendEmail(MailRequest mailRequest){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailRequest.getTo());
//        message.setCc(mailRequest.getCc());
//        message.setBcc(message.getBcc());
        message.setSubject(mailRequest.getSubject());
        message.setText(mailRequest.getBody());

        mailSender.send(message);
        return ResponseEntity.ok("Email Sent");
    }
}
