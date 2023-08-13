package com.study.pattern._01_creational.factory._02_after;

public class TeslaVehicleFactory implements VehicleFactory {

    @Override
    public Vehicle createVehicle(String name) {
        return new TeslaVehicle(name);
    }
}
