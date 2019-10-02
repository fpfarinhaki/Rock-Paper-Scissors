package com.example.rps.service;

import com.example.rps.domain.*;
import com.example.rps.engine.GameEngine;
import com.example.rps.factory.RpsPlayerFactory;
import com.example.rps.repository.GameSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RpsGameService implements GameService {

    @Autowired
    private GameEngine rpsGameEngine;
    @Autowired
    private GameSessionRepository inMemoryGameSessionRepository;
    @Autowired
    private RpsPlayerFactory defaultRpsPlayerFactory;

    @Override
    public GameSession playRound(Optional<String> gameSessionId, HandShape presetMove) {
        RandomMoveRpsPlayer player1 = defaultRpsPlayerFactory.createRandomMoverPlayer();
        PresetMoveRpsPlayer player2 = defaultRpsPlayerFactory.createPresetMoverPlayer(presetMove);

        return playRound(gameSessionId, player1, player2);
    }

    private GameSession playRound(Optional<String> gameSessionId, RpsPlayer player1, RpsPlayer player2) {
        Round playedRound = new Round(player1.makeMove(), player2.makeMove());
        RoundResult result = rpsGameEngine.getWinner(playedRound);

        GameSession gameSession = inMemoryGameSessionRepository.fetchGameSession(gameSessionId);
        gameSession.addRoundPlayed(playedRound, result);

        return gameSession;
    }

}
