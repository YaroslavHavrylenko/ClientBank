package org.example.clientbank.service;

import lombok.RequiredArgsConstructor;
import org.example.clientbank.model.Account;
import org.example.clientbank.model.Currency;
import org.example.clientbank.model.Customer;
import org.example.clientbank.repositiry.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomerInfo(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(long id, Customer customer) {
        Customer updatedCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        updatedCustomer.setAge(customer.getAge());
        updatedCustomer.setName(customer.getName());
        updatedCustomer.setEmail(customer.getEmail());

        return customerRepository.save(updatedCustomer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public Account createAccountForCustomer(Long id, Currency currency){
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        Account account = new Account(currency, customer);
        customer.addAccount(account);
        customerRepository.save(customer);

        return account;
    }

    public boolean deleteAccountForCustomer(Long customerId, Long accountId){
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        Optional<Account> accountOpt = customer.getAccounts().stream()
                .filter(a -> a.getId().equals(accountId))
                .findFirst();
        if(accountOpt.isPresent()){
            customer.removeAccount(accountOpt.get());
            customerRepository.save(customer);
            return true;
        }

        return false;
    }
}
