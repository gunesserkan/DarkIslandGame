package com.oyun;

import com.oyun.location.*;
import com.oyun.location.abstracs.Location;
import com.oyun.model.Player;

import java.util.Scanner;

public class Game {
    private Scanner input = new Scanner(System.in);
    private Location location = null;

    public void start() {
        System.out.println("###################################################################3");
        System.out.println("Welcome to The Advanture Game");
        System.out.print("Please enter a name: ");
        String playerName = input.nextLine();
        Player player = new Player(playerName);
        System.out.println(player.getName().toUpperCase() + " Welcome to the Dark Island");
        System.out.println("----------------------------------");
        player.selectCharacter();
        Location[] locations = {new SafeHouse(player), new ToolStore(player), new Cave(player), new Forest(player), new River(player), new Mine(player)};
        while (true) {
            player.printInfo();
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("------------------------Areas--------------------------------------------");
            for (Location selectableLocation : locations) {
                System.out.println("ID: " + selectableLocation.getId()
                        + " Name: " + selectableLocation.getName()
                        + " Description: " + selectableLocation.getDescription()
                );
            }
            System.out.println((locations.length + 1) + " Exit");
            System.out.print("Where do you want to go?: ");
            int selectedLocation = input.nextInt();
            switch (selectedLocation) {
                case 1:
                    location = locations[0];
                    break;
                case 2:
                    location = locations[1];
                    break;
                case 3:
                    location = locations[2];
                    break;
                case 4:
                    location = locations[3];
                    break;
                case 5:
                    location = locations[4];
                    break;
                case 6:
                    location = locations[5];
                    break;
                case 7:
                    location = null;
                    break;
                default:
                    System.out.println("Please slect a correct place...");
            }
            if (location == null) {
                System.out.println("There is no place for weaks !");
                break;
            } else if (!location.onLocation()) {
                System.out.println("GAME OVER");
                break;
            }
        }
    }
}
