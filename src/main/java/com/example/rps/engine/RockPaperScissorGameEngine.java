package com.example.rps.engine;

import com.example.rps.HandShape;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.Random;

public class RockPaperScissorGameEngine implements GameEngine<HandShape> {
    private static HandShape[] POSSIBLE_RESULTS = {HandShape.ROCK, HandShape.PAPER, HandShape.SCISSOR};

    @Override
    public HandShape getMove() {
        Random random = new Random();
        int randomIndex = random.nextInt(POSSIBLE_RESULTS.length);
        return POSSIBLE_RESULTS[randomIndex];
    }

    @Override
    public Optional<HandShape> getWinner(HandShape move1, HandShape move2) {
        if (move1.equals(move2)) {
            return Optional.empty();
        } else {
            switch (move1) {
                case ROCK:
                    return HandShape.SCISSOR.equals(move2) ? Optional.of(move1) : Optional.of(move2);
                case PAPER:
                    return HandShape.SCISSOR.equals(move2) ? Optional.of(move2) : Optional.of(move1);
                case SCISSOR:
                    return HandShape.ROCK.equals(move2) ? Optional.of(move2) : Optional.of(move1);
                default:
                    return Optional.empty();
            }
        }
    }
}
