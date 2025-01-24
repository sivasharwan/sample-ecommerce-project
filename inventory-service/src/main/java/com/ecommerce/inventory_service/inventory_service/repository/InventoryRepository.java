package com.ecommerce.inventory_service.inventory_service.repository;

import com.ecommerce.inventory_service.inventory_service.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory,String> {
}
