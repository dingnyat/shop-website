package me.annanjin.shop.model;

import me.annanjin.shop.util.validation.PasswordMatcher;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@PasswordMatcher
public class RegisterForm {
    @NotNull(message = "Username must not be empty")
    @NotEmpty(message = "Username must not be empty")
    @Pattern(regexp = "^(?=.{6,25}$)([a-zA-Z0-9]+([._]?[a-zA-Z0-9])*)$", message = "Invalid username")
    private String username;
    @NotNull(message = "Password must not be empty")
    @NotEmpty(message = "Password must not be empty")
    private String password;
    @NotNull(message = "Password must not be empty")
    @NotEmpty(message = "Password must not be empty")
    private String confirmPassword;
    @NotNull(message = "Name must not be empty")
    @NotEmpty(message = "Name must not be empty")
    private String name;
    @NotNull(message = "Phone number must not be empty")
    @NotEmpty(message = "Phone number must not be empty")
    private String phone;
    @NotNull(message = "Email must not be empty")
    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Email filed must me a well-formed email address")
    private String email;
    @NotNull(message = "Address must not be empty")
    @NotEmpty(message = "Address must not be empty")
    private String address;

    public RegisterForm() {
    }

    public RegisterForm(String username, String password, String confirmPassword, String name, String phone, String email, String address) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
