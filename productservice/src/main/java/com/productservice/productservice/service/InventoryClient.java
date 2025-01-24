package com.productservice.productservice.service;

import com.productservice.productservice.model.Inventory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "inventory-service")
public interface InventoryClient {

    @PostMapping("/inventories/addinventory")
    Inventory addInventory(@RequestBody Inventory inventory);
}
