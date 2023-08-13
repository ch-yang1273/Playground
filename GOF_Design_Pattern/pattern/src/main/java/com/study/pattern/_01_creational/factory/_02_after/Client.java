package com.study.pattern._01_creational.factory._02_after;

public class Client {

    public static void main(String[] args) {
        Client client = new Client();

        // DI라고 봐주세요~
        client.print(new TeslaVehicleFactory(),"TeslaCar", "john@mail.com" );
        client.print(new HyundaiVehicleFactory(),"HyundaiCar", "jane@mail.com" );
    }

    private void print(VehicleFactory vehicleFactory, String name, String email) {
        System.out.println(vehicleFactory.orderVehicle(name, email));
    }
}
