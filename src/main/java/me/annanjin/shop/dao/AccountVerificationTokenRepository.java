package me.annanjin.shop.dao;

import me.annanjin.shop.entity.AccountEntity;
import me.annanjin.shop.entity.AccountVerificationTokenEntity;

public interface AccountVerificationTokenRepository extends DAOInterface<Integer, AccountVerificationTokenEntity> {
    AccountVerificationTokenEntity getByToken(String token);

    AccountVerificationTokenEntity getByAccount(AccountEntity accountEntity);
}
