package com.oyun.location;

import com.oyun.model.Player;
import com.oyun.item.SpecialAward;
import com.oyun.character.obstacles.Vampire;
import com.oyun.location.abstracs.BattleLocation;

public class Forest extends BattleLocation {

    public Forest(Player player) {
        super(player, "Forest", 4, "Look out for vampires", new Vampire(), new SpecialAward("firewood",false), 3);
    }
}
