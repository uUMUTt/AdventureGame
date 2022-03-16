package maps;

import assets.Player;
import game.Menu;

public abstract class NormalEnemyLocation extends EnemyLocation {

    private String bossPrizeName;
    private boolean bossPrize;

    public NormalEnemyLocation(Player player, String name, String bossPrizeName, boolean bossPrize, int minEnemy, int maxEnemy) {
        super(player, name, minEnemy, maxEnemy);
        this.bossPrizeName = bossPrizeName;
        this.bossPrize = bossPrize;
    }

    @Override
    public void run() {
        this.createEnemy();//New enemies are created in the enemy array
        this.setEnemyName(this.getEnemy()[0].getName());//to print the enemy's name on the terminal
        System.out.println("======" + this.getName() + "======");
        System.out.println("Map Reward : " + this.getBossPrizeName() + " + Coins");
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
                    //If the Player kills the enemy, Player wins coins.
                    this.getPlayer().setCoin(this.getPlayer().getCoin() + this.getEnemy()[i].getPrizeCoin());
                    System.out.println("You killed the " + this.getEnemyName());
                    System.out.println("+" + this.getEnemy()[i].getPrizeCoin() + " coins >> " + this.getPlayer().getCoin() + " coins");
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

        if (this.getSelection() != 2 && ctrl) {//If the player has killed all the enemies, player gets the boss reward.
            System.out.println("You won a " + this.getBossPrizeName() + " by killing all the enemies");
            this.collectBossPrize();
            this.setBossPrize(false);

        }
        System.out.println("===========================================\n");
    }

    public abstract void collectBossPrize();//Adds the end of chapter reward to the player's inventory

    public String getBossPrizeName() {
        return bossPrizeName;
    }

    public void setBossPrizeName(String bossPrizeName) {
        this.bossPrizeName = bossPrizeName;
    }

    public boolean isBossPrize() {
        return bossPrize;
    }

    public void setBossPrize(boolean bossPrize) {
        this.bossPrize = bossPrize;
    }

}
