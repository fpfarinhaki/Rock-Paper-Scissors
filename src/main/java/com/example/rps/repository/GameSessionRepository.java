package com.example.rps.repository;

import com.example.rps.domain.GameSession;

import java.util.Optional;

public interface GameSessionRepository {

    GameSession fetchGameSession(Optional<String> gameSessionId);
}
