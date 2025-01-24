package com.ecommerce.inventory_service.inventory_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Inventory {
    @Id
    private String productid;
    private int quantity;

    public Inventory() {
    }

    public String getProductid() {
        return productid;
    }

    public void setId(String productid) {
        this.productid = productid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Inventory(String productid, int quantity) {
        this.productid = productid;
        this.quantity = quantity;
    }
}
