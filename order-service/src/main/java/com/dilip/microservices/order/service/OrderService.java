package com.dilip.microservices.order.service;

import com.dilip.microservices.order.dto.OrderRequest;
import com.dilip.microservices.order.model.Order;
import com.dilip.microservices.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    /**
     * Creates a new order based on the given OrderRequest object and saves it to the database.
     *
     * @param  order  the OrderRequest object containing the order details
     */
    public void placeOrder(OrderRequest order) {
        Order orderObj = new Order();
        orderObj.setOrderNumber(UUID.randomUUID().toString());
        orderObj.setSkuCode(order.skuCode());
        orderObj.setPrice(order.price());
        orderObj.setQuantity(order.quantity());
        orderRepository.save(orderObj);
    }
}
