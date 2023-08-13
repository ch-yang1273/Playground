package com.study.pattern._01_creational.factory._01_before;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Vehicle {

    private String name;
    private String color;
    private String brand;

    @Override
    public String toString() {
        return " Vehicle{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
