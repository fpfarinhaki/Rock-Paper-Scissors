package com.example.rps.engine;

import com.example.rps.domain.HandShape;
import com.example.rps.domain.RoundResult;
import org.springframework.stereotype.Component;

import java.util.Random;

import static com.example.rps.domain.RoundResult.*;

@Component
public class RpsGameEngine implements GameEngine<HandShape> {
    private static HandShape[] POSSIBLE_RESULTS = {HandShape.ROCK, HandShape.PAPER, HandShape.SCISSOR};

    @Override
    public HandShape getMove() {
        Random random = new Random();
        int randomIndex = random.nextInt(POSSIBLE_RESULTS.length);
        return POSSIBLE_RESULTS[randomIndex];
    }

    @Override
    public RoundResult getWinner(HandShape move1, HandShape move2) {
        if (move1.equals(move2)) {
            return DRAW;
        } else {
            switch (move1) {
                case ROCK:
                    return HandShape.SCISSOR.equals(move2) ? PLAYER1_WIN : PLAYER2_WIN;
                case PAPER:
                    return HandShape.SCISSOR.equals(move2) ? PLAYER2_WIN : PLAYER1_WIN;
                case SCISSOR:
                    return HandShape.ROCK.equals(move2) ? PLAYER2_WIN : PLAYER1_WIN;
                default:
                    throw new IllegalArgumentException();
            }
        }
    }
}
