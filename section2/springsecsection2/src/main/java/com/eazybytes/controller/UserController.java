package com.eazybytes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.model.Customer;
import com.eazybytes.repositories.CustomerRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final CustomerRepository customerRepository;
   @Autowired
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer){
        try{
            String hashPwd = passwordEncoder.encode(customer.getPwd());
            customer.setPwd(hashPwd);
            Customer savedCustomer = customerRepository.save(customer);
            if(savedCustomer.getId()>0){
                return ResponseEntity.status(HttpStatus.CREATED)
            .body("Successfully registered user");
            }
            else {
                 return ResponseEntity.status(HttpStatus.CREATED)
            .body("User registration failed");
            }
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("An exeption occurred "+ ex.getMessage());
        }
    }

}
