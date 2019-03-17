package me.annanjin.shop.dao.impl;

import me.annanjin.shop.dao.AccountRoleDAO;
import me.annanjin.shop.dao.DAOAbstract;
import me.annanjin.shop.entity.AccountEntity;
import me.annanjin.shop.entity.AccountRoleEntity;
import me.annanjin.shop.entity.RoleEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class AccountRoleDAOImpl extends DAOAbstract<Integer, AccountRoleEntity> implements AccountRoleDAO {
    @Override
    public List<RoleEntity> getByAccountId(Integer id) {
        return this.entityManager
                .createQuery("SELECT a.roleEntity FROM AccountRoleEntity a WHERE a.accountEntity.id = :id", RoleEntity.class)
                .setParameter("id", id).getResultList();
    }

    @Override
    public List<AccountEntity> getByRoleId(Integer id) {
        return this.entityManager
                .createQuery("SELECT a.accountEntity FROM AccountRoleEntity a WHERE a.roleEntity.id = :id", AccountEntity.class)
                .setParameter("id", id).getResultList();
    }
}