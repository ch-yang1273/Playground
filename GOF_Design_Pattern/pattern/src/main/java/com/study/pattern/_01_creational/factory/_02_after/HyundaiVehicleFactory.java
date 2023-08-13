package com.study.pattern._01_creational.factory._02_after;

public class HyundaiVehicleFactory implements VehicleFactory {

    @Override
    public Vehicle createVehicle(String name) {
        return new HyundaiVehicle(name);
    }
}
