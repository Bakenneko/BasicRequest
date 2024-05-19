package org.example.home.controllers;//package org.example.home.controllers;
//import org.example.home.models.Customer;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//public class MainController {
//    List<Customer> customers = new ArrayList<>();
//
//    public MainController() {
//        customers.add(new Customer(1,"Jack"));
//        customers.add(new Customer(2,"Nick"));
//        customers.add(new Customer(3,"Piter"));
//    }
//
//    @GetMapping({"/customers"})
//    public ResponseEntity<List<Customer>> getCustomers() {
//        return new ResponseEntity<>(this.customers, HttpStatus.OK);
//    }
//
//    @PostMapping("/customers")
//    public ResponseEntity<List<Customer>> addCustomer(@RequestBody Customer customer) {
//        System.out.println(customer);
//        this.customers.add(customer);
//        return new ResponseEntity<>(this.customers,HttpStatus.CREATED);
//    }
//
//    @GetMapping("/customers/{id}")
//    public ResponseEntity<Customer>
//    getCustomer(@PathVariable int id) {
//        System.out.println(id);
//        Customer customer = this.customers.get(id-1);
//        return new ResponseEntity<>(customer, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/customers/{id}")
//    public ResponseEntity<List<Customer>> deleteCustomer(@PathVariable int id) {
//        this.customers.remove(id-1);
//        return new ResponseEntity<>(this.customers, HttpStatus.valueOf(200));
//    }
//
//    @PutMapping("/customers/{id}")
//    public ResponseEntity replaceCustomer(@PathVariable int id, @RequestBody Customer customer) {
//        Customer custo = customers.stream()
//                .filter(customer1 -> customer1.getId() == id)
//                .findFirst()
//                .get();
//                System.out.println(custo);
//        int indexOf = customers.indexOf(custo);
//        customers.set(indexOf,customer);
//        return new ResponseEntity(HttpStatusCode.valueOf(201));
//    }
//
//        @PatchMapping("/customers/{id}")
//        @ResponseStatus(HttpStatus.ACCEPTED)
//        public void updateCustomer(@PathVariable int id, @RequestBody Customer customer) {
//            for (Customer item : customers) {
//                if (item.getId() == id) {
//                    item.setId(customer.getId());
//                    item.setName(customer.getName());
//                }
//            }
//        }
//}





//    @GetMapping({"/hello","/"})
//    public String hello() {
//          return "Hello Andrew;"
//    }
//

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import org.example.home.dao.CustomerDAO;
import org.example.home.models.Customer;
import org.example.home.dto.CustomerDTO;
import org.example.home.services.Services;
import org.example.home.views.Views;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class MainController {

    private CustomerDAO customerDAO;

    private Services services;

    @GetMapping("")
    @JsonView(Views.Client.class)
    public ResponseEntity <List<Customer>> getCustomers() {
       List<Customer> all = customerDAO.findAll();
       return new ResponseEntity<>(all, HttpStatusCode.valueOf(200));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCustomer(@RequestBody Customer customer) {
        services.save(customer);
    }

    @GetMapping("/{id}")
    @JsonView(Views.Admin.class)
    public ResponseEntity<Customer> getOneCustomer(@PathVariable int id) {
        Customer customer = customerDAO.findById(id).get();
        return new ResponseEntity<>(customer, HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable int id) {
        customerDAO.deleteById(id);
    }

    @PatchMapping("/{id}")
    public void updateCustomer(@PathVariable int id, @RequestBody CustomerDTO customerDTO) {
        Customer customer = customerDAO.findById(id).get();
        customer.setName(customerDTO.getUsername());
        customerDAO.save(customer);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity <List<Customer>> getCustomerByName(@PathVariable String name) {
//        return new ResponseEntity<>(customerDAO.getByName("Igor"), HttpStatusCode.valueOf(200));
//        return new ResponseEntity<>(customerDAO.findCustomerByName("Igor"), HttpStatusCode.valueOf(200));
//        return new ResponseEntity<>(services.customerListByName("Igor"), HttpStatusCode.valueOf(200));

        return services.customerListByName(name);
    }
}


//////////////////////////////