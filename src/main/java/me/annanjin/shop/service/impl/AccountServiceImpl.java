package me.annanjin.shop.service.impl;

import me.annanjin.shop.dao.AccountDAO;
import me.annanjin.shop.entity.AccountEntity;
import me.annanjin.shop.model.Account;
import me.annanjin.shop.service.AccountService;
import me.annanjin.shop.service.ServiceAbstract;
import me.annanjin.shop.util.BeanTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl extends ServiceAbstract<Integer, Account, AccountEntity, AccountDAO> implements AccountService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AccountServiceImpl(@Autowired AccountDAO repository, @Autowired BeanTools beanTools) {
        super(repository, beanTools);
    }

    @Override
    public Integer create(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return super.create(account);
    }

    @Override
    public void update(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        super.update(account);
    }

    @Override
    public void delete(String username) {
        repository.delete(repository.getByUsername(username));
    }

    @Override
    public Account getByUsername(String username) {
        AccountEntity accountEntity = repository.getByUsername(username);
        if (accountEntity == null) return null;
        return beanTools.convert(accountEntity, new Account());
    }
}
