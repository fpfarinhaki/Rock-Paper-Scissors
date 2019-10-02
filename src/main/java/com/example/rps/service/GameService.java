package com.example.rps.service;

import com.example.rps.domain.GameSession;
import com.example.rps.domain.HandShape;
import com.example.rps.dto.Totals;

import java.util.Optional;

public interface GameService {

    GameSession playRound(Optional<String> gameSessionId, HandShape presetMove);

    Totals getTotals();

}
