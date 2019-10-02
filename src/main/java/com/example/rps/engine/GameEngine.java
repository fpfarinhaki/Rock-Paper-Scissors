package com.example.rps.engine;

import com.example.rps.domain.Round;
import com.example.rps.domain.RoundResult;

public interface GameEngine {

    RoundResult getWinner(Round round);
}
