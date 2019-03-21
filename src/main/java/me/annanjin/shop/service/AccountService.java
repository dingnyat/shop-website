package me.annanjin.shop.service;

import me.annanjin.shop.model.Account;

import java.util.List;

public interface AccountService extends ServiceInterface<Integer, Account> {
    void delete(String username);

    Account getByUsername(String username);

    List<Account> searchByName(String name);
}
