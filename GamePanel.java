import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    //declare things
    static final int GAME_WIDTH = 1400;
    static final int GAME_HEIGHT = 600;
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BUTTON_SIZE = 100;
    static final int MAX_BUTTONS = 12;
    static final int DELAY = 75; //higher -> slower game
    final int x[] = new int[MAX_BUTTONS]; //x coordinate of button
    int minButtons = 2;
    int rightColour;
    boolean replicating = false; //whether the Mode is observing or replicating
    //maybe 2 seperate arrays for sequence
    int randomX[] = new int[MAX_BUTTONS];
    int color1;

    Image image;
	Graphics graphics;

    Timer timer;
	Random random;

    PlayerTurn playerTurn;
    Score score;
    Level level;
    ColourSequence colourSequence;
    Mode mode;



    //constructor
    GamePanel() {

        random = new Random();
        this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        mode();

    }

    public void mode() {

        //TODO some if statement
        //if player clicks on the button start
        startGame();

    }

    public void startGame() {

        //call things
        playerTurn = new PlayerTurn(GAME_WIDTH, GAME_HEIGHT);

        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        level = new Level(GAME_WIDTH, GAME_HEIGHT);
        mode = new Mode(GAME_WIDTH, GAME_HEIGHT);
        
        colourSequence = new ColourSequence();
        this.setFocusable(true);
        // this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);

        //TODO timer countdown from 5 sec
        timer = new Timer(GAME_WIDTH, GAME_HEIGHT);

    }

    //drawing and painting stuff on screen
    public void paintComponent(Graphics g) {

        image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image,0,0,this);        
        
        super.paintComponent(g);
        draw(g);

    }

    public void draw(Graphics g) {

        score.draw(g);
        timer.draw(g);
        playerTurn.draw(g);
        level.draw(g);
        mode.draw(g);

        //lijn om panel duidelijker te maken
        g.drawLine(0, 350, GAME_WIDTH, 350);

    }
    public void gameOver(Graphics g) {

    }

    //parts of panel
    public void newColourSequence() {
        color1 = random.nextInt(GAME_WIDTH);
    }

    public void newLevel() {

    }

    public void newPlayerTurn() {

    }

    public void newScore() {

    }

    public void newTimer() {

    }

    //other stuff
    public void changeColour() {

    }

    public void checkColour() {

    }

    //button pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
    }

}