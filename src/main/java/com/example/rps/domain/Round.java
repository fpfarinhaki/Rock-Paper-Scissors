package com.example.rps.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class Round {

    private final HandShape playerOneMove;
    private final HandShape playerTwoMove;

    private RoundResult roundResult;

}
