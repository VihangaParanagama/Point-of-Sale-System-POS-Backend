package com.ijse.database.service;

import com.ijse.database.entity.CustomerEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CustomerService {
    List<CustomerEntity> getAllCustomers();
    Optional<CustomerEntity> getCustomerById(long customerId);
    CustomerEntity saveCustomer(CustomerEntity customer);
    void deleteCustomer(long customerId);

}
