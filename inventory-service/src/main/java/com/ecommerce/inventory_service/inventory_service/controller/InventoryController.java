package com.ecommerce.inventory_service.inventory_service.controller;

import com.ecommerce.inventory_service.inventory_service.model.Inventory;
import com.ecommerce.inventory_service.inventory_service.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventories")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    private static final Logger log= LoggerFactory.getLogger(InventoryController.class);

    @GetMapping
    public List<Inventory> getAllInventory(){
        return inventoryService.getAllInventories();
    }

    @GetMapping("/{productId}")
    public Inventory getInventoryByProductId(@PathVariable String productId){
        log.info("Getting inventory for product: "+productId);
        return inventoryService.getInventoryByProductId(productId);
    }

    @PutMapping("/{productId}")
    public Inventory updateInventory(@PathVariable String productId, @RequestParam int quantity){
        return inventoryService.updateInventory(productId,quantity);
    }

    @PostMapping("/addinventory")
    public Inventory addInventory(@RequestBody Inventory inventory){
        log.info("Calling inventoryService to add inventory of productid: "+inventory.getProductid() +"quantity: "+inventory.getQuantity());
        return inventoryService.addInventory(inventory);
    }


}
