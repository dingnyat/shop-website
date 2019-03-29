package me.annanjin.shop.dao;

import me.annanjin.shop.entity.AccountEntity;

public interface AccountDAO extends DAOInterface<Integer, AccountEntity> {
    AccountEntity getByUsername(String username);
}
