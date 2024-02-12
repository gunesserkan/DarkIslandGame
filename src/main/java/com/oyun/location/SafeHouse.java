package com.oyun.location;

import com.oyun.model.Player;
import com.oyun.location.abstracs.NormalLocation;

public class SafeHouse extends NormalLocation {

    public SafeHouse(Player player) {
        super(player, "Safe House", 1, "There is no Obstacle here!");
    }

    @Override
    public boolean onLocation() {
        if(isComplete()){
            System.out.println("###You finish The Game###");
            System.out.println("See you next time...");
            System.exit(1);
        }
        System.out.println("You are at the safe house");
        System.out.println("your health is recovered");
        System.out.println("You need to find three awards to finish the game");
        System.out.println("Awards you have: "+this.getPlayer().getInventory().getAwards());
        this.getPlayer().setHealth(this.getPlayer().getDefaultHealth());
        return true;
    }
    public boolean isComplete(){
        return (this.getPlayer().getInventory().isFood()&&this.getPlayer().getInventory().isWater()&&this.getPlayer().getInventory().isFireWood());
    }
}
