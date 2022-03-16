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
import java.util.logging.Level;
import java.util.logging.Logger;

public class Store extends Location {

    private final Weapon weapons[];
    private final Armor armors[];

    public Store(Player player) {
        super(player);
        weapons = new Weapon[3];
        armors = new Armor[3];

    }
    //New items are created each time the store object is created
    public void createWeapons() {
        weapons[0] = new Pistol();
        weapons[1] = new Sword();
        weapons[2] = new Rifle();

    }

    public void createArmors() {
        armors[0] = new LightArmor();
        armors[1] = new NormalArmor();
        armors[2] = new HeavyArmor();
    }

    @Override
    public void run() {
        boolean ctrl = true;
        this.createWeapons();
        this.createArmors();
        do {
            System.out.println("=================STORE===================");
            this.showInventory();//Player's inventory
            System.out.println("==WEAPONS================================");
            System.out.println("(1)Pistol || Damage : 2 >> 25 Coins");
            System.out.println("(2)Sword || Damage : 3 >> 35 Coins");
            System.out.println("(3)Rifle || Damage : 7 >> 45 Coins");
            System.out.println("==ARMORS=================================");
            System.out.println("(4)Light Armor || -1 counterattack >> 15 Coins");
            System.out.println("(5)Normal Armor || -3 counterattack >> 25 Coins");
            System.out.println("(6)Heavy Armor || -5 counterattack >> 40 Coins");
            System.out.println("=========================================");
            System.out.println("(7)Exit");
            System.out.print("Make your choice according to the numbers in the menu : ");
            this.setSelection(inp.nextInt());
            if (this.getSelection() > 0 && this.getSelection() < 7) {
                for (Weapon weapon : weapons) {//Selection is made according to the id of the weapons.
                    if (weapon.getId() == this.getSelection()) {
                        if (this.getPlayer().getCoin() >= weapon.getPrice()) {
                            this.getPlayer().setWeapon(weapon);
                            this.getPlayer().setDamage(this.getPlayer().getDamage() + this.getPlayer().getWeapon().getDamage());
                            this.getPlayer().setCoin(this.getPlayer().getCoin() - weapon.getPrice());
                            System.out.println("You bought a " + weapon.getName());
                        } else {
                            //If the player can't buy the weapon, it shows how much coin they need.
                            System.out.println("You need " + (weapon.getPrice() - this.getPlayer().getCoin()) + " more coins to buy!!!");
                        }
                    }
                }
                for (Armor armor : armors) {//Selection is made according to the id of the armors.
                    if (armor.getId() == this.getSelection()) {
                        if (this.getPlayer().getCoin() >= armor.getPrice()) {
                            this.getPlayer().setArmor(armor);
                            this.getPlayer().setBlock(armor.getProtectionLevel());
                            this.getPlayer().setCoin(this.getPlayer().getCoin() - armor.getPrice());
                            System.out.println("You bought a " + armor.getName());
                        } else {
                            //If the player can't buy the armor, it shows how much money they need.
                            System.out.println("You need " + (armor.getPrice() - this.getPlayer().getCoin()) + " more coins to buy!!!");
                        }
                    }
                }
            } else if (this.getSelection() == 7) {
                ctrl = false;
            } else {
                System.out.println("Wrong Value!!!");
            }

            try {
                Thread.sleep(1100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Store.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (ctrl);
    }

    public void showInventory() {
        System.out.println("Your Coins : " + this.getPlayer().getCoin() + " coins");
        String weapon = "-", armor = "-";
        if (this.getPlayer().getWeapon() != null) {
            weapon = this.getPlayer().getWeapon().getName();
        }
        if (this.getPlayer().getArmor() != null) {
            armor = this.getPlayer().getArmor().getName();
        }
        System.out.println("Your Weapon : " + weapon);
        System.out.println("Your Armor : " + armor);
    }

}
