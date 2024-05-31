package com.ecom.moonlight.service.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.moonlight.dto.CustomerDetail;
import com.ecom.moonlight.entity.CustomerMaster;
import com.ecom.moonlight.logging.Mlogger;
import com.ecom.moonlight.repository.CustomerMasterRepo;

@Service
public class FindUserProfile {

    @Autowired
    private CustomerMasterRepo customerMasterRepo;
    @Autowired
    ModelMapper modelMapper;

    Mlogger logger= Mlogger.getlogger(FindUserProfile.class);
    
    public CustomerDetail getUserDetailById(String userId){
        logger.debug("Inside method getUserDetailById : method excecution started: to get user detail by id");
        CustomerMaster customerMaster=customerMasterRepo.findById(userId).get();
        CustomerDetail customerDetail =modelMapper.map(customerMaster, CustomerDetail.class);
        logger.debug("Inside method getUserDetailById : method excecuted succesfully: user detail found");
        return customerDetail;
        

    }
}
