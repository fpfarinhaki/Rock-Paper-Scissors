package com.example.rps.domain;

import java.util.Random;

public class RandomMoveRpsPlayer extends RpsPlayer {
    private static HandShape[] POSSIBLE_RESULTS = {HandShape.ROCK, HandShape.PAPER, HandShape.SCISSOR};

    public RandomMoveRpsPlayer(String playerId) {
        super(playerId);
    }

    @Override
    public HandShape makeMove() {
        Random random = new Random();
        int randomIndex = random.nextInt(POSSIBLE_RESULTS.length);
        return POSSIBLE_RESULTS[randomIndex];
    }
}
