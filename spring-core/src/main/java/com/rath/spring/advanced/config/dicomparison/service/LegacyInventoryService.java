package com.rath.spring.advanced.config.dicomparison.service;

import com.rath.spring.advanced.config.dicomparison.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class LegacyInventoryService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    public void checkInventory(String productId) {
        if (orderRepository != null) {
            orderRepository.findById("inventory-check-" + productId);
        }
    }
}
