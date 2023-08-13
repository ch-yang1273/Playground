package com.study.pattern._01_creational.factory._02_after;

public interface VehicleFactory {

    default Vehicle orderVehicle(String name, String email) {
        validate(name, email);
        prepareFor(name);

        Vehicle vehicle = createVehicle(name);

        sendEmailTo(email, vehicle);
        return vehicle;
    }

    Vehicle createVehicle(String name);

    // 자바 8버전을 쓰고 있다면 인터페이스에 private 메서드를 사용할 수 없다.
    // 그 때는 추상 클래스인 DefaultVehicleFactory를 작성하고, 이 DefaultVehicleFactory를 상속 받도록 하면 된다.
    private void validate(String name, String email) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("차량 이름를 지정해주세요.");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("연락처를 남겨주세요.");
        }
    }

    private void prepareFor(String name) {
        System.out.println(name + " 만들 준비 중");
    }

    private void sendEmailTo(String email, Vehicle vehicle) {
        System.out.println("to." + email + ": " + vehicle.getName() + " 다 만들었습니다.");
    }
}
