package maps;

import assets.HeavyArmor;
import assets.LightArmor;
import assets.NormalArmor;
import assets.Pistol;
import assets.Player;
import assets.Rifle;
import assets.Snake;
import assets.Sword;

public class Mine extends BonusEnemyLocation {

    public Mine(Player player) {
        super(player, "MINE", 1, 5);//Player player, String name, int minEnemy, int maxEnemy
    }

    //Randomly generated numbers between 1 and 100 determine the enemy's item drop probability.
    @Override
    public void randomAward() {
        int firstPossibility, secondPossibilty;
        firstPossibility = (int) (Math.random() * 100) + 1;//weapon or armor or coins or empty
        secondPossibilty = (int) (Math.random() * 100) + 1;//determines which item or how much coin will drop
        if (firstPossibility < 16) {//%15 Weapon
            if (secondPossibilty < 21) {//%20 Rifle
                this.collectWeapon(new Rifle());
            } else if (secondPossibilty < 51) {//%30 Sword
                this.collectWeapon(new Sword());
            } else {//%50 Pistol
                this.collectWeapon(new Pistol());
            }

        } else if (firstPossibility < 31) {//%15 Armor
            if (secondPossibilty < 21) {//%20 Heavy Armor
                this.collectArmor(new HeavyArmor());
            } else if (secondPossibilty < 51) {//%30 Normal Armor
                this.collectArmor(new NormalArmor());
            } else {//%50 Light Armor
                this.collectArmor(new LightArmor());
            }
        } else if (firstPossibility < 56) {//%25 Coins
            if (secondPossibilty < 21) {//%20 10 Coins
                this.getPlayer().setCoin(this.getPlayer().getCoin() + 10);
                System.out.println("+ 10 Coins >> " + this.getPlayer().getCoin() + " Coins");
            } else if (secondPossibilty < 51) {//%30 5 Coins
                this.getPlayer().setCoin(this.getPlayer().getCoin() + 5);
                System.out.println("+ 5 Coins >> " + this.getPlayer().getCoin() + " Coins");
            } else {//%50 1 Coin
                this.getPlayer().setCoin(this.getPlayer().getCoin() + 1);
                System.out.println("+ 1 Coin >> " + this.getPlayer().getCoin() + " Coins");
            }
        } else {//%45 empty
            System.out.println(":( The item did not drop in the enemy :(");
        }

    }

    @Override
    public void createEnemy() {
        this.setEnemyNumber(this.createRandomNum());
        this.setEnemy(new Snake[this.getEnemyNumber()]);
        for (int i = 0; i < this.getEnemyNumber(); i++) {
            this.getEnemy()[i] = new Snake();
            this.getEnemy()[i].setDamage(randomDamage());
        }
    }

    public int randomDamage() {
        int minDamage = 4, maxDamage = 5, range;
        range = maxDamage - minDamage + 1;
        return (int) (Math.random() * range) + 4;
    }

}
