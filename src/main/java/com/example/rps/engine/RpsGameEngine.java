package com.example.rps.engine;

import com.example.rps.domain.HandShape;
import com.example.rps.domain.Round;
import com.example.rps.domain.RoundResult;
import org.springframework.stereotype.Component;

import static com.example.rps.domain.RoundResult.*;

@Component
public class RpsGameEngine implements GameEngine {

    @Override
    public final RoundResult getWinner(Round round) {
        HandShape playerOneMove = round.getPlayerOneMove();
        HandShape playerTwoMove = round.getPlayerTwoMove();

        if (playerOneMove.equals(playerTwoMove)) {
            return DRAW;
        } else {
            switch (playerOneMove) {
                case ROCK:
                    return HandShape.SCISSOR.equals(playerTwoMove) ? PLAYER1_WIN : PLAYER2_WIN;
                case PAPER:
                    return HandShape.SCISSOR.equals(playerTwoMove) ? PLAYER2_WIN : PLAYER1_WIN;
                case SCISSOR:
                    return HandShape.ROCK.equals(playerTwoMove) ? PLAYER2_WIN : PLAYER1_WIN;
                default:
                    throw new IllegalArgumentException();
            }
        }
    }
}
