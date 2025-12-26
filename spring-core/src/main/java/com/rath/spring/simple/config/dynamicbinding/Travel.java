package com.rath.spring.dynamicbinding;

import org.springframework.stereotype.Component;


public class Travel {
     private Vehicle vehicle;
     
     public Travel(Vehicle vehicle) {
    	 this.vehicle = vehicle;
     }
     
     public void vehicleType() {
    	 vehicle.move();
     }
}
