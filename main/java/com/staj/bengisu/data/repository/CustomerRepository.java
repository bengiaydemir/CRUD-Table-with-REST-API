package com.staj.bengisu.data.repository;

import com.staj.bengisu.data.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    //define functions for filtering
    List <Customer> findByName(String name);
    List <Customer> findByLastname(String lastname);






}
