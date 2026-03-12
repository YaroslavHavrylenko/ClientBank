package org.example.clientbank.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.clientbank.controller.dto.RequestBodyCreateAccount;
import org.example.clientbank.controller.dto.RequestBodyCreateCustomer;
import org.example.clientbank.controller.dto.RequestBodyUpdateCustomer;
import org.example.clientbank.model.Account;
import org.example.clientbank.model.Currency;
import org.example.clientbank.model.Customer;
import org.example.clientbank.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerInfo(id);
        return ResponseEntity.ok(customer);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody RequestBodyCreateCustomer body) {
        Customer customer = new Customer(body.getName(), body.getEmail(), body.getAge());
        Customer createdCustomer = customerService.createCustomer(customer);
        return ResponseEntity.ok(createdCustomer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCustomer(@PathVariable Long id, RequestBodyUpdateCustomer body) {
        Customer customer = new Customer(body.getName(), body.getEmail(), body.getAge());
        customerService.updateCustomer(id, customer);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/accounts")
    public ResponseEntity<Account> createAccountForCustomer(@PathVariable Long id, @Valid @RequestBody RequestBodyCreateAccount body) {
        Account account = customerService.createAccountForCustomer(id, body.getCurrency());
        if (account != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(account);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{customerId}/account/{accountId}")
    public ResponseEntity<Void> deleteCustomerAccount(@PathVariable long customerId, @PathVariable long accountId) {
        boolean isDeleted = customerService.deleteAccountForCustomer(customerId, accountId);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
