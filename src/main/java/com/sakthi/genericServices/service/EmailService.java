package com.sakthi.genericServices.service;

import com.sakthi.genericServices.model.MailRequest;
import com.sakthi.genericServices.model.PhoneCountryCodeModel;
import com.sakthi.genericServices.repository.PhoneCountryCodeRepo;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService  {
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    PhoneCountryCodeRepo phoneCountryCodeRepo;
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
    public ResponseEntity<List<PhoneCountryCodeModel>> savePhoneCode(List<PhoneCountryCodeModel> data){
        return new ResponseEntity<>(phoneCountryCodeRepo.saveAll(data), HttpStatus.OK);
    }
    public ResponseEntity<List<PhoneCountryCodeModel>> getPhoneCode(){
        return new ResponseEntity<>(phoneCountryCodeRepo.findAll(), HttpStatus.OK);
    }
}
