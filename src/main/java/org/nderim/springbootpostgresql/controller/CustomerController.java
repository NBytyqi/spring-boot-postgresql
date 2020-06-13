package org.nderim.springbootpostgresql.controller;

import org.nderim.springbootpostgresql.domain.Customer;
import org.nderim.springbootpostgresql.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository repository;

    @GetMapping("/bulkcreate")
    public String bulkcreate() {
        repository.saveAll(Arrays.asList(new Customer("Nderim", "Bytyqi"),
                new Customer("John", "Doe")));
        return "Customers are created";
    }

    @PostMapping("/create")
    public String create(@RequestBody Customer customer) {
        repository.save(customer);
        return "Customer is created";
    }

    @GetMapping("/findAll")
    public List<Customer> findAll() {
        return repository.findAll();
    }

    @GetMapping("/customer/{id}")
    public Customer findById(@PathVariable Long id) throws Exception {
        Optional<Customer> opt = repository.findById(id);
        opt.orElseThrow(() -> new Exception("Customer not found"));

        return opt.get();
    }

    @GetMapping("/customer/firstname/{firstName}")
    public List<Customer> findByFirstName(@PathVariable String firstName) {
        return repository.findByFirstName(firstName);
    }
}