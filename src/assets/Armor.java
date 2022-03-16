package assets;

public abstract class Armor extends Item {

    public Armor(int protectionLevel, int id, int price, String name) {
        super(id, price, name);
        this.protectionLevel = protectionLevel;
    }

    
    private int protectionLevel;

    public int getProtectionLevel() {
        return protectionLevel;
    }

    public void setProtectionLevel(int protectionLevel) {
        this.protectionLevel = protectionLevel;
    }

}
