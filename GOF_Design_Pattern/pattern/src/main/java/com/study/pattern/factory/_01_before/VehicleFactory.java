package com.study.pattern.factory._01_before;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleFactory {

    public static Vehicle orderVehicle(String type, String email) {
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("차량 종류를 지정해주세요.");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("연락처를 남겨주세요.");
        }

        prepareFor(type);

        Vehicle vehicle = new Vehicle();
        vehicle.setName(type);

        // Customizing for specific type
        if (type.equalsIgnoreCase("car")) {
            vehicle.setBrand("Tesla");
            vehicle.setColor("red");
        } else if (type.equalsIgnoreCase("motorcycle")) {
            vehicle.setBrand("Harley");
            vehicle.setColor("black");
        }

        // notify
        sendEmailTo(email, vehicle);

        return vehicle;
    }

    private static void prepareFor(String type) {
        System.out.println(type + " 만들 준비 중");
    }

    private static void sendEmailTo(String email, Vehicle vehicle) {
        System.out.println(vehicle.getName() + " 다 만들었습니다.");
    }
}
