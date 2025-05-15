package com.eazybytes.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eazybytes.model.Customer;
import java.util.Optional;


@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long> {

     Optional<Customer> findByEmail(String email);

}
