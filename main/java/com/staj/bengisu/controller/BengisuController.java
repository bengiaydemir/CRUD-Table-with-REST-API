package com.staj.bengisu.controller;

import com.staj.bengisu.data.entity.Customer;
import com.staj.bengisu.data.repository.CustomerRepository;
//import com.staj.bengisu.model.DenemeModel;
import com.staj.bengisu.exception.IDNotFoundException;
import com.staj.bengisu.exception.MandatoryFieldMissingException;
import com.staj.bengisu.service.BengisuService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@ControllerAdvice

@CrossOrigin(maxAge = 3600)
@RequestMapping("/bengisu")  //next to local host
public class BengisuController {

    //@Autowired aynı şey

    @Autowired

    CustomerRepository repo;
    BengisuService bengisuService;

    //http://localhost:8080/bengisu
    @GetMapping   //lists all customers
    public List<Customer> getAllCustomer() {
        List<Customer> customers = repo.findAll();  //new local variable
        return customers;

        //return bengisuService.getAllCustomers();
    }

    //http://localhost:8080/bengisu/{id}
    @GetMapping("/{id}")  //search by id
    public Customer getCustomer(@PathVariable Long id) {
        Optional<Customer> customer = repo.findById(id);
        if(customer.isPresent()) {
            return customer.get();
        }else {
            throw new IDNotFoundException("Customer ID is not found!");
        }
        /*Customer customers = repo.findById(id).get(); //create local variable
        return customers;*/
    }

    //http://localhost:8080/bengisu/add
    @PostMapping("/add") //adds new customer
    public void createCustomer(@RequestBody Customer customer) {

        if (customer.getName().isEmpty() || customer.getLastname().isEmpty()) {
            throw new MandatoryFieldMissingException("Mandatory Field is Missing!");
        }else {
        repo.save(customer);
        }
    }

    //http://localhost:8080/bengisu/update/{id}
    @PutMapping("/update/{id}")  // Update customer with specific id
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        Customer customer = repo.findById(id)
                .orElseThrow(); // Implement your own logic for handling if customer not found

        // Update customer details
        customer.setName(updatedCustomer.getName());
        customer.setLastname(updatedCustomer.getLastname());
        customer.setAge(updatedCustomer.getAge());
        customer.setSubscriptionType(updatedCustomer.getSubscriptionType());
        customer.setContactNumber(updatedCustomer.getContactNumber());


        // Save updated customer to database
        Customer savedCustomer = repo.save(customer);

        return ResponseEntity.ok(savedCustomer);
    }


    //http://localhost:8080/bengisu/delete/{id}
    @DeleteMapping("/delete/{id}") //deletes customer w/specific id
    public void deleteCustomer(@PathVariable Long id) {

        Customer customer = repo.findById(id).get();
        repo.delete(customer);
    }

    //filtering by name and lastname
    //http://localhost:8080/bengisu/customers?name=Bengisu
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getCustomersByName(@RequestParam(required = false) String name,
                                                             @RequestParam(required = false) String lastname) {
        Customer example = new Customer();
        example.setName(name);
        example.setLastname(lastname);
        repo.findAll(Example.of(example));

        return new ResponseEntity<List<Customer>>(repo.findByName(name), HttpStatus.OK);
    }

    //auditor aware
    //http://localhost:8080/bengisu/saveCustomer
    @PostMapping("/saveCustomer")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(repo.save(customer));
    }

    //exception handlers
    @RestControllerAdvice
    public class GlobalExceptionHandler
    {
        @ExceptionHandler(IDNotFoundException.class)
        @ResponseStatus(code = HttpStatus.NOT_FOUND)
        public String runtimeExceptionHandler(IDNotFoundException ex) {
            return ex.getMsg();
        }

        @ExceptionHandler(MandatoryFieldMissingException.class)
        public ResponseEntity<String> mandatoryFieldMissingExceptionHandler(MandatoryFieldMissingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMsg());
        }

    }






    //filtering by lastname
    //http://localhost:8080/bengisu/customers?lastname=Aydemir
    /*@GetMapping("/customers")
    public ResponseEntity<List<Customer>> getCustomersByLastName(@RequestParam String lastname) {
        return new ResponseEntity<List<Customer>>(repo.findByLastname(lastname), HttpStatus.OK);
    }*/


    //first learned ones
        /*
    @GetMapping
    public String deneme(@RequestParam String name) {
        return "Hello World! " + name;
    }

    @PostMapping
    public String deneme2(@RequestBody DenemeModel model) {
        return bengisuService.getAgeSentence(model);
    }

    @GetMapping(value = "/withpathvariable/{name}")
    public String deneme3(@PathVariable String name) {
        return "Hello World! " + name;
    }
*/


}


