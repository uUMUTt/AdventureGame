package maps;

import assets.Player;
import assets.Bear;

public class River extends NormalEnemyLocation {

    public River(Player player) {
        super(player, "RIVER", "Water", true, 1, 3);//Player player, String name, int minEnemy, int maxEnemy
    }

    ////Creates an Bear array based on a random number generated at specified intervals
    @Override
    public void createEnemy() {
        this.setEnemyNumber(this.createRandomNum());
        this.setEnemy(new Bear[this.getEnemyNumber()]);
        for (int i = 0; i < this.getEnemyNumber(); i++) {
            this.getEnemy()[i] = new Bear();
        }
    }

    @Override
    public void collectBossPrize() {
        this.getPlayer().setWater(true);
    }

}
