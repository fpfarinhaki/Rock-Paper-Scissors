package com.example.rps.domain;

import com.example.rps.engine.MoveMaker;

public abstract class RpsPlayer implements MoveMaker<HandShape> {
    private final String playerId;

    public RpsPlayer(String playerId) {
        this.playerId = playerId;
    }
}
