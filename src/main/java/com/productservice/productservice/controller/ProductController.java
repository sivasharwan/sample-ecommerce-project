package com.productservice.productservice.controller;


import com.productservice.productservice.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.productservice.productservice.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private static final Logger Log= LoggerFactory.getLogger(ProductController.class);
    @Autowired
    ProductService productService;
    @GetMapping
    public List<Product> getallProducts(){

        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable String id){
        return productService.getProductById(id);
    }

    @PostMapping("/addproduct/{initialQuantity}")
    public Product addProduct(@RequestBody Product product,@PathVariable int initialQuantity){
        Log.info("Adding product: "+product + "with initial quantity: "+initialQuantity);
        return productService.addProduct(product,initialQuantity);
    }

}
