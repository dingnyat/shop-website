package me.annanjin.shop.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "account")
public class AccountEntity {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", nullable = false, unique = true, length = 128)
    private String username;

    @Column(name = "password", nullable = false, length = 512)
    private String password;

    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @Column(name = "address", nullable = false, length = 1024)
    private String address;

    @Column(name = "phone", nullable = false, unique = true, length = 64)
    private String phone;

    @Column(name = "email", unique = true, length = 128)
    private String email;

    @Column(name = "avatar_url", length = 1024)
    private String avatarURL;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "account_role",
            joinColumns = {@JoinColumn(name = "account_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "role_id", nullable = false)},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"account_id", "role_id"})}
    )
    private Set<RoleEntity> roles;

    public AccountEntity() {
    }

    public AccountEntity(String username, String password, String name, String address, String phone, String email, String avatarURL, Set<RoleEntity> roles) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.avatarURL = avatarURL;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }
}
