package com.example.rps.controller;

import com.example.rps.domain.GameSession;
import com.example.rps.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/games/rps")
public class RpsGameController {

    @Autowired
    private GameService rpsGameService;

    @GetMapping(value = {"/{gameSessionId}/round/play", "/round/play"}, produces = "application/json")
    public GameSession playRound(@PathVariable(name = "gameSessionId", required = false) Optional<String> gameSessionId) {

        return rpsGameService.playRound(gameSessionId);
    }

}
