package com.example.rps.util;

import java.io.Serializable;

public interface IdGenerator<T extends Serializable> {
    T generateId();
}
