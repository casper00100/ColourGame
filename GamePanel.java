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
    PlayerTimer timer; //PlayerTimer is used on screen
    //java.swing.Timer is used for computer showing colours with a delay
    private javax.swing.Timer sequenceTimer; 
	Random random;

    PlayerTurn playerTurn;
    Score score;
    Level level;
    Mode mode;

    private int currentSequenceIndex = 0;
    private ColourSequence colourSequence;
    

    // Make a reference to ButtonPanel, used in displaySequence()
    private ButtonPanel buttonPanelRef;  

    //constructor
    public GamePanel(ButtonPanel panelRef) {

        this.buttonPanelRef = panelRef;
        
        random = new Random();
        this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        startGame();

    }

    public void startGame() {

        //call things
        playerTurn = new PlayerTurn(GAME_WIDTH, GAME_HEIGHT);

        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        level = new Level(GAME_WIDTH, GAME_HEIGHT);
        mode = new Mode(GAME_WIDTH, GAME_HEIGHT);
        
        colourSequence = new ColourSequence();

        //TODO timer countdown from 10 sec
        timer = new PlayerTimer(10);

        colourSequence = new ColourSequence();
        displaySequence();
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


    //Display the colour sequence by the computer
    private void displaySequence() {
        currentSequenceIndex = 0; // Reset the index

        // Using javax.swing.Timer for the sequence display
        sequenceTimer = new javax.swing.Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentSequenceIndex < colourSequence.getSequence().length) {
                    // Hide all buttons before showing the next color
                    for (JButton btn : buttonPanelRef.getColorButtons()) {
                        btn.setVisible(false);
                    }
    
                    // Fetch the current color in sequence
                    Color currentColor = colourSequence.getSequence()[currentSequenceIndex];
    
                    // Find the button with the matching color and display it
                    for (JButton btn : buttonPanelRef.getColorButtons()) {
                        if (btn.getBackground().equals(currentColor)) {
                            btn.setVisible(true);
                            break; // Exit the loop once the button is found
                        }
                    }
    
                    currentSequenceIndex++; // Move to the next color in the sequence
                } else {
                    // Stop the timer when we've shown the entire sequence
                    sequenceTimer.stop();
                    // Show all buttons after the sequence is displayed
                    for (JButton btn : buttonPanelRef.getColorButtons()) {
                        btn.setVisible(true);
                    }
                }
            }
        });
    
        sequenceTimer.start();
    }
    

    
    //button pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
    }

}