package com.roque.agenda.controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.roque.agenda.models.Customer;
import com.roque.agenda.models.DomainEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerController {
    Controller controller = new Controller();

    @GetMapping("/")
    public ResponseEntity<?> getCustomers(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "cpf", required = false) String cpf ) {
        try {
            Customer customer = new Customer();

            if (name != null) customer.setName(name);
            if (cpf != null) customer.setCpf(cpf);

            List<DomainEntity> customers = controller.listAll(customer);
            return ResponseEntity.ok(customers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable("id") int id) {
        try {
            Customer customer = new Customer();
            customer.setId(id);

            DomainEntity respCustomer = controller.read(customer);
            return ResponseEntity.ok(respCustomer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        try {
            DomainEntity respCustomer = controller.create(customer);
            return ResponseEntity.ok(respCustomer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") int id) {
        try {
            Customer customer = new Customer();
            customer.setId(id);
            controller.delete(customer);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("id") int id, @RequestBody Customer customer) {
        try {
            customer.setId(id);
            DomainEntity respCustomer = controller.update(customer);
            return ResponseEntity.ok(respCustomer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
