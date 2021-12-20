package com.buku.repository;

import com.buku.entity.Account;

public interface AccountRepository {

    public Account save(Account account);

    public Account getByUsername(String username);

    public Account[] getAll();

    public void removeAll();

}
