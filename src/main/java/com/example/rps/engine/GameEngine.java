package com.example.rps.engine;

public interface GameEngine<R extends Enum> {

    R getMove();

}
