import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class PlayerTurn extends Rectangle {

    //declare
    static int GAME_WIDTH;
	static int GAME_HEIGHT;
    boolean turn_player1;
    // int turn_player2;
    String name_player1 = "player 1";
    // name_player1 = "player 1";
    String name_player2 = "player 2";
    // name_player1 = "player 2";
    Random random;

    //constructor
    PlayerTurn(int GAME_WIDTH, int GAME_HEIGHT) {

        PlayerTurn.GAME_WIDTH = GAME_WIDTH;
        PlayerTurn.GAME_HEIGHT = GAME_HEIGHT;
        
    }

    //weet niet waar dit moet

    public void firstTurn() {

        // if (random.nextInt(2) == 0) {
        //     turn_player1 = true;
        // } else {
        //     turn_player1 = false;
        // }

    }

    public void draw(Graphics g) {

        random = new Random();

        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas", Font.PLAIN,45));
        g.drawString("Turn:", 350, 100);
            
        if (random.nextInt(2) == 0) {
            turn_player1 = true;
            g.drawString(name_player1, 350, 200);
        } else {
            turn_player1 = false;
            g.drawString(name_player2, 350, 200);

        }

    }

}