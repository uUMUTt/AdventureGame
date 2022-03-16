package maps;

import assets.Player;
import assets.Vampire;

public class Forest extends NormalEnemyLocation {

    public Forest(Player player) {
        super(player, "FOREST", "Firewood", true, 1, 3);//Player player, String name, int minEnemy, int maxEnemy
    }

    ////Creates an Vampire array based on a random number generated at specified intervals
    @Override
    public void createEnemy() {
        this.setEnemyNumber(this.createRandomNum());
        this.setEnemy(new Vampire[this.getEnemyNumber()]);
        for (int i = 0; i < this.getEnemyNumber(); i++) {
            this.getEnemy()[i] = new Vampire();
        }
    }

    @Override
    public void collectBossPrize() {
        this.getPlayer().setFirewood(true);
    }

}
