package com.playground.testing.fakebean;

public interface TargetRepository {
    void save(String key, String value);

    String get(String key);

    void delete(String key);
}
