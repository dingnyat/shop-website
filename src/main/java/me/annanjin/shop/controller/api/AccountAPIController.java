package me.annanjin.shop.controller.api;

import me.annanjin.shop.model.Account;
import me.annanjin.shop.model.AccountVerificationToken;
import me.annanjin.shop.model.RegisterForm;
import me.annanjin.shop.model.Role;
import me.annanjin.shop.service.AccountService;
import me.annanjin.shop.service.EmailService;
import me.annanjin.shop.service.RoleService;
import me.annanjin.shop.util.datatable.DataTableRequest;
import me.annanjin.shop.util.datatable.TableRecordResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@RestController
public class AccountAPIController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @PostMapping("/api/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterForm registerForm, BindingResult result, HttpServletRequest request) {
        // validate
        if (result.hasErrors()) {
            return new ResponseEntity<>(Objects.requireNonNull(result.getAllErrors().get(0).getDefaultMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (accountService.isEmailExisted(registerForm.getEmail())) {
            return new ResponseEntity<>("Email has been used by another account!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (accountService.isPhoneNumberExisted(registerForm.getPhone())) {
            return new ResponseEntity<>("Phone number has been used by another account!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // save
        try {
            Account account = new Account();
            account.setUsername(registerForm.getUsername());
            account.setPassword(passwordEncoder.encode(registerForm.getPassword()));
            account.setName(registerForm.getName());
            account.setAddress(registerForm.getAddress());
            account.setEmail(registerForm.getEmail());
            account.setPhone(registerForm.getPhone());
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.getById(2));
            account.setRoles(roles);
            account.setEnabled(false);
            account.setId(accountService.create(account));

            AccountVerificationToken verificationToken = new AccountVerificationToken();
            verificationToken.setTime((new Date()).getTime());
            verificationToken.setAccount(account);
            verificationToken.setToken(UUID.randomUUID().toString());
            accountService.createAccountVerificationToken(verificationToken);
            System.out.println("http://localhost:8080/verify?token=" + verificationToken.getToken());
            /*String subject = "Register Confirmation - Annanjin Store";
            String confirmUrl = "http://localhost:8080/verify?token=" + verificationToken.getToken();
            String message = "Register Confirmation Link: " + confirmUrl;
            emailService.sendSimpleMessage(account.getEmail(), subject, message);*/
            return new ResponseEntity<>("Successfully", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.toString());
            return new ResponseEntity<>("An error occured! Please try again!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
