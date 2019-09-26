package com.example.rps.engine;

import com.example.rps.domain.HandShape;
import com.example.rps.domain.RoundResult;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.example.rps.domain.RoundResult.PLAYER1_WIN;
import static com.example.rps.domain.RoundResult.PLAYER2_WIN;
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
        assertThat(rpsGameEnginge.getWinner(HandShape.PAPER, HandShape.ROCK)).isNotNull();
    }

    @Test
    @Parameters(method = "testGameResultWinners_Parameters")
    public void shouldReturnACorrectWinnerForTheBattle(HandShape move1, HandShape move2, RoundResult expectedResult) {
        assertThat(rpsGameEnginge.getWinner(move1, move2)).isEqualTo(expectedResult);
    }

    @SuppressWarnings("unused")
    private static Object[][] testGameResultWinners_Parameters() {
        return new Object[][]{
                {HandShape.PAPER, HandShape.ROCK, PLAYER1_WIN},
                {HandShape.PAPER, HandShape.SCISSOR,PLAYER2_WIN},
                {HandShape.ROCK, HandShape.PAPER, PLAYER2_WIN},
                {HandShape.ROCK, HandShape.SCISSOR, PLAYER1_WIN},
                {HandShape.SCISSOR, HandShape.ROCK, PLAYER2_WIN},
                {HandShape.SCISSOR, HandShape.PAPER, PLAYER1_WIN}
        };
    }

    @Test
    @Parameters(method = "testGameResultDraws_Parameters")
    public void shouldReturnEmptyWinnerForADraw(HandShape move1, HandShape move2) {
        assertThat(rpsGameEnginge.getWinner(move1, move2)).isEqualTo(RoundResult.DRAW);
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
