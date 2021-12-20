package com.buku.service;

import com.buku.entity.Account;
import com.buku.exception.AccountNotFoundException;
import com.buku.model.LoginAccountRequest;
import com.buku.model.LogoutAccountRequest;
import com.buku.model.RegisterAccountRequest;

public interface AccountService {

    public Account register(RegisterAccountRequest request);

    public boolean login(LoginAccountRequest request) throws AccountNotFoundException;

    public boolean logout(LogoutAccountRequest request);

    public void deleteAll();

}
