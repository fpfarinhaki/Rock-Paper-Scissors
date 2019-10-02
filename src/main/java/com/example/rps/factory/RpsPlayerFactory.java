package com.example.rps.factory;

import com.example.rps.domain.HandShape;
import com.example.rps.domain.PresetMoveRpsPlayer;
import com.example.rps.domain.RandomMoveRpsPlayer;

public interface RpsPlayerFactory {

    RandomMoveRpsPlayer createRandomMoverPlayer();

    PresetMoveRpsPlayer createPresetMoverPlayer(HandShape presetMove);
}
