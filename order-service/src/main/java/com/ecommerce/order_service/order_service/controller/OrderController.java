package com.ecommerce.order_service.order_service.controller;

import com.ecommerce.order_service.order_service.model.Order;
import com.ecommerce.order_service.order_service.service.OrderService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders(){
        return orderService.getallOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable String id){
        return orderService.getOrderById(id);
    }

    @PostMapping
    public Order addOrder(@RequestBody Order order){
        return orderService.addOrder(order);
    }
}
