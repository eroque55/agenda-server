package com.roque.agenda.controllers;

import com.roque.agenda.models.Contact;
import com.roque.agenda.models.Customer;
import com.roque.agenda.models.DomainEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {
    Controller controller = new Controller();

    @GetMapping("/")
    public ResponseEntity<?> getContacts() {
        try {
            Contact contact = new Contact();
            List<DomainEntity> contacts = controller.listAll(contact);
            return ResponseEntity.ok(contacts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getContact(@PathVariable("id") int id) {
        try {
            Contact contact = new Contact();
            contact.setId(id);
            DomainEntity respContact = controller.read(contact);
            return ResponseEntity.ok(respContact);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> createContact(@PathVariable("id") int customerId, @RequestBody Contact contact) {
        try {
            Customer customer = new Customer();
            customer.setId(customerId);
            contact.setCustomer(customer);
            DomainEntity respContact = controller.create(contact);
            return ResponseEntity.ok(respContact);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContact(@PathVariable("id") int id) {
        try {
            Contact contact = new Contact();
            contact.setId(id);
            controller.delete(contact);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("id") int id, @RequestBody Contact contact) {
        try {
            contact.setId(id);
            DomainEntity respContact = controller.update(contact);
            return ResponseEntity.ok(respContact);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
