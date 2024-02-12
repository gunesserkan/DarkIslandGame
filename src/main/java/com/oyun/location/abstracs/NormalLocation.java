package com.oyun.location.abstracs;

import com.oyun.model.Player;

public abstract class NormalLocation extends Location {
    public NormalLocation(Player player, String name, int id, String description) {
        super(player, name, id, description);
    }

    @Override
    public boolean onLocation() {
        return true;
    }
}
