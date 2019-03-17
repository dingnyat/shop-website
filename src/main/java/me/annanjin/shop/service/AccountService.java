package me.annanjin.shop.service;

import me.annanjin.shop.model.Account;

import java.util.List;

public interface AccountService {
    int add(Account account);

    void edit(Account account);

    void delete(int id);

    void delete(String username);

    Account getByUsername(String username);

    Account getById(int id);

    List<Account> searchByName(String name);

    List<Account> getAll();

    Account login(Account account);
}
