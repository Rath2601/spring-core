package com.rath.spring.advanced.config.dicomparison.controller;

import com.rath.spring.advanced.config.dicomparison.service.LegacyInventoryService;
import com.rath.spring.advanced.config.dicomparison.service.NotificationService;
import com.rath.spring.advanced.config.dicomparison.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class OrderController {
    
    private final OrderService orderService;
    private NotificationService notificationService;
    
    @Autowired(required = false)
    private LegacyInventoryService legacyInventoryService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired(required = false)
    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
    
    public void processOrder(String orderId, double amount) {
        orderService.processOrder(orderId, amount);
        if (notificationService != null) {
            notificationService.sendNotification("customer@example.com", 
                "Your order " + orderId + " has been processed");
        }
        if (legacyInventoryService != null) {
            legacyInventoryService.checkInventory("PROD-123");
        }
    }
    
    public String getPaymentProvider() {
        return orderService.getPaymentProvider();
    }
}
