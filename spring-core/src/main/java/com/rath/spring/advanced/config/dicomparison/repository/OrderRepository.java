package com.rath.spring.advanced.config.dicomparison.repository;

import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {
    
    public void save(String orderId) {
        System.out.println("OrderRepository: Saving order " + orderId);
    }
    
    public String findById(String orderId) {
        System.out.println("OrderRepository: Finding order " + orderId);
        return "Order-" + orderId;
    }
}

