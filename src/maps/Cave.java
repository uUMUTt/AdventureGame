package maps;

import assets.Player;
import assets.Zombie;

public class Cave extends NormalEnemyLocation {

    public Cave(Player player) {
        super(player, "CAVE", "Food", true, 1, 3);//Player player, String name, int minEnemy, int maxEnemy
    }

    //Creates an Zombie array based on a random number generated at specified intervals
    @Override
    public void createEnemy() {
        this.setEnemyNumber(this.createRandomNum());
        this.setEnemy(new Zombie[this.getEnemyNumber()]);
        for (int i = 0; i < this.getEnemyNumber(); i++) {
            this.getEnemy()[i] = new Zombie();
        }
    }

    @Override
    public void collectBossPrize() {
        this.getPlayer().setFood(true);
    }

}
