package maps;

import assets.Player;
import java.util.Scanner;

public abstract class Location {

    private Player player;
    private int selection;
    Scanner inp = new Scanner(System.in);

    public Location(Player player) {
        this.player = player;
    }

    public abstract void run();

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getSelection() {
        return selection;
    }

    public void setSelection(int selection) {
        this.selection = selection;
    }

}
