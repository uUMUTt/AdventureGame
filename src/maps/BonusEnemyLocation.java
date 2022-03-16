package maps;

import assets.Armor;
import assets.HeavyArmor;
import assets.LightArmor;
import assets.NormalArmor;
import assets.Pistol;
import assets.Player;
import assets.Rifle;
import assets.Sword;
import assets.Weapon;
import game.Menu;

/*
Bonus maps do not have end-of-level rewards.
Items and coins will only drop from dead enemies according to certain probabilities.
 */
public abstract class BonusEnemyLocation extends EnemyLocation {

    public BonusEnemyLocation(Player player, String name, int minEnemy, int maxEnemy) {
        super(player, name, minEnemy, maxEnemy);
    }

    @Override
    public void run() {
        this.createEnemy();//New enemies are created in the enemy array
        this.setEnemyName(this.getEnemy()[0].getName());//to print the enemy's name on the terminal
        System.out.println("======" + this.getName() + "======");
        System.out.println("Number of " + this.getEnemyName() + " >> " + this.getEnemyNumber());
        int i = 0;//to access elements of the enemy array
        boolean ctrl = true;//For control of staying in or fleeing a fight

        do {
            ctrl = this.firstAttack(this.getPlayer(), this.getEnemy()[i], i);
            if (this.getSelection() == 1) {//fight
                while (this.getPlayer().getHealt() > 0 && this.getEnemy()[i].getHealt() > 0 && ctrl) {
                    ctrl = this.fight(this.getPlayer(), i);//Both player and enemy attack once
                }
                System.out.println("==========================================");
                if (this.getEnemy()[i].getHealt() <= 0) {
                    System.out.println("You killed the " + this.getEnemyName());
                    this.randomAward();//generate random reward
                } else if (ctrl) {//if the enemy kills the player
                    System.out.println(this.getEnemyName() + " kill you!!!");
                    System.out.println("===GAME OVER===");
                    ctrl = false;
                    Menu.ctrl = false;//To terminate the program
                }
                i++;//next enemy
            } else if (this.getSelection() == 2) {//flee
                ctrl = false;
            }
        } while (i < this.getEnemyNumber() && ctrl && this.getPlayer().getHealt() > 0);
        //If the player is not dead or flee, the loop continues until all enemies are dead.

        System.out.println("===========================================\n");
    }

    //Randomly generated numbers between 1 and 100 determine the enemy's item drop probability.
    public abstract void randomAward();

    //To determine whether to retrieve Weapon dropped from an enemy
    public void collectWeapon(Weapon weapon) {
        System.out.println("The " + weapon.getName() + " fell from the enemy. Do you want to collect it?");
        System.out.println("<(1)Collect>");
        System.out.println("<(2)Don't Collect>");
        System.out.print(">> ");
        this.setSelection(inp.nextInt());
        if (this.getSelection() == 1) {
            this.getPlayer().setWeapon(weapon);
            this.getPlayer().setDamage(this.getPlayer().getDamage() + weapon.getDamage());
            System.out.println("The " + weapon.getName() + " has been added to your weapon inventory");
        }
    }

    //To determine whether to retrieve Armor dropped from an enemy
    public void collectArmor(Armor armor) {
        System.out.println("The " + armor.getName() + " fell from the enemy. Do you want to collect it?");
        System.out.println("<(1)Collect>");
        System.out.println("<(2)Don't Collect>");
        System.out.print(">> ");
        this.setSelection(inp.nextInt());
        if (this.getSelection() == 1) {
            this.getPlayer().setArmor(armor);
            System.out.println("The " + armor.getName() + " has been added to your armor inventory");
        }
    }

}
