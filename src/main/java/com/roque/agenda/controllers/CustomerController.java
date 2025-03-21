package com.roque.agenda.controllers;

import com.roque.agenda.models.Customer;
import com.roque.agenda.models.DomainEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    Controller controller = new Controller();

    @GetMapping("/")
    public ResponseEntity<?> getCustomers() {
        try {
            Customer customer = new Customer();
            List<DomainEntity> customers = controller.listAll(customer);
            return ResponseEntity.ok(customer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public DomainEntity getCustomer(@PathVariable("id") int id) {
        Customer customer = new Customer();
        customer.setId(id);
        return controller.read(customer);
    }

    @PostMapping("/")
    public DomainEntity createCustomer(@RequestBody Customer customer) {
        return controller.create(customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") int id) {
        Customer customer = new Customer();
        customer.setId(id);
        controller.delete(customer);
    }

    @PutMapping("/{id}")
    public DomainEntity updateCustomer(@PathVariable("id") int id, @RequestBody Customer customer) {
        customer.setId(id);
        return controller.update(customer);
    }
}
