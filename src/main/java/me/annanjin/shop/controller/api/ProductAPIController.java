package me.annanjin.shop.controller.api;

import me.annanjin.shop.model.Product;
import me.annanjin.shop.service.ProductService;
import me.annanjin.shop.util.datatable.DataTableRequest;
import me.annanjin.shop.util.datatable.TableRecordResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductAPIController {

    @Autowired
    private ProductService productService;

    @PostMapping("/api/admin/product/add")
    public String addProduct(@RequestBody Product product) {
        try {
            productService.create(product);
            return "Successfully!";
        } catch (Exception e) {
            return "Failed!";
        }
    }

    @PutMapping("/api/admin/product/edit")
    public String editProduct(@RequestBody Product product) {
        try {
            productService.update(product);
            return "Successfully!";
        } catch (Exception e) {
            return "Failed!";
        }
    }

    @DeleteMapping("/api/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id) {
        try {
            productService.delete(id);
            return "Successfully!";
        } catch (Exception e) {
            return "Failed!";
        }
    }

    @DeleteMapping("/api/admin/product/selected-delete")
    public String deleteSelectedProducts(@RequestBody List<Integer> ids) {
        try {
            ids.forEach(id -> productService.delete(id));
            return "Successfully!";
        } catch (Exception e) {
            return "Failed!";
        }
    }

    @GetMapping("/api/admin/product/list")
    public List<Product> products() {
        return productService.getAllRecords();
    }

    @PostMapping("/api/admin/product/table_data")
    public TableRecordResponseData<Product> products(@RequestBody DataTableRequest datatableRequest) {
        List<Product> products = productService.getTableData(datatableRequest, "id", "name", "quantity", "price");
        TableRecordResponseData<Product> responseData = new TableRecordResponseData<>(products);
        responseData.setDraw(datatableRequest.getDraw());
        responseData.setRecordsTotal(productService.getTheNumberOfAllRecords());
        responseData.setRecordsFiltered(productService.getTheNumberOfFilteredRecords(datatableRequest, "id", "name", "quantity", "price"));
        return responseData;
    }

}