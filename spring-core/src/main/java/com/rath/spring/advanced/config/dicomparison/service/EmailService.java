package com.rath.spring.advanced.config.dicomparison.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

    @Autowired
    private NotificationService notificationService;

//    @Autowired(required = false)
//    public void setNotificationService(NotificationService notificationService) {
//        this.notificationService = notificationService;
//    }
    
    public void sendEmail(String to, String subject, String body) {
        System.out.println("EmailService: Sending email to " + to);
        System.out.println("  Subject: " + subject);
        System.out.println("  Body: " + body);
        if (notificationService !=null){
            System.out.println("NotificationService value is "+notificationService);
        }
    }
}

