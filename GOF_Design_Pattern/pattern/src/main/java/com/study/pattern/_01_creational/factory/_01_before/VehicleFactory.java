package com.study.pattern._01_creational.factory._01_before;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleFactory {

    public static Vehicle orderVehicle(String name, String email) {
        // Validation
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("차량 이름를 지정해주세요.");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("연락처를 남겨주세요.");
        }

        prepareFor(name);

        Vehicle vehicle = new Vehicle();
        vehicle.setName(name);

        // Customizing for specific name
        if (name.equalsIgnoreCase("TeslaCar")) {
            vehicle.setBrand("Tesla");
            vehicle.setColor("red");
        } else if (name.equalsIgnoreCase("HyundaiCar")) {
            vehicle.setBrand("Hyundai");
            vehicle.setColor("black");
        }

        // notify
        sendEmailTo(email, vehicle);

        return vehicle;
    }

    private static void prepareFor(String name) {
        System.out.println(name + " 만들 준비 중");
    }

    private static void sendEmailTo(String email, Vehicle vehicle) {
        System.out.println("to." + email + ": " + vehicle.getName() + " 다 만들었습니다.");
    }
}
