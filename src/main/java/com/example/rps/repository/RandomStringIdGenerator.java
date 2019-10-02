package com.example.rps.repository;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RandomStringIdGenerator implements IdGenerator<String> {
    @Override
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
