package com.example.rps.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Totals {

    private final Long rounds;
    private final Long playerOneWins;
    private final Long playerTwoWins;
    private final Long draws;

}
