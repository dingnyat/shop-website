package me.annanjin.shop.dao;

import me.annanjin.shop.entity.AccountEntity;
import me.annanjin.shop.entity.AccountRoleEntity;
import me.annanjin.shop.entity.RoleEntity;

import java.util.List;

public interface AccountRoleDAO extends DAOInterface<Integer, AccountRoleEntity> {
    List<RoleEntity> getByAccountId(Integer id);

    List<AccountEntity> getByRoleId(Integer id);
}
