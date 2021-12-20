package com.buku.service.impl;

import com.buku.entity.Account;
import com.buku.exception.AccountNotFoundException;
import com.buku.model.LoginAccountRequest;
import com.buku.model.LogoutAccountRequest;
import com.buku.model.RegisterAccountRequest;
import com.buku.repository.AccountRepository;
import com.buku.service.AccountService;

import java.util.Objects;

public class AccountServiceImpl implements AccountService {

    AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account register(RegisterAccountRequest request) {
        Account account = new Account();
        account.setUsername(request.getUsername());
        account.setPassword(request.getPassword());

        accountRepository.save(account);

        return account;
    }

    @Override
    public boolean login(LoginAccountRequest request) throws AccountNotFoundException {
        Account account = accountRepository.getByUsername(request.getUsername());

        if (account.getUsername() != null) {
            return Objects.equals(request.getPassword(), account.getPassword());
        }

        throw new AccountNotFoundException();
    }

    @Override
    public boolean logout(LogoutAccountRequest request) {
        return false;
    }

    @Override
    public void deleteAll() {
        accountRepository.removeAll();
    }

}
