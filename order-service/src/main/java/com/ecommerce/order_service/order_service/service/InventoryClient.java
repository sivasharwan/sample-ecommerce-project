package com.ecommerce.order_service.order_service.service;

import com.ecommerce.order_service.order_service.model.Inventory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory-service")
public interface InventoryClient {

    @GetMapping("/inventories/{productId}")
    Inventory getInventoryByProductId(String productid);

    @PutMapping("/inventories/{productId}")
    Inventory updateInventory(@PathVariable String productId,@RequestParam int quantity);
}
