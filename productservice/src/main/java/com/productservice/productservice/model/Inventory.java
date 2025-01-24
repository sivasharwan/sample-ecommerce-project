package com.productservice.productservice.model;

public class Inventory {
    private String productid;
    private int quantity;

    public Inventory(String productid, int quantity) {
        this.productid = productid;
        this.quantity = quantity;
    }

    public Inventory() {
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
