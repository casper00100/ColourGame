import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class Score extends Rectangle {
    
    //declare
    static int GAME_WIDTH;
	static int GAME_HEIGHT;
    int score_player1;
    int score_player2;
    //TODO name input code
    HomeFrame homeFrame = new HomeFrame();
    String nameplayer1 = homeFrame.name_player1;
    String nameplayer2 = homeFrame.name_player2;

    private String name_player1;
    private String name_player2;

    //constructor
    Score(int GAME_WIDTH, int GAME_HEIGHT) {

        Score.GAME_WIDTH = GAME_WIDTH;
        Score.GAME_HEIGHT = GAME_HEIGHT;

    }

    // PlayerTimer timeScore = new PlayerTimer();

    public void calculateScore() {
        // timeScore.getCountdown();
    }

    public void draw(Graphics g) {
        
        if (nameplayer1.equals("")) {
            nameplayer1 = "player 1";
        }
        if (nameplayer2.equals("")) {
            nameplayer2 = "player 2";
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas", Font.PLAIN,45));

        g.drawString("Score", 0, 100);
        g.drawString(nameplayer1 + ": " + String.valueOf(score_player1), 0, 200);
        g.drawString(nameplayer2 + ": " + String.valueOf(score_player2), 0, 300);
        
    }

}