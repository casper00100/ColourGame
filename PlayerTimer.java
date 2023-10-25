import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class PlayerTimer extends Rectangle {

    // Declaration
    static int GAME_WIDTH;
    static int GAME_HEIGHT;

    private boolean replicating = false;  // Mode to decide which timer to use
    private int time_left = 5;            // Default time for replicating mode
    private int time_observe = 10;        // Default time for observation mode

    // Constructor used to initialize dimensions
    public PlayerTimer(int GAME_WIDTH, int GAME_HEIGHT) {   
        PlayerTimer.GAME_WIDTH = GAME_WIDTH;
        PlayerTimer.GAME_HEIGHT = GAME_HEIGHT;
    }

    // Constructor used to set time left directly
    public PlayerTimer(int timeLeft) {
        this.time_left = timeLeft;
    }

    // Draw method to display the timer on screen
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas", Font.PLAIN, 45));
        g.drawString("Time left:", 1100, 100);

        if (replicating) {
            g.drawString(String.valueOf(time_left), 1100, 200);
        } else {
            g.drawString(String.valueOf(time_observe), 1100, 200);
        }
    }

    // Getter and Setter methods for replicating if needed elsewhere
    public boolean isReplicating() {
        return replicating;
    }

    public void setReplicating(boolean replicating) {
        this.replicating = replicating;
    }
}
