package com.example.rps.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GameSession {

    private String id;
    private List<Round> roundsPlayed;

    public GameSession(String gameSessionId) {
        roundsPlayed = new ArrayList<>();
        id = gameSessionId;
    }

    public void addRoundPlayed(Round playedRound) {
        this.roundsPlayed.add(playedRound);
    }
}
