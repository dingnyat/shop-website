package me.annanjin.shop.model;

import com.google.gson.annotations.Expose;
import org.springframework.web.multipart.MultipartFile;

public class Account {

    private int id;
    private String username;
    private String password;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String avatarURL;
    @Expose(serialize = false)
    private MultipartFile multipartFile;

    public Account() {
    }

    public Account(int id, String username, String password, String name, String address, String phone, String email, String avatarURL, MultipartFile multipartFile) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.avatarURL = avatarURL;
        this.multipartFile = multipartFile;
    }

    public Account(String username, String password, String name, String address, String phone, String email, String avatarURL, MultipartFile multipartFile) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.avatarURL = avatarURL;
        this.multipartFile = multipartFile;
    }

    public Account(String username, String password, String name, String address, String phone, String email, String avatarURL) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.avatarURL = avatarURL;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
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
}
