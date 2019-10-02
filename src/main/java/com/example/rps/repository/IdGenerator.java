package com.example.rps.repository;

import java.io.Serializable;

public interface IdGenerator<T extends Serializable> {
    T generateId();
}
