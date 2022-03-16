package maps;

import assets.Player;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SafeHouse extends Location {

    public SafeHouse(Player player) {
        super(player);
    }

    @Override
    //The player's health is regenerated.
    public void run() {
        this.getPlayer().setHealt(this.getPlayer().getConstantHealt());
        System.out.println("****Your healt is Full*****");
        try {
            Thread.sleep(1300);
        } catch (InterruptedException ex) {
            Logger.getLogger(SafeHouse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
