package assets;

import maps.Location;

public abstract class Player extends Character {

    private int coin;
    private boolean firewood = false;
    private boolean water = false;
    private boolean food = false;
    private Weapon weapon;
    private Armor armor;
    private int constantHealt;
    private int block;

    public Player(int coin, int constantHealt, int id, int damage, int healt, String name) {
        super(id, damage, healt, name);
        this.coin = coin;
        this.constantHealt = constantHealt;
    }
    //Player attacks the enemy.
    public void attack(Enemy enemy) {
        enemy.setHealt(enemy.getHealt() - this.getDamage());
        if (enemy.getHealt() < 0) {//Enemy's healt  is not allowed to go below 0
            enemy.setHealt(0);
        }
    }
    //The player goes to the specified location
    public void go(Location location) {
        location.run();
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public boolean isFirewood() {
        return firewood;
    }

    public void setFirewood(boolean firewood) {
        this.firewood = firewood;
    }

    public boolean isWater() {
        return water;
    }

    public void setWater(boolean water) {
        this.water = water;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public int getConstantHealt() {
        return constantHealt;
    }

    public void setConstantHealt(int constantHealt) {
        this.constantHealt = constantHealt;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

}
