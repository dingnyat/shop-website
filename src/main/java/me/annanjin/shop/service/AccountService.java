package me.annanjin.shop.service;

import me.annanjin.shop.model.Account;
import me.annanjin.shop.model.AccountVerificationToken;

public interface AccountService extends ServiceInterface<Integer, Account> {
    void deleteByUsername(String username);

    Account getByUsername(String username);

    boolean isEmailExisted(String email);

    boolean isPhoneNumberExisted(String phoneNumber);

    void createAccountVerificationToken(AccountVerificationToken verificationToken);

    AccountVerificationToken getVerificationToken(String token);
}
