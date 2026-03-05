package org.example.clientbank.dao;

import org.example.clientbank.model.Customer;

import java.util.*;

public class CustomerDao implements Dao<Customer> {
    private final Map<Long, Customer> customerMap = new HashMap<>();
    private long nextCustomerId = 1;

    public Customer save(Customer customer) {
        if (customer.getId() == null) {
            customer.setId(nextCustomerId++);
        }
        customerMap.put(customer.getId(), customer);
        return customer;
    }

    @Override
    public boolean delete(Customer customer) {
        return customerMap.remove(customer.getId()) != null;
    }

    @Override
    public void deleteAll(List<Customer> customers) {
        for (Customer customer : customers) {
            delete(customer);
        }
    }

    @Override
    public void saveAll(List<Customer> customers) {
        for (Customer customer : customers) {
            save(customer);
        }
    }

    public List<Customer> findAll() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public boolean deleteById(long id) {
        return customerMap.remove(id) != null;
    }

    @Override
    public Customer getOne(long id) {
        return customerMap.get(id);
    }
}