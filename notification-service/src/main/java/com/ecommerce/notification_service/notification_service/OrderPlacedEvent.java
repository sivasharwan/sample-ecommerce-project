package com.ecommerce.notification_service.notification_service;

public class OrderPlacedEvent {
    private String orderId;
    private String email;

    public OrderPlacedEvent() {
    }

    public OrderPlacedEvent(String orderId, String email) {
        this.orderId = orderId;
        this.email = email;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
