package com.ecommerce.order_service.order_service.repository;

import com.ecommerce.order_service.order_service.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,String> {
}
