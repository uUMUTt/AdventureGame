package assets;

public abstract class Weapon extends Item {

    private int damage;

    public Weapon(int damage, int id, int price, String name) {
        super(id, price, name);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

}
