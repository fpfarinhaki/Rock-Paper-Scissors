package com.example.rps.controller;

import com.example.rps.domain.GameSession;
import com.example.rps.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/games/rps")
@CrossOrigin(origins = "http://localhost:4200")
public class RpsGameController {

    @Autowired
    private GameService rpsGameService;

    @GetMapping(value = {"/{gameSessionId}/round/play", "/round/play"}, produces = "application/json")
    public GameSession playRound(@PathVariable(name = "gameSessionId", required = false) Optional<String> gameSessionId) {

        return rpsGameService.playRound(gameSessionId);
    }

}
