package com.eazybytes.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.eazybytes.model.Customer;
import com.eazybytes.repositories.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EazyBankUserDetailService implements UserDetailsService {

    @Autowired
    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Customer customer =  customerRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User details not found for the user "+username));
      List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(customer.getRole()));
      return new User(customer.getEmail(),customer.getPwd(),authorities);
    }

}
