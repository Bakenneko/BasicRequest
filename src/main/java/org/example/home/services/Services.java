package org.example.home.services;

import lombok.AllArgsConstructor;
import org.example.home.dao.CustomerDAO;
import org.example.home.models.Customer;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service

public class Services {

    private CustomerDAO customerDAO;

    public void save(Customer customer) {
        if (customer.getId() > 0) {
            customerDAO.save(customer);
        }else {
            throw new RuntimeException("id < 0");
        }
    }
}
