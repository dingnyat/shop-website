package me.annanjin.shop.dao.impl;

import me.annanjin.shop.dao.AccountDAO;
import me.annanjin.shop.dao.DAOAbstract;
import me.annanjin.shop.entity.AccountEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;

@Repository
@Transactional
public class AccountDAOImpl extends DAOAbstract<Integer, AccountEntity> implements AccountDAO {

    @Override
    public AccountEntity getByUsername(String username) {
        try {
            return this.entityManager
                    .createQuery("SELECT a FROM AccountEntity a WHERE a.username = :username", AccountEntity.class)
                    .setParameter("username", username).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}