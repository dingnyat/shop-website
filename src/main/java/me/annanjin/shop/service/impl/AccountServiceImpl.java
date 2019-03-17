package me.annanjin.shop.service.impl;

import me.annanjin.shop.dao.AccountDAO;
import me.annanjin.shop.entity.AccountEntity;
import me.annanjin.shop.model.Account;
import me.annanjin.shop.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public int add(Account account) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setUsername(account.getUsername());
        accountEntity.setPassword(passwordEncoder.encode(account.getPassword()));
        accountEntity.setName(account.getName());
        accountEntity.setAddress(account.getAddress());
        accountEntity.setPhone(account.getPhone());
        accountEntity.setEmail(account.getEmail());
        accountEntity.setAvatarURL(account.getAvatarURL());
        return accountDAO.add(accountEntity);
    }

    @Override
    public Account getByUsername(String username) {
        AccountEntity accountEntity = accountDAO.getByUsername(username);
        if (accountEntity == null) return null;
        Account account = new Account();
        account.setId(accountEntity.getId());
        account.setUsername(accountEntity.getUsername());
        account.setPassword(accountEntity.getPassword());
        account.setName(accountEntity.getName());
        account.setAddress(accountEntity.getAddress());
        account.setPhone(accountEntity.getPhone());
        account.setEmail(accountEntity.getEmail());
        account.setAvatarURL(accountEntity.getAvatarURL());
        return account;
    }

    @Override
    public void edit(Account account) {
        AccountEntity accountEntity = accountDAO.getByUsername(account.getUsername());
        accountEntity.setUsername(account.getUsername());
        accountEntity.setPassword(passwordEncoder.encode(account.getPassword()));
        accountEntity.setName(account.getName());
        accountEntity.setAddress(account.getAddress());
        accountEntity.setPhone(account.getPhone());
        accountEntity.setEmail(account.getEmail());
        accountEntity.setAvatarURL(account.getAvatarURL());
        accountDAO.update(accountEntity);
    }

    @Override
    public void delete(int id) {
        accountDAO.remove(accountDAO.getById(id));
    }

    @Override
    public void delete(String username) {
        accountDAO.remove(accountDAO.getByUsername(username));
    }

    @Override
    public Account getById(int id) {
        AccountEntity accountEntity = accountDAO.getById(id);
        if (accountEntity == null) return null;
        Account account = new Account();
        account.setId(accountEntity.getId());
        account.setUsername(accountEntity.getUsername());
        account.setPassword(accountEntity.getPassword());
        account.setName(accountEntity.getName());
        account.setAddress(accountEntity.getAddress());
        account.setPhone(accountEntity.getPhone());
        account.setEmail(accountEntity.getEmail());
        account.setAvatarURL(accountEntity.getAvatarURL());
        return account;
    }

    @Override
    public List<Account> searchByName(String name) {
        List<AccountEntity> accountEntities = accountDAO.searchByName(name);
        List<Account> accountList = new ArrayList<Account>();
        for (AccountEntity accountEntity : accountEntities) {
            Account account = new Account();
            account.setId(accountEntity.getId());
            account.setUsername(accountEntity.getUsername());
            account.setPassword(accountEntity.getPassword());
            account.setName(accountEntity.getName());
            account.setAddress(accountEntity.getAddress());
            account.setPhone(accountEntity.getPhone());
            account.setEmail(accountEntity.getEmail());
            account.setAvatarURL(accountEntity.getAvatarURL());
            accountList.add(account);
        }
        return accountList;
    }

    @Override
    public List<Account> getAll() {
        List<AccountEntity> accountEntities = accountDAO.getAll();
        List<Account> accountList = new ArrayList<Account>();
        for (AccountEntity accountEntity : accountEntities) {
            Account account = new Account();
            account.setId(accountEntity.getId());
            account.setUsername(accountEntity.getUsername());
            account.setPassword(accountEntity.getPassword());
            account.setName(accountEntity.getName());
            account.setAddress(accountEntity.getAddress());
            account.setPhone(accountEntity.getPhone());
            account.setEmail(accountEntity.getEmail());
            account.setAvatarURL(accountEntity.getAvatarURL());
            accountList.add(account);
        }
        return accountList;
    }

    @Override
    public Account login(Account account) {
        AccountEntity accountEntity = accountDAO.getByUsername(account.getUsername());
        if (accountEntity != null && account.getPassword().equals(accountEntity.getPassword())) {
            account.setId(accountEntity.getId());
            account.setUsername(accountEntity.getUsername());
            account.setPassword(accountEntity.getPassword());
            account.setName(accountEntity.getName());
            account.setAddress(accountEntity.getAddress());
            account.setPhone(accountEntity.getPhone());
            account.setEmail(accountEntity.getEmail());
            account.setAvatarURL(accountEntity.getAvatarURL());
            return account;
        }
        return null;
    }
}
