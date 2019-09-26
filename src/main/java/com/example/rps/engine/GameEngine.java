package com.example.rps.engine;

import java.util.Optional;

public interface GameEngine<M extends Enum> {

    M getMove();

    /**
     *
     * An empty Optional means a draw.
     */
    Optional<M> getWinner(M move1, M move2);
}
