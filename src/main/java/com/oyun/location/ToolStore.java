package com.oyun.location;

import com.oyun.item.Armor;
import com.oyun.location.abstracs.Location;
import com.oyun.model.Player;
import com.oyun.item.Weapon;
import com.oyun.location.abstracs.NormalLocation;

public class ToolStore extends NormalLocation {
    public ToolStore(Player player) {
        super(player, "Tool Store", 2, "You can buy item here!");
    }

    @Override
    public boolean onLocation() {
        System.out.println("Welcom to Tool Store");
        boolean showMenu = true;
        while (showMenu) {
            System.out.println("1: weapons");
            System.out.println("2: armors");
            System.out.println("3: exit");
            System.out.print("Please select the operation you want to make: ");
            int selectedCase = Location.input.nextInt();
            while (selectedCase < 1 || selectedCase > 3) {
                System.out.println("you made the wrong chose,please enter a value between 1 and 3");
                selectedCase = Location.input.nextInt();
            }
            switch (selectedCase) {
                case 1:
                    printWeapons();
                    break;
                case 2:
                    printArmors();
                    break;
                case 3:
                    System.out.println("You wanted to exit");
                    showMenu = false;
                    return true;
            }
        }
        return true;
    }

    public void printWeapons() {
        System.out.println("Your money: " + getPlayer().getMoney());
        System.out.println("--------------");
        System.out.println("Weapons");
        System.out.println("--------------------------------------------");
        for (Weapon weapon : Weapon.weapons()) {
            System.out.println("ID: " + weapon.getId()
                    + " Name: " + weapon.getName()
                    + " Damage: " + weapon.getDamage()
                    + " Price: " + weapon.getPrice()
            );
        }
        System.out.println("4 : Exit");
        buyWeapon();
    }

    public void buyWeapon() {
        System.out.print("Which weapon do you want to buy?");
        int selectedWeaponId = input.nextInt();

        while (selectedWeaponId < 1 || selectedWeaponId > Weapon.weapons().length + 1) {
            System.out.print("you made the wrong chose,please enter a value between 1 and " + (Weapon.weapons().length + 1) + ": ");
            selectedWeaponId = Location.input.nextInt();
        }
        if (selectedWeaponId != 4) {
            Weapon selectedWeapon = Weapon.getWeaponObjeById(selectedWeaponId);

            if (selectedWeapon != null) {
                if (selectedWeapon.getPrice() > getPlayer().getMoney()) {
                    System.out.println("You have not enough money to buy " + selectedWeapon.getName() + " weapon!!");
                    printWeapons();
                } else {
                    System.out.println("You bought this weapon " + selectedWeapon.getName());
                    this.getPlayer().setMoney(getPlayer().getMoney() - selectedWeapon.getPrice());
                    System.out.println("You have " + this.getPlayer().getMoney() + " money");
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                }
            }
        }
    }

    public void printArmors() {
        System.out.println("Your money: " + getPlayer().getMoney());
        System.out.println("--------------");
        System.out.println("Armors");
        System.out.println("--------------------------------------------");
        for (Armor armor : Armor.armors()) {
            System.out.println("ID: " + armor.getId()
                    + " Name: " + armor.getName()
                    + " Block: " + armor.getBlock()
                    + " Price: " + armor.getPrice()
            );
        }
        System.out.println("4 : Exit");
        buyArmor();
    }

    public void buyArmor() {
        System.out.print("Which armor do you want to buy?");
        int selectedArmorId = input.nextInt();

        while (selectedArmorId < 1 || selectedArmorId > Weapon.weapons().length + 1) {
            System.out.print("you made the wrong chose,please enter a value between 1 and " + (Armor.armors().length + 1) + ": ");
            selectedArmorId = Location.input.nextInt();
        }
        if (selectedArmorId != 4) {
            Armor selectedArmor = Armor.getArmorObjeById(selectedArmorId);

            if (selectedArmor != null) {
                if (selectedArmor.getPrice() > getPlayer().getMoney()) {
                    System.out.println("You have not enough money to buy " + selectedArmor.getName() + " armor!!");
                    printArmors();
                } else {
                    System.out.println("You bought this armor " + selectedArmor.getName());
                    this.getPlayer().setMoney(getPlayer().getMoney() - selectedArmor.getPrice());
                    System.out.println("You have " + this.getPlayer().getMoney() + " money");
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                }
            }
        }
    }
}
