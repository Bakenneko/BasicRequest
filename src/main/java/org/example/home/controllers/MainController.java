package org.example.home.controllers;

import org.example.home.models.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {
    List<Customer> customers = new ArrayList<>();

    public MainController() {
        customers.add(new Customer(1,"Jack"));
        customers.add(new Customer(2,"Nick"));
        customers.add(new Customer(3,"Piter"));
    }

    @GetMapping({"/hello","/"})
    public List<Customer> hello() {
        return customers;
    }

//    @GetMapping({"/hello","/"})
//    public String hello() {
//        return "Hello Andrew;";
//    }

}
