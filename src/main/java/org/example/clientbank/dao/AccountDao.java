package org.example.clientbank.dao;

import org.example.clientbank.model.Account;

import java.util.*;

public class AccountDao implements Dao<Account> {
    private final Map<Long, Account> accountMap = new HashMap<>();
    private long nextAccountId = 1;

    @Override
    public Account save(Account account) {
        if (account.getId() == null) {
            account.setId(nextAccountId++);
        }
        accountMap.put(account.getId(), account);
        return account;
    }

    @Override
    public boolean delete(Account account) {
        return accountMap.remove(account.getId()) != null;
    }

    @Override
    public void deleteAll(List<Account> accounts) {
        for (Account account : accounts) {
            delete(account);
        }
    }

    @Override
    public void saveAll(List<Account> accounts) {
        for (Account account : accounts) {
            save(account);
        }
    }

    @Override
    public List<Account> findAll() {
        return new ArrayList<>(accountMap.values());    }

    @Override
    public boolean deleteById(long id) {
        return accountMap.remove(id) != null;
    }

    @Override
    public Account getOne(long id) {
        return accountMap.get(id);
    }
}