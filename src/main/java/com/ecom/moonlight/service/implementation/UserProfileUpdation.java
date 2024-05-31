package com.ecom.moonlight.service.implementation;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.moonlight.dto.CustomerDetail;
import com.ecom.moonlight.entity.CustomerAddress;
import com.ecom.moonlight.entity.CustomerMaster;
import com.ecom.moonlight.entity.CustomerPayment;
import com.ecom.moonlight.logging.Mlogger;
import com.ecom.moonlight.repository.CustomerMasterRepo;


@Service
public class UserProfileUpdation {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CustomerMasterRepo customerMasterRepo;
    

    Mlogger logger=Mlogger.getlogger(UserProfileUpdation.class);

   
    public void updateCustomerDetails(CustomerDetail customerDetail,String uuid){
        logger.debug("Inside method updateCustomerDetails method : execution started:  CustomerDetail dto is {}", customerDetail);
       CustomerMaster customerMaster= modelMapper.map(customerDetail,CustomerMaster.class);
       customerMaster.setUserId(uuid);
       setUserIdToChildEntity(customerMaster,uuid);
       customerMasterRepo.save(customerMaster);
       logger.debug("Inside method updateCustomerDetails method : executed successfully: customer updated successfully");
  
    }

    private void setUserIdToChildEntity(CustomerMaster customerMaster,String uuid) {
        logger.debug("Inside method setUserIdToChildEntity method : execution started: customerMaster  and uuid ");
           CustomerMaster customerMasterWithId=new CustomerMaster();
           customerMasterWithId.setUserId(uuid);
            customerMaster.getCustomerAddress().forEach(customAdd ->customAdd.setCustomerMaster(customerMasterWithId));
            customerMaster.getCustomerPayment().forEach(cudtPay -> cudtPay.setCustomerMaster(customerMasterWithId));
            logger.debug("Inside method setUserIdToChildEntity method : executed successfully: customerMaster updated successfully");
           
            
        
    }
    
}
