package com.playground.testing.fakebean;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RealRepository implements TargetRepository {

    Map<String, String> map = new HashMap<>();

    @Override
    public void save(String key, String value) {
        System.out.println("RealRepository.save");
        map.put(key, value);
    }

    @Override
    public String get(String key) {
        System.out.println("RealRepository.get");
        return map.get(key);
    }

    @Override
    public void delete(String key) {
        System.out.println("RealRepository.delete");
        map.remove(key);
    }
}
