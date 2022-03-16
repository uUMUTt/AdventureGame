package assets;

public abstract class Enemy extends Character {

    private int prizeCoin;
    private int damage;

    public Enemy(int prizeCoin, int id, int damage, int healt, String name) {
        super(id, damage, healt, name);
        this.prizeCoin = prizeCoin;
    }
    //the enemy attacks the player
    public void attack(Player player) {
        damage = this.getDamage() - player.getBlock();//If the player has armor, the enemy's damage is blocked
        if (damage < 0) {//Damage value is not allowed to go below 0
            damage = 0;
        }
        player.setHealt(player.getHealt() - damage);
        if (player.getHealt() < 0) {//Player's healt  is not allowed to go below 0
            player.setHealt(0);
        }
    }

    public int getPrizeCoin() {
        return prizeCoin;
    }

    public void setPrizeCoin(int prizeCoin) {
        this.prizeCoin = prizeCoin;
    }

}
