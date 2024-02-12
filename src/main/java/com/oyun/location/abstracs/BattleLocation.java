package com.oyun.location.abstracs;

import com.oyun.model.Obstacle;
import com.oyun.model.Player;
import com.oyun.item.SpecialAward;

import java.util.Random;

public abstract class BattleLocation extends Location {
    private Obstacle obstacle;
    private SpecialAward specialAward;
    private int maxObstacleCount;
    private boolean isPlayerFirst;

    public BattleLocation(Player player, String name, int id, String description, Obstacle obstacle, SpecialAward specialAward, int maxObstacleCount) {
        super(player, name, id, description);
        this.obstacle = obstacle;
        this.specialAward = specialAward;
        this.maxObstacleCount = maxObstacleCount;
    }

    public int randomObstacleValue() {
        Random random = new Random();
        return random.nextInt(this.getMaxObstacleCount()) + 1;
    }
    public void whoIsFırst(){
        Random random = new Random();
        this.isPlayerFirst = random.nextBoolean();
    }

    public boolean isPlayerFirst() {
       return isPlayerFirst;
    }

    public void setPlayerFirst(boolean playerFirst) {
        isPlayerFirst = playerFirst;
    }

    @Override
    public boolean onLocation() {
        if (this.getSpecialAward().isWon()) {
            System.out.println("You won special award of " + this.getName());
            return true;
        }
        System.out.println("##################################################################################");
        System.out.println("You are here: " + this.getName());
        int randomObstacleCount = this.randomObstacleValue();
        System.out.println("Look out there are " + randomObstacleCount + " " + this.getObstacle().getName());
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("What will you do?");
        System.out.println("<A>ttack Or <R>un");
        String selectedAct = input.nextLine().toUpperCase();
        if (selectedAct.equals("A") && combat(randomObstacleCount)) {
            System.out.println("you killed all of the "+this.getObstacle().getName()+" at the " + this.getName());
            System.out.println("You have new item " + this.getSpecialAward().getName());
            this.getPlayer().getInventory().setAwards(this.getSpecialAward().getName());
            this.getSpecialAward().setWon(true);
            return true;
        }
        if (this.getPlayer().getHealth() <= 0) {
            System.out.println("you die");
            return false;
        }
        return true;
    }

    public boolean combat(int randomObstacleValue) {
        for (int i = 1; i <= randomObstacleValue; i++) {
            whoIsFırst();
            this.getObstacle().setHealth(this.getObstacle().getDefaultHealth());
            System.out.println("###############################################################################");
            System.out.println();
            playerStatistics();
            obstacleStatistics(i);
            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
                if (this.isPlayerFirst) {
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
                            afterAttack();
                            this.isPlayerFirst = true;
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
                        afterAttack();
                        this.isPlayerFirst = true;
                    }
                }

            }
            if (this.getObstacle().getHealth() < this.getPlayer().getHealth()) {
                System.out.println("You killed the " + i + ". " + this.getObstacle().getName());
                System.out.println("you won " + this.getObstacle().getAward() + " money");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                System.out.println("Your total money: " + this.getPlayer().getMoney());
                System.out.println("----------------------------------------------");
                System.out.println();
            } else {
                return false;
            }
        }
        return true;
    }

    public void afterAttack() {
        if (this.getObstacle().getHealth() > 0 && this.getPlayer().getHealth() > 0) {
            System.out.println("Your health: " + this.getPlayer().getHealth());
            System.out.println(this.getObstacle().getName() + " healthy: " + this.getObstacle().getHealth());
            System.out.println("##############################");
        }
    }

    public void playerStatistics() {
        System.out.println("Your statistics");
        System.out.println("---------------------------------------");
        System.out.println("Healthy: " + this.getPlayer().getHealth());
        if (this.getPlayer().getInventory().getWeapon().getDamage() > 0) {
            System.out.println("Your weapon: " + this.getPlayer().getInventory().getWeapon().getName());
        }
        System.out.println("Damage: " + this.getPlayer().getTotalDamage());
        System.out.println("Armor value: " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println();
    }

    public void obstacleStatistics(int obstacleNumber) {
        System.out.println(obstacleNumber + ". " + this.getObstacle().getName() + " statistics");
        System.out.println("------------------------------");
        System.out.println("Healthy: " + this.getObstacle().getHealth());
        System.out.println("Damage: " + this.getObstacle().getDamage());
        System.out.println("Award: " + this.getObstacle().getAward());
        System.out.println();
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public SpecialAward getSpecialAward() {
        return specialAward;
    }

    public void setAward(SpecialAward specialAward) {
        this.specialAward = specialAward;
    }

    public int getMaxObstacleCount() {
        return maxObstacleCount;
    }

    public void setMaxObstacleCount(int maxObstacleCount) {
        this.maxObstacleCount = maxObstacleCount;
    }

}
