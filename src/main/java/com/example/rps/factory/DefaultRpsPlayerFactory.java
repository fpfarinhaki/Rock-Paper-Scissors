package com.example.rps.factory;

import com.example.rps.domain.HandShape;
import com.example.rps.domain.PresetMoveRpsPlayer;
import com.example.rps.domain.RandomMoveRpsPlayer;
import com.example.rps.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;

public class DefaultRpsPlayerFactory implements RpsPlayerFactory {

    @Autowired
    private IdGenerator<String> randomStringIdGenerator;

    @Override
    public RandomMoveRpsPlayer createRandomMoverPlayer() {
        return new RandomMoveRpsPlayer(randomStringIdGenerator.generateId());
    }

    @Override
    public PresetMoveRpsPlayer createPresetMoverPlayer(HandShape presetMove) {
        return new PresetMoveRpsPlayer(randomStringIdGenerator.generateId(), presetMove);
    }
}
