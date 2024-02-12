package com.oyun.item;

public class SpecialAward{
    private String name;
    private  boolean won;
    public SpecialAward(String name,boolean won){
        this.name=name;
        this.won=won;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }
}
