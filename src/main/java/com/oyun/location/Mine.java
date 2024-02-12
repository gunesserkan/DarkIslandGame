package com.oyun.location;

import com.oyun.character.obstacles.Snake;
import com.oyun.item.Armor;
import com.oyun.item.SpecialAward;
import com.oyun.item.Weapon;
import com.oyun.location.abstracs.BattleLocation;
import com.oyun.model.Player;

import java.util.Random;

public class Mine extends BattleLocation {

    public Mine(Player player) {
        super(player, "Mine", 6, " There are sneaks, you can drop weapons, armors and money from snakes! ", new Snake(), new SpecialAward(" ", false), 5);
    }

    @Override
    public boolean onLocation() {
        System.out.println("##################################################################################");
        System.out.println("You are here: " + this.getName());
        int randomObstacleCount = this.randomObstacleValue();
        System.out.println("Look out there are " + randomObstacleCount + " " + this.getObstacle().getName());
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("What will you do?");
        System.out.println("<A>ttack Or <R>un");
        String selectedAct = input.nextLine().toUpperCase();
        if (selectedAct.equals("A") && combat(randomObstacleCount)) {
            System.out.println("you killed all of the " + this.getObstacle().getName() + " at the " + this.getName());
            return true;
        }
        if (this.getPlayer().getHealth() <= 0) {
            System.out.println("you die");
            return false;
        }
        return true;
    }

    @Override
    public boolean combat(int randomObstacleValue) {
        for (int i = 1; i <= randomObstacleValue; i++) {
            whoIsFırst();
            this.getObstacle().setHealth(this.getObstacle().getDefaultHealth());
            System.out.println("###############################################################################");
            System.out.println();
            playerStatistics();
            obstacleStatistics(i);
            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
                if (this.isPlayerFirst()) {
                    System.out.println("<A>ttack Or <R>un");
                    String selectedAct = input.nextLine().toUpperCase();
                    System.out.println("###############################################################################");
                    if (selectedAct.equals("A")) {
                        this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                        afterAttack();
                        if (this.getObstacle().getHealth() > 0) {
                            System.out.println(this.getObstacle().getName() + " attacked you");
                            System.out.println("------------------------");
                            int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                            if (obstacleDamage < 0) {
                                obstacleDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                            this.afterAttack();
                            this.setPlayerFirst(true);
                        }
                    } else {
                        return false;
                    }
                } else {
                    if (this.getObstacle().getHealth() > 0) {
                        System.out.println(this.getObstacle().getName() + " attacked you");
                        System.out.println("------------------------");
                        int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                        if (obstacleDamage < 0) {
                            obstacleDamage = 0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                        this.afterAttack();
                    }
                    this.setPlayerFirst(true);
                }

            }
            if (this.getObstacle().getHealth() < this.getPlayer().getHealth()) {
                System.out.println("You killed the " + i + ". " + this.getObstacle().getName());
                System.out.println("-----------------------");
                placeAward(createAward());
            } else {
                return false;
            }
        }
        return true;
    }

    public Object createAward() {
        Weapon[] weaponList = {
                new Weapon(1, "Pistol", 2, 15),
                new Weapon(2, "Sword", 3, 35),
                new Weapon(3, "Rifle", 7, 45)
        };
        Armor[] armorList = {
                new Armor(1, "Light", 1, 15)
                , new Armor(2, "Middle", 3, 25)
                , new Armor(3, "Heavy", 5, 40)
        };
        Integer[] moneyList = {1, 5, 10};
        Random random = new Random();
        double randomDouble = random.nextDouble();
        if (randomDouble < 0.45) {
            return null;
        } else if (randomDouble < 0.70) {
            //para kazanma oranı
            double randomMoney = random.nextDouble();
            if (randomMoney < 0.50) {
                return moneyList[0];
            } else if (randomDouble < 0.80) {
                return moneyList[1];
            } else {
                return moneyList[2];
            }
        } else if (randomDouble < 0.85) {
            //silah kazanma oranı
            double randomWeapon = random.nextDouble();
            if (randomWeapon < 0.50) {
                return weaponList[0];
            } else if (randomWeapon < 0.80) {
                return weaponList[1];
            } else {
                return weaponList[2];
            }
        } else {
            //zırh kazanma ihtimali
            double randomArmor = random.nextDouble();
            if (randomArmor < 0.50) {
                return armorList[0];
            } else if (randomArmor < 0.80) {
                return armorList[1];
            } else {
                return armorList[2];
            }
        }
    }

    public void placeAward(Object obj) {
        if (obj != null) {
            if (obj.getClass().equals(Weapon.class)) {
                this.getPlayer().getInventory().setWeapon((Weapon) obj);
                System.out.println("You won this Award: " + obj.getClass().getSimpleName() + " from " + this.getObstacle().getName());
            } else if (obj.getClass().equals(Armor.class)) {
                this.getPlayer().getInventory().setArmor((Armor) obj);
                System.out.println("You won this Award: " + obj.getClass().getSimpleName() + " from " + this.getObstacle().getName());
            } else if (obj.getClass().equals(Integer.class)) {
                this.getPlayer().setMoney(this.getPlayer().getMoney() + (Integer) obj);
                System.out.println("You won this Award: Money from " + this.getObstacle().getName());
            }
        } else {
            System.out.println("You did not win anything from " + this.getObstacle().getName());
        }
    }
}
