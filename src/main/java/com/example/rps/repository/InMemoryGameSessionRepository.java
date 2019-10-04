package com.example.rps.repository;

import com.example.rps.domain.GameSession;
import com.example.rps.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class InMemoryGameSessionRepository implements GameSessionRepository {
    private ConcurrentMap<String, GameSession> gameSessionStore = new ConcurrentHashMap<>();

    @Autowired
    private IdGenerator<String> randomIdGenerator;

    @Override
    public GameSession fetchGameSession(Optional<String> gameSessionId) {
        String id = gameSessionId.orElse(randomIdGenerator.generateId());
        gameSessionStore.putIfAbsent(id, new GameSession(id));
        return gameSessionStore.get(id);
    }

    @Override
    public Collection<GameSession> getAll() {
        return this.gameSessionStore.values();
    }
}
