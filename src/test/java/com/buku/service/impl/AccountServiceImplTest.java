package com.buku.service.impl;

import com.buku.entity.Account;
import com.buku.exception.AccountNotFoundException;
import com.buku.model.LoginAccountRequest;
import com.buku.model.RegisterAccountRequest;
import com.buku.repository.AccountRepository;
import com.buku.repository.impl.AccountRepositoryImpl;
import com.buku.service.AccountService;
import com.buku.utils.Database;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceImplTest {

    AccountService service;

    @BeforeEach
    void setUp() {
        AccountRepository repository = new AccountRepositoryImpl(Database.getConnection());

        service = new AccountServiceImpl(repository);
    }

    @BeforeAll
    static void beforeAll() {
        AccountRepository repository = new AccountRepositoryImpl(Database.getConnection());

        AccountService service = new AccountServiceImpl(repository);

        service.deleteAll();
    }

    @Test
    void register() {
        RegisterAccountRequest accountRequest = new RegisterAccountRequest();
        accountRequest.setUsername("raden");
        accountRequest.setPassword("123");

        Account account = service.register(accountRequest);


        assertEquals(account.getUsername(), accountRequest.getUsername());
    }

    @Test
    void loginSuccess() throws AccountNotFoundException {
        RegisterAccountRequest accountRequest = new RegisterAccountRequest();
        accountRequest.setUsername("raden");
        accountRequest.setPassword("123");

        Account account = service.register(accountRequest);

        LoginAccountRequest login = new LoginAccountRequest();
        login.setUsername("raden");
        login.setPassword("123");

        boolean status = service.login(login);

        assertTrue(status);
    }

    @Test
    void loginFail() throws AccountNotFoundException {
        RegisterAccountRequest accountRequest = new RegisterAccountRequest();
        accountRequest.setUsername("raden");
        accountRequest.setPassword("123");

        Account account = service.register(accountRequest);

        LoginAccountRequest login = new LoginAccountRequest();
        login.setUsername("raden");
        login.setPassword("asd");

        boolean status = service.login(login);

        assertFalse(status);
    }
}