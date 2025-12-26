package com.rath.spring.advanced.config.dicomparison.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private EmailService emailService;
    
//    @Autowired(required = false)
//    public void setEmailService(EmailService emailService) {
//        this.emailService = emailService;
//    }
    
    public void sendNotification(String recipient, String message) {
        if (emailService != null) {
            emailService.sendEmail(recipient, "Notification", message);
        }
    }
}
