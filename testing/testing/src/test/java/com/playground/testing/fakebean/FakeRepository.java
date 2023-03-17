package com.playground.testing.fakebean;

import org.springframework.context.annotation.Primary;

import java.util.HashMap;
import java.util.Map;

@Primary
public class FakeRepository implements TargetRepository {

    Map<String, String> map = new HashMap<>();

    @Override
    public void save(String key, String value) {
        System.out.println("FakeRepository.save");
        map.put(key, value);
    }

    @Override
    public String get(String key) {
        System.out.println("FakeRepository.get");
        return map.get(key);
    }

    @Override
    public void delete(String key) {
        System.out.println("FakeRepository.delete");
        map.remove(key);
    }
}
