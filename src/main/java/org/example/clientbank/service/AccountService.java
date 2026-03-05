package org.example.clientbank.service;

import org.example.clientbank.model.Account;
import org.example.clientbank.repositiry.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccountInfo(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    public List<Account> getAllAccountsInfo() {
        return accountRepository.findAll();
    }

    public Boolean rechargeAccount(String number, Double amount) {
        Account account = accountRepository.findByNumber(number).orElse(null);

        if (account == null) {
            return false;
        }

        account.setBalance(amount);
        accountRepository.save(account);
        return true;
    }

    public Boolean withdrawal(String number, double amount) {
        Account account = accountRepository.findByNumber(number).orElse(null);

        if (account == null || account.getBalance() < amount) {
            return false;
        }

        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
        return true;
    }

    public Boolean transferMoney(String numberAccountFrom, String numberAccountTo, double amount) {
        Account fromAccount = accountRepository.findByNumber(numberAccountFrom).orElse(null);
        Account toAccount = accountRepository.findByNumber(numberAccountTo).orElse(null);
        if (fromAccount == null || toAccount == null || fromAccount.getBalance() < amount) {
            return false;
        }
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
        return true;
    }
}
