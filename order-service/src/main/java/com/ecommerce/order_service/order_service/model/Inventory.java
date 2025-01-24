package com.ecommerce.order_service.order_service.model;

public class Inventory {
    String productid;
    private int quantity;

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

    public Inventory(String productid, int quantity) {
        this.productid = productid;
        this.quantity = quantity;
    }
}
