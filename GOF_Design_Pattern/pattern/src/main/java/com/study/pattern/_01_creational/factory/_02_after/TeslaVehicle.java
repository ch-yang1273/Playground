package com.study.pattern._01_creational.factory._02_after;

public class TeslaVehicle extends Vehicle {

    public TeslaVehicle(String name) {
        setName(name);
        setBrand("Tesla");
        setColor("red");
    }
}
