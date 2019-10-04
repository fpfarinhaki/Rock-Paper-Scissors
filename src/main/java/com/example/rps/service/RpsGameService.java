package com.example.rps.service;

import com.example.rps.domain.*;
import com.example.rps.dto.Totals;
import com.example.rps.engine.GameEngine;
import com.example.rps.factory.RpsPlayerFactory;
import com.example.rps.repository.GameSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.rps.domain.RoundResult.DRAW;
import static com.example.rps.domain.RoundResult.PLAYER1_WIN;
import static com.example.rps.domain.RoundResult.PLAYER2_WIN;

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

    @Override
    public Totals getTotals() {
        Collection<GameSession> allSessions = inMemoryGameSessionRepository.getAll();
        List<Round> playedRounds = allSessions.stream()
                .map(GameSession::getRoundsPlayed)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        return Totals.builder()
                .rounds(playedRounds.stream().count())
                .draws(playedRounds.stream().filter(round -> DRAW.equals(round.getRoundResult())).count())
                .playerOneWins(playedRounds.stream().filter(round -> PLAYER1_WIN.equals(round.getRoundResult())).count())
                .playerTwoWins(playedRounds.stream().filter(round -> PLAYER2_WIN.equals(round.getRoundResult())).count())
                .build();
    }g

    private GameSession playRound(Optional<String> gameSessionId, RpsPlayer player1, RpsPlayer player2) {
        Round playedRound = new Round(player1.makeMove(), player2.makeMove());
        RoundResult result = rpsGameEngine.getWinner(playedRound);

        GameSession gameSession = inMemoryGameSessionRepository.fetchGameSession(gameSessionId);
        gameSession.addRoundPlayed(playedRound, result);

        return gameSession;
    }
}
