package me.annanjin.shop.model;

public class AccountVerificationToken {

    private Long id;
    private String token;
    private Account account;
    private Long time;

    public AccountVerificationToken() {
    }

    public AccountVerificationToken(Long id, String token, Account account, Long time) {
        this.id = id;
        this.token = token;
        this.account = account;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
