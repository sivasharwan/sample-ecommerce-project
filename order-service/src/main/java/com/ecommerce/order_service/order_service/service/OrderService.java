package com.ecommerce.order_service.order_service.service;

import com.ecommerce.order_service.order_service.event.OrderPlacedEvent;
import com.ecommerce.order_service.order_service.model.Inventory;
import com.ecommerce.order_service.order_service.model.Order;
import com.ecommerce.order_service.order_service.repository.OrderRepository;
import org.apache.coyote.http11.filters.SavedRequestInputFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderService {

    private static final Logger log= LoggerFactory.getLogger(OrderService.class);

    @Autowired
    InventoryClient inventoryClient;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public List<Order> getallOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(String id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order addOrder(Order order) {
        String productid= order.getProductid();
        log.info("Checking product: "+ productid);
        Inventory inventory =inventoryClient.getInventoryByProductId(productid);
        if(inventory!=null){
            log.info("Inventory product: "+inventory.getProductid()+" quantity: "+inventory.getQuantity());
            if(inventory.getQuantity()>=order.getQuantity()){
                log.info("Adding order: "+order);
                Order placedorder=orderRepository.save(order);
                inventoryClient.updateInventory(productid,-order.getQuantity());
                log.info("Order placed successfully");
                //Send the message to the kafka topic
                //order number and Email
                log.info("Sending order placed event to kafka topic :{}",placedorder.getId());
                OrderPlacedEvent orderPlacedEvent =new OrderPlacedEvent(placedorder.getId(),placedorder.getEmail());
                kafkaTemplate.send("order-placed",orderPlacedEvent);
                log.info("Order placed event sent successfully with order id :{} and user email :{}",orderPlacedEvent.getOrderId(),orderPlacedEvent.getEmail());
                return placedorder;
            }
            else{
                throw new RuntimeException("Product out of stock");
            }
        }
        else{
            throw new RuntimeException("Product not found in inventory");
        }
    }
}
