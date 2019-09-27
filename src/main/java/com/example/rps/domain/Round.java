package com.example.rps.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Round {

    private HandShape playerOneMove;
    private HandShape playerTwoMove;
    private RoundResult roundResult;

}
