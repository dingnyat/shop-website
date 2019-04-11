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

    @Override
    public AccountEntity getByEmail(String email) {
        try {
            return this.entityManager
                    .createQuery("SELECT a FROM AccountEntity a WHERE a.email = :email", AccountEntity.class)
                    .setParameter("email", email).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public AccountEntity getByPhoneNumber(String phoneNumber) {
        try {
            return this.entityManager
                    .createQuery("SELECT a FROM AccountEntity a WHERE a.phone = :phone", AccountEntity.class)
                    .setParameter("phone", phoneNumber).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}