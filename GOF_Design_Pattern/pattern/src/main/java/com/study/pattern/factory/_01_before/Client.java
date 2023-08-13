package com.study.pattern.factory._01_before;

public class Client {

    public static void main(String[] args) {
        Vehicle car = VehicleFactory.orderVehicle("car", "john@mail.com");
        System.out.println(car);

        Vehicle motorcycle = VehicleFactory.orderVehicle("motorcycle", "jane@mail.com");
        System.out.println(motorcycle);
    }
}
