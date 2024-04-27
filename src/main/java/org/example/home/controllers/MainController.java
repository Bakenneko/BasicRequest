package org.example.home.controllers;

import org.example.home.models.Customer;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping({"/customers"})
    public ResponseEntity<List<Customer>> getCustomers() {
        return new ResponseEntity<>(
                this.customers, HttpStatus.OK);
    }

    @PostMapping("/customers")
    public ResponseEntity<List<Customer>> addCustomer(@RequestBody Customer customer) {
        System.out.println(customer);
        this.customers.add(customer);
        return new ResponseEntity<>(this.customers,HttpStatus.CREATED);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer>
    getCustomer(@PathVariable int id) {
        System.out.println(id);
        Customer customer = this.customers.get(id-1);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
    @DeleteMapping("/customers/{id}")
    public ResponseEntity<List<Customer>> deleteCustomer(@PathVariable int id) {
        this.customers.remove(id-1);
        return new ResponseEntity<>(this.customers, HttpStatus.valueOf(200));
    }
}





//    @GetMapping({"/hello","/"})
//    public String hello() {
//          return "Hello Andrew;"
//    }
//


