package com.sakthi.genericServices.repository;

import com.sakthi.genericServices.model.PhoneCountryCodeModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhoneCountryCodeRepo extends MongoRepository<PhoneCountryCodeModel,String> {
}
