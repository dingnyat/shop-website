package me.annanjin.shop.controller.web;

import me.annanjin.shop.model.*;
import me.annanjin.shop.service.AccountService;
import me.annanjin.shop.service.CategoryService;
import me.annanjin.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class ClientController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String index() {
        return "client/index";
    }

    @GetMapping("/login")
    public String login() {
        return "client/login";
    }

    @GetMapping("/verify")
    public String accountVerify(@RequestParam("token") String token, Model model) {
        AccountVerificationToken verificationToken = accountService.getVerificationToken(token);
        if (verificationToken == null) {
            model.addAttribute("error", true);
            return "client/verify";
        }
        long tokenTime = verificationToken.getTime();
        Date date = new Date();
        if (date.getTime() - tokenTime > 1000 * 60 * 30) { // 30 minutes
            model.addAttribute("error", true);
            return "client/verify";
        }
        Account account = accountService.getById(verificationToken.getAccount().getId());
        account.setEnabled(true);
        accountService.update(account);
        model.addAttribute("success", true);
        model.addAttribute("message", "Error! Token is invalid. You should log in to your account and re-send register confirmation token!");
        return "client/verify";
    }

    @GetMapping("/register-successfully")
    public String registerSuccessfully() {
        return "client/register-successfully";
    }

    @GetMapping("/category/{categoryCode}")
    public String showProductByCategory(@PathVariable("categoryCode") String code, Model model) {
        Category category = categoryService.getByCode(code);
        model.addAttribute("category", category);
        return "client/product-grid";
    }

    @PostMapping("/api/category/{categoryCode}")
    @ResponseBody
    public ResponseEntity<ProductFilterResponse> productGrid(@PathVariable("categoryCode") String code,
                                                             @RequestBody(required = false) ProductFilterRequest request) {
        if (request == null) request = new ProductFilterRequest();
        request.setCategory(categoryService.getByCode(code));
        List<Product> products = productService.getProductsByCategoryWithFilter(request);
        ProductFilterResponse response = new ProductFilterResponse();
        response.setResults(products);
        response.setRecordsFiltered((long) products.size());
        response.setRecordsTotal(productService.getRecordsTotal(request));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
