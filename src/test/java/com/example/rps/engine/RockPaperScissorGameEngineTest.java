package com.example.rps.engine;

import com.example.rps.HandShape;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
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

    @Test
    public void shouldReturnAWinner() {
        assertThat(rpsGameEnginge.getWinner(HandShape.PAPER, HandShape.ROCK)).isNotEmpty();
    }

    @Test
    @Parameters(method = "testGameResultWinners_Parameters")
    public void shouldReturnACorrectWinnerForTheBattle(HandShape move1, HandShape move2, Optional<HandShape> expectedResult) {
        assertThat(rpsGameEnginge.getWinner(move1, move2)).isEqualTo(expectedResult);
    }

    @SuppressWarnings("unused")
    private static Object[][] testGameResultWinners_Parameters() {
        return new Object[][]{
                {HandShape.PAPER, HandShape.ROCK, Optional.of(HandShape.PAPER)},
                {HandShape.PAPER, HandShape.SCISSOR, Optional.of(HandShape.SCISSOR)},
                {HandShape.ROCK, HandShape.PAPER, Optional.of(HandShape.PAPER)},
                {HandShape.ROCK, HandShape.SCISSOR, Optional.of(HandShape.ROCK)},
                {HandShape.SCISSOR, HandShape.ROCK, Optional.of(HandShape.ROCK)},
                {HandShape.SCISSOR, HandShape.PAPER, Optional.of(HandShape.SCISSOR)}
        };
    }

    @Test
    @Parameters(method = "testGameResultDraws_Parameters")
    public void shouldReturnEmptyWinnerForADraw(HandShape move1, HandShape move2) {
        assertThat(rpsGameEnginge.getWinner(move1, move2)).isEmpty();
    }

    @SuppressWarnings("unused")
    private static Object[][] testGameResultDraws_Parameters() {
        return new Object[][]{
                {HandShape.PAPER, HandShape.PAPER},
                {HandShape.ROCK, HandShape.ROCK},
                {HandShape.SCISSOR, HandShape.SCISSOR}
        };
    }
}
