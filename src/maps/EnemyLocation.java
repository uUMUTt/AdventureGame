package maps;

import assets.Enemy;
import assets.Player;

public abstract class EnemyLocation extends Location {

    private Enemy[] enemy;
    private int enemyNumber;
    private String name;
    private int minEnemy;
    private int maxEnemy;

    private String enemyName;
    private int firstAttackNum;

    public EnemyLocation(Player player, String name, int minEnemy, int maxEnemy) {
        super(player);
        this.name = name;
        this.minEnemy = minEnemy;
        this.maxEnemy = maxEnemy;
    }

    public boolean fight(Player player, int i) {
        if (this.getFirstAttackNum() == 2) {
            this.askAttack(i);
            if (this.getSelection() == 1) {
                this.getPlayer().attack(this.getEnemy()[i]);
                System.out.println("You >attack> " + this.getEnemyName());
                System.out.println("<< " + this.enemyName + " Healt : " + this.getEnemy()[i].getHealt() + " >>");
                System.out.println("<< " + this.enemyName + " Damage : " + this.getEnemy()[i].getDamage() + " >>");
                if (this.getEnemy()[i].getHealt() > 0) {
                    this.getEnemy()[i].attack(this.getPlayer());
                    System.out.println(this.getEnemyName() + " >attack> You");
                }
            } else {
                return false;
            }

        } else {
            this.getEnemy()[i].attack(this.getPlayer());
            System.out.println(this.getEnemyName() + " >attack> You");
            System.out.println("[Your Healt : " + this.getPlayer().getHealt() + "]");
            if (this.getPlayer().getHealt() > 0) {
                this.askAttack(i);
                if (this.getSelection() == 1) {
                    this.getPlayer().attack(this.getEnemy()[i]);
                    System.out.println("You >attack> " + this.getEnemyName());
                    System.out.println("<< " + this.enemyName + " Healt : " + this.getEnemy()[i].getHealt() + " >>");
                    System.out.println("<< " + this.enemyName + " Damage : " + this.getEnemy()[i].getDamage() + " >>");
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean firstAttack(Player player, Enemy enemy, int i) {//50% chance of who will attack first
        this.firstAttackNum = (int) (Math.random() * 2) + 1;//%50(1 or 2)
        System.out.println("<=============================>");
        System.out.println("You've encountered a " + enemyName + ". Will you fight it?");
        System.out.println("[Your Healt : " + this.getPlayer().getHealt() + "]");
        System.out.println("[Your Damage : " + this.getPlayer().getDamage() + "]");
        System.out.println("<< " + this.enemyName + " Healt : " + this.getEnemy()[i].getHealt() + " >>");
        System.out.println("<< " + this.enemyName + " Damage : " + this.getEnemy()[i].getDamage() + " >>");
        System.out.println("Press '1' to fight");
        System.out.println("Press '2' to flee");
        System.out.print(">> ");
        this.setSelection(inp.nextInt());
        System.out.println("\n<============================>");
        if (this.getSelection() == 1) {
            if (this.firstAttackNum == 1) {
                this.getPlayer().attack(this.getEnemy()[i]);
                System.out.println("You > first attack> " + enemyName);
            } else {
                this.getEnemy()[i].attack(this.getPlayer());
                System.out.println(enemyName + " > first attack > You");
            }
            return true;
        }
        return false;
    }

    public void askAttack(int i) {
        System.out.println("=============================");
        System.out.println("[Your Healt : " + this.getPlayer().getHealt() + "]");
        System.out.println("[Your Damage : " + this.getPlayer().getDamage() + "]");
        System.out.println("Will you keep fighting??");
        System.out.println("<< " + this.enemyName + " Healt : " + this.getEnemy()[i].getHealt() + " >>");
        System.out.println("Press '1' to fight");
        System.out.println("Press '2' to flee");
        System.out.print(">> ");
        this.setSelection(inp.nextInt());
        System.out.println("\n============================");
    }

    public abstract void createEnemy();

    public Enemy[] getEnemy() {
        return enemy;
    }

    public int createRandomNum() {
        int range;
        range = this.getMaxEnemy() - this.getMinEnemy() + 1;
        return (int) (Math.random() * range) + this.getMinEnemy();
    }

    public void setEnemy(Enemy[] enemy) {
        this.enemy = enemy;
    }

    public int getEnemyNumber() {
        return enemyNumber;
    }

    public void setEnemyNumber(int enemyNumber) {
        this.enemyNumber = enemyNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnemyName() {
        return enemyName;
    }

    public void setEnemyName(String enemyName) {
        this.enemyName = enemyName;
    }

    public int getFirstAttackNum() {
        return firstAttackNum;
    }

    public void setFirstAttackNum(int firstAttackNum) {
        this.firstAttackNum = firstAttackNum;
    }

    public int getMinEnemy() {
        return minEnemy;
    }

    public void setMinEnemy(int minEnemy) {
        this.minEnemy = minEnemy;
    }

    public int getMaxEnemy() {
        return maxEnemy;
    }

    public void setMaxEnemy(int maxEnemy) {
        this.maxEnemy = maxEnemy;
    }

}
