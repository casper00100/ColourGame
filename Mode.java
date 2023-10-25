import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class Mode extends Rectangle {

    //declare
    static int GAME_WIDTH;
	static int GAME_HEIGHT;

    //TODO distinguish between observing and replicating
    boolean replicating = false;
    
    //constructor
    Mode(int GAME_WIDTH, int GAME_HEIGHT) {

        Mode.GAME_WIDTH = GAME_WIDTH;
        Mode.GAME_HEIGHT = GAME_HEIGHT;

    }

    public void draw(Graphics g) {

        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas", Font.PLAIN,45));

        g.drawString("Game mode:", 800, 100);
        if (replicating == true) {
            g.drawString("replicating", 800, 200);
        }
        if (replicating == false) {
            g.drawString("observing", 800, 200);
        }
    }

}