package com.oyun.model;

import com.oyun.character.heroes.Archer;
import com.oyun.character.heroes.Knight;
import com.oyun.character.heroes.Samurai;

import java.util.Scanner;

public class Player {
    private int damage;
    private int health;
    private int defaultHealth;
    private int money;
    private String name;
    private String characterName;
    private Inventory inventory;
    private Scanner input = new Scanner(System.in);

    public Player(String name) {
        this.name = name;
        this.inventory = new Inventory();
    }

    public void selectCharacter() {
        System.out.println("Characters");
        System.out.println("------------------------------------------------------------");
        GameCharacter[] characters = {new Samurai(), new Archer(), new Knight()};
        for (GameCharacter character : characters) {
            System.out.println("ID: " + character.getId() +
                    "\t Character: " + character.getName() +
                    "\t Damage: " + character.getDamage() +
                    "\t Healthy: " + character.getHealthy() +
                    "\t Money: " + character.getMoney());
        }
        System.out.println("------------------------------------------------------------");
        System.out.print("Please select a character:");
        int selectedCharacter = input.nextInt();
        switch (selectedCharacter) {
            case 1:
                initPlayer(characters[0]);
                break;
            case 2:
                initPlayer(characters[1]);
                break;
            case 3:
                initPlayer(characters[2]);
                break;
            default:
                initPlayer(characters[0]);
        }
      /*  System.out.println("Selected character: " + this.getCharacterName()
                + " Damage: " + this.getDamage()
                + " Healthy: " + this.getHealth()
                + " Money: " + this.getMoney());*/
    }

    private void initPlayer(GameCharacter character) {
        this.setCharacterName(character.getName());
        this.setDamage(character.getDamage());
        this.setHealth(character.getHealthy());
        this.setDefaultHealth(character.getHealthy());
        this.setMoney(character.getMoney());
    }

    public void printInfo() {
        System.out.println(
                " Weapon: " + this.getInventory().getWeapon().getName()
                        + " Armor: " + this.getInventory().getArmor().getBlock()
                        + " Damage: " + this.getTotalDamage()
                        + " Healthy: " + this.getHealth()
                        + " Money: " + this.getMoney());
    }

    public int getTotalDamage() {
        return damage + inventory.getWeapon().getDamage();
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDefaultHealth() {
        return defaultHealth;
    }

    public void setDefaultHealth(int defaultHealth) {
        this.defaultHealth = defaultHealth;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
