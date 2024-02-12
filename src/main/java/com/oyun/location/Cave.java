package com.oyun.location;

import com.oyun.model.Player;
import com.oyun.item.SpecialAward;
import com.oyun.character.obstacles.Zombie;
import com.oyun.location.abstracs.BattleLocation;

public class Cave extends BattleLocation {

    public Cave(Player player) {
        super(player, "Cave", 3, "Look out there are a few zombies", new Zombie(), new SpecialAward("food",false), 3);
    }
}
