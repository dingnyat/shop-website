package me.annanjin.shop.dao.impl;

import me.annanjin.shop.dao.AccountVerificationTokenRepository;
import me.annanjin.shop.dao.DAOAbstract;
import me.annanjin.shop.entity.AccountEntity;
import me.annanjin.shop.entity.AccountVerificationTokenEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;

@Repository
@Transactional
public class AccountVerificationTokenRepositoryImpl extends DAOAbstract<Integer, AccountVerificationTokenEntity> implements AccountVerificationTokenRepository {
    @Override
    public AccountVerificationTokenEntity getByToken(String token) {
        try {
            return this.entityManager
                    .createQuery("SELECT a FROM AccountVerificationTokenEntity a WHERE a.token = :token", AccountVerificationTokenEntity.class)
                    .setParameter("token", token).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public AccountVerificationTokenEntity getByAccount(AccountEntity accountEntity) {
        try {
            return this.entityManager
                    .createQuery("SELECT a FROM AccountVerificationTokenEntity a WHERE a.account = :account", AccountVerificationTokenEntity.class)
                    .setParameter("account", accountEntity).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
