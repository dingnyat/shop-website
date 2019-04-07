package me.annanjin.shop.controller.api;

import me.annanjin.shop.model.Role;
import me.annanjin.shop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleAPIController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/api/role/list")
    public List<Role> roles() {
        return roleService.getAllRecords();
    }
}
