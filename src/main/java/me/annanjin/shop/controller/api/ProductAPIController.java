package me.annanjin.shop.controller.api;

import me.annanjin.shop.model.Product;
import me.annanjin.shop.service.ProductService;
import me.annanjin.shop.util.datatable.DataTableRequest;
import me.annanjin.shop.util.datatable.TableRecordResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/api/admin/product/list")
    public List<Product> products() {
        return productService.getAllRecords();
    }

    @PostMapping("/api/admin/product/table_data")
    public TableRecordResponseData<Product> products(@RequestBody DataTableRequest datatableRequest) {
        List<Product> products = productService.getTableData(datatableRequest);
        TableRecordResponseData<Product> responseData = new TableRecordResponseData<>(products);
        responseData.setDraw(datatableRequest.getDraw());
        responseData.setRecordsTotal(productService.getTheNumberOfAllRecords());
        responseData.setRecordsFiltered(productService.getTheNumberOfFilteredRecords(datatableRequest));
        return responseData;
    }

}