package assets;

public abstract class Character {

    private int id;
    private int damage;
    private int healt;
    private String name;

    public Character(int id, int damage, int healt, String name) {
        this.id = id;
        this.damage = damage;
        this.healt = healt;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealt() {
        return healt;
    }

    public void setHealt(int healt) {
        this.healt = healt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
