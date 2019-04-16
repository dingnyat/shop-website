package me.annanjin.shop.controller.api;

import com.google.gson.Gson;
import me.annanjin.shop.model.Cart;
import me.annanjin.shop.service.AccountService;
import me.annanjin.shop.service.CartItemService;
import me.annanjin.shop.service.CartService;
import me.annanjin.shop.util.datatable.DataTableRequest;
import me.annanjin.shop.util.datatable.TableRecordResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class OrderAPIController {
    @Autowired
    private CartService cartService;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private AccountService accountService;

    @PostMapping("/api/admin/order/add")
    public ResponseEntity<?> addOrder(@RequestBody Cart cart) {
        try {
            cart.setBuyer(accountService.getById(cart.getBuyer().getId()));
            cart.setDateBuy(new Date());
            cartService.create(cart);
            return new ResponseEntity<>("Successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/admin/order/table_data")
    public TableRecordResponseData<Cart> carts(@RequestBody DataTableRequest datatableRequest) {
        List<Cart> carts = cartService.getTableData(datatableRequest, "id", "buyer.name", "dateBuy");
        TableRecordResponseData<Cart> responseData = new TableRecordResponseData<>(carts);
        responseData.setDraw(datatableRequest.getDraw());
        responseData.setRecordsTotal(cartService.getTheNumberOfAllRecords());
        responseData.setRecordsFiltered(cartService.getTheNumberOfFilteredRecords(datatableRequest, "id", "buyer.name", "dateBuy"));
        return responseData;
    }

    @DeleteMapping("/api/admin/order/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id) {
        try {
            cartService.delete(id);
            return "Successfully!";
        } catch (Exception e) {
            return "Failed!";
        }
    }
}
