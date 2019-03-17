package me.annanjin.shop.dao;

import me.annanjin.shop.entity.AccountEntity;

import java.util.List;

public interface AccountDAO extends DAOInterface<Integer, AccountEntity> {
    AccountEntity getByUsername(String username);

    List<AccountEntity> searchByName(String name);
}
