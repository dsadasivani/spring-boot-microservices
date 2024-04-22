package com.dilip.microservices.order.service;

import com.dilip.microservices.order.client.InventoryClient;
import com.dilip.microservices.order.dto.OrderRequest;
import com.dilip.microservices.order.model.Order;
import com.dilip.microservices.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    /**
     * Places an order by checking the stock availability and saving the order details in the repository.
     *
     * @param  order  the order request object containing the SKU code, price, and quantity
     * @throws RuntimeException if the stock is not available for the product with the given SKU code
     */
    public void placeOrder(OrderRequest order) {
        boolean stockAvailable = inventoryClient.isStockAvailable(order.skuCode(), order.quantity());
        if(!stockAvailable) {
            throw new RuntimeException("Insufficient stock for Product with skuCode: " + order.skuCode());
        }
        Order orderObj = new Order();
        orderObj.setOrderNumber(UUID.randomUUID().toString());
        orderObj.setSkuCode(order.skuCode());
        orderObj.setPrice(order.price());
        orderObj.setQuantity(order.quantity());
        orderRepository.save(orderObj);
        log.info("Order placed successfully. Order number: " + orderObj.getOrderNumber());
    }
}
