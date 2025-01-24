package com.productservice.productservice.service;

import com.productservice.productservice.model.Inventory;
import com.productservice.productservice.model.Product;
import com.productservice.productservice.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {

    private static Logger logger= LoggerFactory.getLogger(ProductService.class);

    @Autowired
    InventoryClient inventoryClient;
    @Autowired
    ProductRepository productRepository;


    public List<Product> getAllProducts(){
        logger.info("getting all products list..");
        return productRepository.findAll();
    }

    public Product getProductById(String id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product addProduct(Product product, int initialQuantity) {
        if(product==null){
            throw new RuntimeException("Product cannot be null");
        }
        if(initialQuantity<0){
            throw new RuntimeException("Initial Quantity cannot be negative");
        }
        if(productRepository.findById(product.getId()).isPresent()){
            throw new RuntimeException("Product already exists");
        }
        Product savedProduct= productRepository.save(product);
        Inventory inventory= new Inventory();
        inventory.setProductid(savedProduct.getId());
        inventory.setQuantity(initialQuantity);
        inventoryClient.addInventory(inventory);
        logger.info("Product successfully added: "+savedProduct);
        return savedProduct;
    }
}
