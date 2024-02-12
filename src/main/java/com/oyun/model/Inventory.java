package com.oyun.model;

import com.oyun.item.Armor;
import com.oyun.item.Weapon;

public class Inventory {
   private Weapon weapon;
   private Armor armor;
   private boolean water;
   private boolean fireWood;
   private boolean food;


    public Inventory() {
        this.weapon = new Weapon(0, "Punch", 0, 0);
        this.armor = new Armor(0, "none", 0, 0);
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public boolean isWater() {
        return water;
    }

    public void setWater(boolean water) {
        this.water = water;
    }

    public boolean isFireWood() {
        return fireWood;
    }

    public void setFireWood(boolean fireWood) {
        this.fireWood = fireWood;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public void setAwards(String award){
        switch (award){
            case "water":
                this.setWater(true);
                break;
            case "firewood":
                this.setFireWood(true);
                break;
            case "food":
                this.setFood(true);
        }
    }
    public String getAwards(){
        String awards="";
        if(this.isWater()){
            awards+=" water ";
        }else if(this.isFood()){
            awards+=" food ";
        }else if(this.isFireWood()){
            awards+=" firewood ";
        }
        return awards;
    }
}
