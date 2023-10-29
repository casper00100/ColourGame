import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class Level extends Rectangle {

    //declare
    static int GAME_WIDTH;
	static int GAME_HEIGHT;
    int level_sequence; //1 t/m 10
    int max_level = 10;

    //constructor
    Level(int GAME_WIDTH, int GAME_HEIGHT) {

        Level.GAME_WIDTH = GAME_WIDTH;
        Level.GAME_HEIGHT = GAME_HEIGHT;
        
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas", Font.PLAIN,45));

        g.drawString("Level:", 600, 100);
        g.drawString(String.valueOf(level_sequence) + " / " + String.valueOf(max_level), 600, 200);
    }

}
