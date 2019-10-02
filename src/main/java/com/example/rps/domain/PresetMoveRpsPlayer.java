package com.example.rps.domain;

public class PresetMoveRpsPlayer extends RpsPlayer {

    private final HandShape presetMove;

    public PresetMoveRpsPlayer(String playerId, HandShape presetMove) {
        super(playerId);
        this.presetMove = presetMove;
    }

    @Override
    public HandShape makeMove() {
        return this.presetMove;
    }
}
