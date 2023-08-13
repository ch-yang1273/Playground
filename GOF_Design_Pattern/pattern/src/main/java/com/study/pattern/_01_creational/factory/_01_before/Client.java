package com.study.pattern._01_creational.factory._01_before;

public class Client {

    public static void main(String[] args) {
        Vehicle teslaCar = VehicleFactory.orderVehicle("TeslaCar", "john@mail.com");
        System.out.println(teslaCar);

        Vehicle hyundaiCar = VehicleFactory.orderVehicle("HyundaiCar", "jane@mail.com");
        System.out.println(hyundaiCar);
    }
}
