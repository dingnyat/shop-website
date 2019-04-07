package me.annanjin.shop.controller.web;

import me.annanjin.shop.model.Account;
import me.annanjin.shop.model.Role;
import me.annanjin.shop.service.AccountService;
import me.annanjin.shop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/admin/account")
    public String view() {
        return "admin/account/account";
    }

    @PostMapping("/admin/account/add")
    @ResponseBody
    public String addAccount(@ModelAttribute Account account) {
        try {
            if (account.getMultipartFile() != null && !account.getMultipartFile().isEmpty()) {
                final String UPLOAD_FOLDER = "D:\\user";
                String imageUrl = System.currentTimeMillis() + ".jpg";
                Path pathAvatar = Paths.get(UPLOAD_FOLDER + File.separator + imageUrl);
                Files.write(pathAvatar, account.getMultipartFile().getBytes());
                account.setAvatarUrl(imageUrl);
            }
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleService.getById(2));
            account.setRoles(roles);
            accountService.create(account);
            return "Successfully!";
        } catch (Exception e) {
            return "Failed!";
        }
    }
}
