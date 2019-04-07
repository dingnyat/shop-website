package me.annanjin.shop.controller.api;

import me.annanjin.shop.model.Account;
import me.annanjin.shop.model.Role;
import me.annanjin.shop.service.AccountService;
import me.annanjin.shop.service.RoleService;
import me.annanjin.shop.util.datatable.DataTableRequest;
import me.annanjin.shop.util.datatable.TableRecordResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
public class AccountAPIController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private RoleService roleService;

    @PostMapping("/api/admin/account/add")
    public String addAccount(@RequestBody Account account) {
        try {
            accountService.create(account);
            return "Successfully!";
        } catch (Exception e) {
            return "Failed!";
        }
    }

    @PutMapping("/api/admin/account/edit")
    public String editAccount(@RequestBody Account account) {
        try {
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleService.getById(2));
            account.setRoles(roles);
            accountService.update(account);
            return "Successfully!";
        } catch (Exception e) {
            return "Failed!";
        }
    }

    @DeleteMapping("/api/admin/account/delete/{id}")
    public String deleteAccounts(@PathVariable("id") Integer id) {
        try {
            accountService.delete(id);
            return "Successfully!";
        } catch (Exception e) {
            return "Failed!";
        }
    }

    @DeleteMapping("/api/admin/account/selected-delete")
    public String deleteSelectedAccounts(@RequestBody List<Integer> ids) {
        try {
            ids.forEach(id -> accountService.delete(id));
            return "Successfully!";
        } catch (Exception e) {
            return "Failed!";
        }
    }

    @GetMapping("/api/admin/account/list")
    public List<Account> accounts() {
        return accountService.getAllRecords();
    }

    @PostMapping("/api/admin/account/table_data")
    public TableRecordResponseData<Account> accounts(@RequestBody DataTableRequest datatableRequest) {
        List<Account> accounts = accountService.getTableData(datatableRequest, "id", "username", "name", "address", "phone", "email");
        TableRecordResponseData<Account> responseData = new TableRecordResponseData<>(accounts);
        responseData.setDraw(datatableRequest.getDraw());
        responseData.setRecordsTotal(accountService.getTheNumberOfAllRecords());
        responseData.setRecordsFiltered(accountService.getTheNumberOfFilteredRecords(datatableRequest, "id", "username", "name", "address", "phone", "email"));
        return responseData;
    }

}
