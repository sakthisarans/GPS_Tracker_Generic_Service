package com.sakthi.genericServices.controller;

import com.sakthi.genericServices.model.MailRequest;
import com.sakthi.genericServices.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tracker/generic")
public class GenericController {
    @Autowired
    EmailService emailService;
    @PostMapping("/sendmail")
    public ResponseEntity<?> sendEmail(@RequestBody MailRequest mailRequest){
        return emailService.sendEmail(mailRequest);
    }
}
