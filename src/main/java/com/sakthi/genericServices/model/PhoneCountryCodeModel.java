package com.sakthi.genericServices.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter@Setter@Document(collection = "phoneCountryCode")
public class PhoneCountryCodeModel {
    @Id
    private String id;
    private String code;
    private String label;
    private String phone;
    private Object phoneLength;
    private int min;
    private int max;
}
