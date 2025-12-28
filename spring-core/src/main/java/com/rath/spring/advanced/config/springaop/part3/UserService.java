package com.rath.spring.advanced.config.springaop.part3;

import org.springframework.stereotype.Component;

/**
 * PART 3 - Spring AOP
 * Component with two methods where one calls the other
 */
@Component
public class UserService {
    
    public void createUser(String username) {
        System.out.println("  [UserService] createUser() called with: " + username);
        System.out.println("  [UserService] About to call validateUser() from within createUser()...");
        validateUser(username); // Self-invocation
        System.out.println("  [UserService] User created: " + username);
    }
    
    public void validateUser(String username) {
        System.out.println("  [UserService] validateUser() called with: " + username);
    }
}


