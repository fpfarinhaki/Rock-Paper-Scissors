package com.example.rps.service;

import com.example.rps.domain.*;
import com.example.rps.engine.GameEngine;
import com.example.rps.factory.RpsPlayerFactory;
import com.example.rps.repository.GameSessionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.Times;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RpsGameServiceTest {

    private static final HandShape SOME_RANDOM_MOVE = HandShape.PAPER;
    private static final String SOME_GENERATED_ID = "someGeneratedId";
    private static final RoundResult SOME_ROUND_RESULT = RoundResult.DRAW;
    private static final HandShape PRESET_MOVE = HandShape.SCISSOR;

    @Mock private GameSessionRepository gameSessionRepository;
    @Mock private GameEngine rpsGameEngine;
    @Mock private RpsPlayerFactory playerFactory;
    @Mock private RandomMoveRpsPlayer randomMoveRpsPlayer;
    @Mock private PresetMoveRpsPlayer presetMoveRpsPlayer;
    @Captor private ArgumentCaptor<Round> playedRoundCaptor;

    @InjectMocks
    private RpsGameService rpsGameService;

    @Test
    public void shouldPlayARoundOfGameWhenNoGameSessionIdProvided() {
        Optional<String> noSessionId = Optional.empty();
        given(gameSessionRepository.fetchGameSession(noSessionId)).willReturn(new GameSession(SOME_GENERATED_ID));

        GameSession result = playRound(noSessionId);

        then(rpsGameEngine).should(new Times(1)).getWinner(playedRoundCaptor.capture());
        assertThat(playedRoundCaptor.getValue().getPlayerOneMove()).isEqualTo(SOME_RANDOM_MOVE);
        assertThat(playedRoundCaptor.getValue().getPlayerTwoMove()).isEqualTo(PRESET_MOVE);
        then(gameSessionRepository).should(new Times(1)).fetchGameSession(Optional.empty());

        assertThat(result.getId()).isNotBlank();
        assertThat(result.getRoundsPlayed()).hasSize(1);
    }

    @Test
    public void shouldPlayRoundWhenExistantGameSessionIdIsProvided() {
        //Given a no sessionId for the first round
        Optional<String> noSessionId = Optional.empty();
        given(gameSessionRepository.fetchGameSession(noSessionId)).willReturn(new GameSession(SOME_GENERATED_ID));
        GameSession firstRoundPlayed = playRound(Optional.empty());

        // Given a generated gameSessionId for the second round
        Optional<String> gameSessionId = Optional.ofNullable(firstRoundPlayed.getId());
        given(gameSessionRepository.fetchGameSession(gameSessionId)).willReturn(firstRoundPlayed);

        //When second round is played
        GameSession secondRoundPlayed = playRound(gameSessionId);

        //Then
        then(rpsGameEngine).should(new Times(2)).getWinner(playedRoundCaptor.capture());
        assertThat(playedRoundCaptor.getValue().getPlayerOneMove()).isEqualTo(SOME_RANDOM_MOVE);
        assertThat(playedRoundCaptor.getValue().getPlayerTwoMove()).isEqualTo(PRESET_MOVE);
        then(gameSessionRepository).should(new Times(1)).fetchGameSession(noSessionId);
        then(gameSessionRepository).should(new Times(1)).fetchGameSession(gameSessionId);
        assertThat(secondRoundPlayed.getId()).isEqualTo(gameSessionId.get());
        assertThat(secondRoundPlayed.getRoundsPlayed()).hasSize(2);
    }

    private GameSession playRound(Optional<String> gameSessionId) {
        when(playerFactory.createPresetMoverPlayer(PRESET_MOVE)).thenReturn(presetMoveRpsPlayer);
        when(playerFactory.createRandomMoverPlayer()).thenReturn(randomMoveRpsPlayer);
        when(presetMoveRpsPlayer.makeMove()).thenReturn(PRESET_MOVE);
        when(randomMoveRpsPlayer.makeMove()).thenReturn(SOME_RANDOM_MOVE);
        when(rpsGameEngine.getWinner(any(Round.class))).thenReturn(SOME_ROUND_RESULT);

        return rpsGameService.playRound(gameSessionId, PRESET_MOVE);
    }
}