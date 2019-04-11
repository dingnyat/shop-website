package me.annanjin.shop.service.impl;

import me.annanjin.shop.dao.AccountDAO;
import me.annanjin.shop.dao.AccountVerificationTokenRepository;
import me.annanjin.shop.entity.AccountEntity;
import me.annanjin.shop.entity.AccountVerificationTokenEntity;
import me.annanjin.shop.model.Account;
import me.annanjin.shop.model.AccountVerificationToken;
import me.annanjin.shop.service.AccountService;
import me.annanjin.shop.service.ServiceAbstract;
import me.annanjin.shop.util.BeanTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl extends ServiceAbstract<Integer, Account, AccountEntity, AccountDAO> implements AccountService {

    @Autowired
    private AccountVerificationTokenRepository verificationTokenRepository;

    public AccountServiceImpl(@Autowired AccountDAO repository, @Autowired BeanTools beanTools) {
        super(repository, beanTools);
    }

    @Override
    public void deleteByUsername(String username) {
        repository.delete(repository.getByUsername(username));
    }

    @Override
    public Account getByUsername(String username) {
        AccountEntity accountEntity = repository.getByUsername(username);
        if (accountEntity == null) return null;
        return beanTools.map(accountEntity, new Account());
    }

    @Override
    public boolean isEmailExisted(String email) {
        return repository.getByEmail(email) != null;
    }

    @Override
    public boolean isPhoneNumberExisted(String phoneNumber) {
        return repository.getByPhoneNumber(phoneNumber) != null;
    }

    @Override
    public void createAccountVerificationToken(AccountVerificationToken verificationToken) {
        verificationTokenRepository.create(this.beanTools.map(verificationToken, new AccountVerificationTokenEntity()));
    }

    @Override
    public AccountVerificationToken getVerificationToken(String token) {
        AccountVerificationTokenEntity verificationTokenEntity = verificationTokenRepository.getByToken(token);
        if (verificationTokenEntity == null) return null;
        return beanTools.map(verificationTokenEntity, new AccountVerificationToken());
    }
}
