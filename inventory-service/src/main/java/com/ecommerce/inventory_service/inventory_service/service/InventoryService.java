package com.ecommerce.inventory_service.inventory_service.service;

import com.ecommerce.inventory_service.inventory_service.model.Inventory;
import com.ecommerce.inventory_service.inventory_service.repository.InventoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    private static final Logger log = LoggerFactory.getLogger(InventoryService.class);

    public List<Inventory> getAllInventories() {
        log.info("Calling getAllInventories method..");
        return inventoryRepository.findAll();
    }

    public Inventory getInventoryByProductId(String productId) {
        log.info("Calling getInventoryByProductId for product: "+productId);
        return inventoryRepository.findById(productId).orElse(null);
    }

    public Inventory updateInventory(String productId, int quantity) {
        Inventory inventory = inventoryRepository.findById(productId).orElse(null);
        if(inventory!=null){
            log.info("Updating inventory for product: "+ productId+ "with quantity: "+ quantity);
            inventory.setQuantity(inventory.getQuantity()+quantity);
            log.info("Updated inventory for product: "+productId + "with quantity: "+inventory.getQuantity());
            return inventoryRepository.save(inventory);
        }
        else{
            throw new RuntimeException("Product not found");
        }
    }

    public Inventory addInventory(Inventory inventory) {
        log.info("Checking conditions to add inventory...");
        if(inventory.getProductid()==null || inventory.getProductid().isEmpty()){
            throw new RuntimeException("Product id cannot be null or empty");
        }
        if(inventory.getQuantity()<0){
            throw new RuntimeException("Quantity cannot be negative");
        }
        if(inventoryRepository.findById(inventory.getProductid()).isPresent()){
            throw new RuntimeException("Product already exists");
        }
        log.info("Adding inventory: "+ inventory.getProductid() + "with quantity: "+inventory.getQuantity());
        return inventoryRepository.save(inventory);
    }
}
