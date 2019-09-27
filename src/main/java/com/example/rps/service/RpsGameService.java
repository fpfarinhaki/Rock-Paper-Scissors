package com.example.rps.service;

import com.example.rps.domain.GameSession;
import com.example.rps.domain.HandShape;
import com.example.rps.domain.Round;
import com.example.rps.domain.RoundResult;
import com.example.rps.engine.GameEngine;
import com.example.rps.repository.GameSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RpsGameService implements GameService {

    @Autowired
    private GameEngine<HandShape> rpsGameEngine;

    @Autowired
    private GameSessionRepository rpsGameRepository;


    @Override
    public GameSession playRound(Optional<String> gameSessionId) {
        HandShape playerOneMove = rpsGameEngine.getMove();
        HandShape playerTwoMove = rpsGameEngine.getMove();
        RoundResult result = rpsGameEngine.getWinner(playerOneMove, playerTwoMove);

        GameSession gameSession = rpsGameRepository.fetchGameSession(gameSessionId);
        gameSession.addRoundPlayed(new Round(playerOneMove, playerTwoMove, result));

        return gameSession;
    }

}
