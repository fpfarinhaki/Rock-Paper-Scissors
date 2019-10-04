package com.example.rps.repository;

import com.example.rps.domain.GameSession;
import com.example.rps.util.IdGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collection;
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

    @Before
    public void setUp() throws Exception {
        given(idGenerator.generateId()).willReturn(SOME_GENERATED_ID);
    }

    @Test
    public void shouldCreateANewGameSession() {
        GameSession gameSession = repository.fetchGameSession(Optional.empty());

        then(idGenerator).should().generateId();
        assertThat(gameSession).isNotNull();
        assertThat(gameSession.getRoundsPlayed()).isEmpty();
    }

    @Test
    public void shouldReturnAnExistantGameSession() {
        GameSession previouslyCreatedGameSession = repository.fetchGameSession(Optional.empty());
        String previouslyCreatedGameSessionid = previouslyCreatedGameSession.getId();

        GameSession gameSession = repository.fetchGameSession(Optional.of(previouslyCreatedGameSessionid));

        assertThat(gameSession).isNotNull();
        assertThat(gameSession.getId()).isEqualTo(previouslyCreatedGameSessionid);
    }

    @Test
    public void shouldGetAllGameSessions() {
        GameSession gameSession = repository.fetchGameSession(Optional.empty());

        Collection<GameSession> all = repository.getAll();

        assertThat(all.size()).isEqualTo(1);
        assertThat(all).contains(gameSession);
    }
}