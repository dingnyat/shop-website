package me.annanjin.shop.controller.web;

import me.annanjin.shop.model.Account;
import me.annanjin.shop.model.Role;
import me.annanjin.shop.service.AccountService;
import me.annanjin.shop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/logout";
    }

    @GetMapping(value = {"/dashboard", ""})
    public String dashboard() {
        return "admin/dashboard";
    }

    @GetMapping(value = "/category")
    public String category() {
        return "admin/category";
    }

    @GetMapping("/product")
    public String product() {
        return "admin/product";
    }

    @GetMapping(value = "/account")
    public String account() {
        return "admin/account";
    }

    @GetMapping(value = "/order")
    public String order(Model model) {
        List<Account> accountList = accountService.getAllRecords();
        model.addAttribute("accountList", accountList);
        return "admin/order";
    }

    @PostMapping("/account/add")
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
            account.setPassword(passwordEncoder.encode(account.getPassword()));
            accountService.create(account);
            return "Successfully!";
        } catch (Exception e) {
            return "Failed!";
        }
    }
}
