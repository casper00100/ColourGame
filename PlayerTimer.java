import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class PlayerTimer extends Rectangle {

    // Declaration
    static int GAME_WIDTH;
    static int GAME_HEIGHT;

    private boolean replicating = true;  // Mode to decide which timer to use
    private int time_left = 5;            // Default time for replicating mode
    // private int time_observe = 10;        // Default time for observation mode
    private double countdown;

    // Constructor used to initialize dimensions
    public PlayerTimer(int GAME_WIDTH, int GAME_HEIGHT) {   
        PlayerTimer.GAME_WIDTH = GAME_WIDTH;
        PlayerTimer.GAME_HEIGHT = GAME_HEIGHT;
    }

    // Constructor used to set time left directly
    public PlayerTimer(double countdown) {
        this.countdown = countdown; 
        
    }

    public void update() {
        // Implement countdown logic here
        countdown = countdown - 1;

        if (countdown < 0) {
            countdown = 0;
        }
    }

    // Draw method to display the timer on screen
    public void draw(Graphics g) {

        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas", Font.PLAIN, 45));
        g.drawString("Time left:", 1100, 100);

        // if (replicating) {
        g.drawString(String.valueOf(countdown), 1100, 200);
        // }               



        


        // } else {
        //     g.drawString(String.valueOf(time_observe), 1100, 200);
        // }



    }

    public double getCountdown() {
        return countdown;
    }

    // Getter and Setter methods for replicating if needed elsewhere
    public boolean isReplicating() {
        return replicating;
    }

    public void setReplicating(boolean replicating) {
        this.replicating = replicating;
    }
}

