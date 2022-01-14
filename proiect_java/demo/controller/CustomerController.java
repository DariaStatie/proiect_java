package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.model.Product;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/get")
    public ResponseEntity<List<Customer>> getCustomers() {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomers());
    }

    @GetMapping("get/id/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomerById(id));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/get/firstname/{firstName}/lastname/{lastName}")
    public ResponseEntity<?> getCustomer(@PathVariable String firstName, @PathVariable String lastName) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomerByName(firstName, lastName));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody @Valid Customer customer) {
        Customer createdCustomer = customerService.addCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editCustomer(@RequestBody @Valid Customer newCustomer) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(customerService.editCustomer(newCustomer));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(customerService.deleteCustomer(id));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
