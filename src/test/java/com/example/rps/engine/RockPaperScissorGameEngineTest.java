package com.example.rps.engine;

import com.example.rps.HandShape;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RockPaperScissorGameEngineTest {

    private GameEngine<HandShape> rpsGameEnginge = new RockPaperScissorGameEngine();

    @Test
    public void shouldRunARoundOfRpsGame() {
        assertThat(rpsGameEnginge.getMove()).isInstanceOf(HandShape.class);
    }

    @Test
    public void shouldReturnAValidRpsResponse() {
        //given
        HandShape[] validRpsResult = {HandShape.PAPER, HandShape.ROCK, HandShape.SCISSOR};

        //when
        HandShape result = rpsGameEnginge.getMove();

        //then
        assertThat(result).isIn(validRpsResult);
    }
}