import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class Timer extends Rectangle {

    //declare
    static int GAME_WIDTH;
	static int GAME_HEIGHT;

    //TODO use Mode.java to decide which timer to use
    boolean replicating = false;

    int time_left = 5;
    int time_observe = 10;

    //constructor
    Timer(int GAME_WIDTH, int GAME_HEIGHT) {
        
        Timer.GAME_WIDTH = GAME_WIDTH;
        Timer.GAME_HEIGHT = GAME_HEIGHT;

    }

    public void draw(Graphics g) {

        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas", Font.PLAIN,45));
        g.drawString("Time left:", 1100, 100);

        if (replicating == true) {
            g.drawString(String.valueOf(time_left), 1100, 200);
        }
        if (replicating == false) {
            g.drawString(String.valueOf(time_observe), 1100, 200);
        }

    }

}