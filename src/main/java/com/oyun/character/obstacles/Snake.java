package com.oyun.character.obstacles;

import com.oyun.model.Obstacle;

import java.util.Random;

public class Snake extends Obstacle {

    public Snake() {
        super(4, "Snake", 0, 12,0);
        this.setDamage(randomDamageCalculate());
    }

    public int randomDamageCalculate() {
        Random random = new Random();
        return random.nextInt(3,6);
    }
}
