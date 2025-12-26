package com.rath.spring.simple.config.annotationconfig;

import org.springframework.stereotype.Component;

@Component
public class Travel {
     private Vehicle vehicle;
     
     public Travel(Vehicle vehicle) {
    	 this.vehicle = vehicle;
     }
     
     public void vehicleType() {
    	 vehicle.move();
     }
}
