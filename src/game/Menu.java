package game;

import assets.Archer;
import assets.Knight;
import assets.Player;
import assets.Samurai;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import maps.Cave;
import maps.Forest;
import maps.Mine;
import maps.NormalEnemyLocation;
import maps.River;
import maps.SafeHouse;
import maps.Store;

public class Menu {

    public static Player player;
    public static boolean ctrl;//to check the game to continue
    private static Cave cave;
    private static Forest forest;
    private static River river;
    private static Store store;
    private static SafeHouse safeHouse;
    private static Mine mine;
    /*
    The menu is the class that changes the flow of the game according to the decisions made by the player.
    With the Thread.sleep() commands, the selections are made to appear on the terminal for a while.
    */
    public static void run() {
        Scanner inp = new Scanner(System.in);
        System.out.println("========ADVENTURE GAME========");
        System.out.println("==Warriors==");
        System.out.println("(1)Samurai > Healt:21 || Damage:5 || Starting Coin: 15");
        System.out.println("(2)Archer > Healt:18 || Damage:7 || Starting Coin: 20");
        System.out.println("(3)Knight > Healt:24 || Damage:8 || Starting Coin: 5");

        int selection;
        do {
            ctrl = false;
            System.out.print("Choose your warrior(1,2,3) : ");
            selection = inp.nextInt();
            if (selection == 1) {
                player = new Samurai();
            } else if (selection == 2) {
                player = new Archer();
            } else if (selection == 3) {
                player = new Knight();
            } else {
                System.out.println("You entered the wrong value!!!");
                ctrl = true;
            }
        } while (ctrl);
        ctrl = true;
        System.out.println("===================================================================");
        System.out.println("Your Warrior : " + player.getName());
        System.out.println("\nNow the game has started."
                + "\nWhat you need to do now is to collect the loot by defeating the enemies in the enemy areas."
                + "\nYou will win the game if you collect the boss rewards(food, firewood and water) on all maps."
                + "\nYou can increase your inventory on the mine map"
                + "\nYou can go to the safe house to renew your healt."
                + "\nDon't forget to stop by the store to beat stronger enemies."
                + "\nLOADING...");
        System.out.println("====================================================================");
        try {
            Thread.sleep(6000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        Menu.createMap();
        do {

            if (ctrl) {
                System.out.println("======MAPS======");
                System.out.println("(1)Cave > Enemy:Zombie(1-3) || Boss Reward:Food");
                System.out.println("(2)Forest > Enemy:Vampire(1-3) || Boss Reward:Firewood");
                System.out.println("(3)River > Enemy:Bear(1-3) || Boss Reward:Water");
                System.out.println("(4)*Mine* > Enemy:Snake(1-5) || Rewards:Weapon-Armor-Coins");
                System.out.println("========SAFE ZONE========");
                System.out.println("(5)Safe House > Renew your healt");
                System.out.println("(6)Store > Buy weapons and armor");
                System.out.println("===========================================");
                System.out.println("(7)Show Inventory");
                System.out.println("(8)Restart the game");
                System.out.println("(9)Leave the game");
                System.out.println("===========================================");
                System.out.print("Make your choice according to the numbers in the menu : ");
                selection = inp.nextInt();

                switch (selection) {
                    case 1:
                        Menu.goEnemyMap(cave);
                        break;
                    case 2:
                        Menu.goEnemyMap(forest);
                        break;
                    case 3:
                        Menu.goEnemyMap(river);
                        break;
                    case 4:
                        Menu.player.go(mine);
                        break;
                    case 5:
                        player.go(safeHouse);
                        ctrl = Menu.isWin();
                        break;
                    case 6:
                        player.go(store);
                        break;
                    case 7://shows the player's inventory
                        System.out.println("======INVENTORY======");
                        if (player.isFirewood()) {
                            System.out.println("Firewood = 1");
                        } else {
                            System.out.println("Firewood = 0");
                        }
                        if (player.isFood()) {
                            System.out.println("Food = 1");
                        } else {
                            System.out.println("Food = 0");
                        }
                        if (player.isWater()) {
                            System.out.println("Water = 1");
                        } else {
                            System.out.println("Water = 0");
                        }
                        System.out.println("=====================");
                        System.out.println("Coins = " + player.getCoin());
                        System.out.println("=====Weapons=====");
                        if (player.getWeapon() != null) {
                            System.out.println("(+)Weapon : " + player.getWeapon().getName());
                        } else {
                            System.out.println("(+)Weapon : - ");
                        }
                        System.out.println("=====ARMORS=====");
                        if (player.getArmor() != null) {
                            System.out.println("(+)Armor : " + player.getArmor().getName());
                        } else {
                            System.out.println("(+)Armor : - ");
                        }
                        System.out.println("==================");
                         {
                            try {
                                Thread.sleep(1200);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;

                    case 8:
                        run();
                        break;
                    case 9:
                        ctrl = false;
                        break;
                }

                try {
                    Thread.sleep(1300);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } while (ctrl);

    }
    //The player wins the game if they collect all the items and return to the safe house.
    public static boolean isWin() {
        if (Menu.player.isFirewood() && Menu.player.isFood() && Menu.player.isWater()) {
            System.out.println("**************************************************");
            System.out.println("**** You have successfully completed the game ****");
            System.out.println("**************************************************");
            return false;
        }
        return true;
    }
    //Maps to go are created
    public static void createMap() {
        cave = new Cave(Menu.player);
        forest = new Forest(Menu.player);
        river = new River(Menu.player);
        safeHouse = new SafeHouse(Menu.player);
        store = new Store(Menu.player);
        mine = new Mine(Menu.player);
    }
    //Enemy areas with all collected items cannot be re-entered
    public static void goEnemyMap(NormalEnemyLocation enemyPlace) {
        if (enemyPlace.isBossPrize()) {
            Menu.player.go(enemyPlace);
        } else {
            System.out.println("!!!!You have completed the " + enemyPlace.getName() + " map!!!!");
        }

    }
}
