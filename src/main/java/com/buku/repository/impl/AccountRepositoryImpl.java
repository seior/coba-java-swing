package com.buku.repository.impl;

import com.buku.entity.Account;
import com.buku.repository.AccountRepository;
import com.buku.utils.CheckError;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryImpl implements AccountRepository {

    Connection connection;

    public AccountRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Account save(Account account) {
        String SQL = "insert into account (username, password) values (?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, account.getUsername());
            statement.setString(2, account.getPassword());

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                account.setId(generatedKeys.getInt(1));
            }

            statement.close();
            generatedKeys.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return account;
    }

    @Override
    public Account getByUsername(String username) {
        String SQL = "select * from account where username = ?";

        Account account = new Account();
        try {
            PreparedStatement statement = connection.prepareStatement(SQL);

            statement.setString(1, username);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                account.setId(result.getInt(1));
                account.setUsername(result.getString(2));
                account.setPassword(result.getString(3));
            }

            return account;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Account[] getAll() {
        String SQL = "select * from account";

        List<Account> accounts = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(SQL);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Account account = new Account();

                account.setId(result.getInt(1));
                account.setUsername(result.getString(2));
                account.setPassword(result.getString(3));

                accounts.add(account);
            }

            return listAccountToArray(accounts);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void removeAll() {
        CheckError.env();

        String SQL = "delete from account";

        try {
            PreparedStatement statement = connection.prepareStatement(SQL);

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private Account[] listAccountToArray(List<Account> accounts) {
        Account[] newAccounts = new Account[accounts.size()];

        for (int i = 0; i < accounts.size(); i++) {
            newAccounts[i] = accounts.get(i);
        }

        return newAccounts;
    }

}
