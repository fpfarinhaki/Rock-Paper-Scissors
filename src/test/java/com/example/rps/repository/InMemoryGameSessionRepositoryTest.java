package com.example.rps.repository;

import com.example.rps.domain.GameSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@RunWith(MockitoJUnitRunner.class)
public class InMemoryGameSessionRepositoryTest {

    private static final String SOME_GENERATED_ID = "someGeneratedId";

    @InjectMocks
    private InMemoryGameSessionRepository repository;
    @Mock
    IdGenerator idGenerator;

    @Test
    public void shouldCreateANewGameSession() {
        given(idGenerator.generateId()).willReturn(SOME_GENERATED_ID);

        GameSession gameSession = repository.fetchGameSession(Optional.empty());

        then(idGenerator).should().generateId();
        assertThat(gameSession).isNotNull();
        assertThat(gameSession.getRoundsPlayed()).isEmpty();
    }

    @Test
    public void shouldReturnAnExistantGameSession() {
        given(idGenerator.generateId()).willReturn(SOME_GENERATED_ID);
        GameSession previouslyCreatedGameSession = repository.fetchGameSession(Optional.empty());
        String previouslyCreatedGameSessionid = previouslyCreatedGameSession.getId();

        GameSession gameSession = repository.fetchGameSession(Optional.of(previouslyCreatedGameSessionid));

        assertThat(gameSession).isNotNull();
        assertThat(gameSession.getId()).isEqualTo(previouslyCreatedGameSessionid);
    }
}