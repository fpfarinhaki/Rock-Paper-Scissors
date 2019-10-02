package com.example.rps.engine;

import com.example.rps.domain.RoundResult;

public interface GameEngine<M extends Enum> {

    M getMove();

    RoundResult getWinner(M move1, M move2);
}
