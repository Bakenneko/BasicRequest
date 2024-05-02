package org.example.home.services;

import lombok.AllArgsConstructor;
import org.example.home.dao.CustomerDAO;
import org.example.home.models.Customer;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service

public class Services {

    private CustomerDAO customerDAO;

    public void save(Customer customer) {
        if (customer.getId() > 0) {
            customerDAO.save(customer);
        } else {
            throw new RuntimeException("id < 0");
        }
    }

    public ResponseEntity<List<Customer>> customerListByName(String name) {
        if (name != null && !name.isBlank()) {
          return  new ResponseEntity<>(customerDAO.findCustomerByName(name), HttpStatusCode.valueOf(200));
        } else {
            throw new RuntimeException("name is empty");
        }
    }
}