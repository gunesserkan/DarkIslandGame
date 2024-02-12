package com.oyun.location;

import com.oyun.character.obstacles.Bear;
import com.oyun.model.Player;
import com.oyun.item.SpecialAward;
import com.oyun.location.abstracs.BattleLocation;

public class River extends BattleLocation {
    public River(Player player) {
        super(player, "River", 5, "Look out for Bears", new Bear(), new SpecialAward("water",false), 2);
    }
}
