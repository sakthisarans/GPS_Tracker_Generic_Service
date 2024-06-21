package com.sakthi.genericServices.model;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class MailRequest {
    private String to;
    private String cc;
    private String bcc;
    private String subject;
    private String body;
}
