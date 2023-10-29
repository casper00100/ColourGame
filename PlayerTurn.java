import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.awt.Graphics;


public class PlayerTurn {

    private int xPosition;
    private int yPosition;
    private static final int FONT_SIZE = 25;

    private boolean player1Turn;

    public PlayerTurn(int gameWidth, int gameHeight, boolean player1Turn) {
        this.xPosition = gameWidth / 2 - 70; // Adjusted for approximate centering
        this.yPosition = gameHeight / 4; // Positioned to the top 1/4 of the screen
        this.player1Turn = player1Turn;
    }

    public boolean isPlayer1Turn() {
        return player1Turn;
    }

    public void setPlayer1Turn(boolean player1Turn) {
        this.player1Turn = player1Turn;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        if (player1Turn) {
            g.drawString("Player 1's Turn", xPosition, yPosition);
        } else {
            g.drawString("Player 2's Turn", xPosition, yPosition);
        }
    }
}

