package me.annanjin.shop.service.impl;

import me.annanjin.shop.dao.AccountDAO;
import me.annanjin.shop.entity.AccountEntity;
import me.annanjin.shop.model.Account;
import me.annanjin.shop.service.AccountService;
import me.annanjin.shop.utils.BeanTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BeanTools beanTools;

    @Override
    public Integer add(Account account) {
        AccountEntity accountEntity = beanTools.convert(account, new AccountEntity());
        accountEntity.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountDAO.add(accountEntity);
    }

    @Override
    public void update(Account account) {
        AccountEntity accountEntity = accountDAO.getByUsername(account.getUsername());
        beanTools.convert(account, accountEntity);
        accountEntity.setPassword(passwordEncoder.encode(account.getPassword()));
        accountDAO.update(accountEntity);
    }

    @Override
    public void remove(Integer id) {
        accountDAO.remove(accountDAO.getById(id));
    }

    @Override
    public Account getById(Integer id) {
        AccountEntity accountEntity = accountDAO.getById(id);
        if (accountEntity == null) return null;
        return beanTools.convert(accountEntity, new Account());
    }

    @Override
    public void delete(String username) {
        accountDAO.remove(accountDAO.getByUsername(username));
    }

    @Override
    public Account getByUsername(String username) {
        AccountEntity accountEntity = accountDAO.getByUsername(username);
        if (accountEntity == null) return null;
        return beanTools.convert(accountEntity, new Account());
    }

    @Override
    public List<Account> searchByName(String name) {
        List<AccountEntity> accountEntities = accountDAO.searchByName(name);
        return accountEntities.stream()
                .map(accountEntity -> beanTools.convert(accountEntity, new Account()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Account> getAll() {
        List<AccountEntity> accountEntities = accountDAO.getAll();
        return accountEntities.stream()
                .map(accountEntity -> beanTools.convert(accountEntity, new Account()))
                .collect(Collectors.toList());
    }
}
