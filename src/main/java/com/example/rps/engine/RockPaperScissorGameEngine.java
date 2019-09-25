package com.example.rps.engine;

import com.example.rps.HandShape;

import java.util.Random;

public class RockPaperScissorGameEngine implements GameEngine<HandShape> {
    private static HandShape[] POSSIBLE_RESULTS = {HandShape.ROCK, HandShape.PAPER, HandShape.SCISSOR};

    @Override
    public HandShape getMove() {
        Random random = new Random();
        int randomIndex  = random.nextInt(POSSIBLE_RESULTS.length);
        return POSSIBLE_RESULTS[randomIndex];
    }
}
