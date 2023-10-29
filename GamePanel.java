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
    private javax.swing.Timer countdownTimer; //Countdown timer initialized


    private int round = 1; // start at round 1
    private boolean waitingForUserInput = false; // track when we're waiting for the user to replicate sequence

    PlayerTurn playerTurn;
    Score score;
    Level level;
    Mode mode;

    private int currentSequenceIndex = 0;
    private ColourSequence colourSequence;

    //Game starts with player1 in turn
    private boolean player1Turn = true; // Start with player 1

    // Make a reference to ButtonPanel, used in displaySequence()
    private ButtonPanel buttonPanelRef;

    public static int scorePoint1;
    public static int scorePoint2;

  

    //constructor
    public GamePanel() {

        //Add a start button
        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
        this.add(startButton);


        random = new Random();
        this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        startGame();

    }

    public void setButtonPanel(ButtonPanel panelRef) {
        this.buttonPanelRef = panelRef;
    }

    public void startGame() {
        // Stop any active timers
        if(sequenceTimer != null) {
            sequenceTimer.stop();
        }
        if(countdownTimer != null) {
            countdownTimer.stop();
        }
    
        //call things
        playerTurn = new PlayerTurn(GAME_WIDTH, GAME_HEIGHT, player1Turn);
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        level = new Level(GAME_WIDTH, GAME_HEIGHT);
        mode = new Mode(GAME_WIDTH, GAME_HEIGHT);
        displaySequence();
        timer = new PlayerTimer(10);
        newTimer();
    }

    //drawing and painting stuff on screen
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image,0,0,this);        
        
  
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
    
    public void gameOver() {
        // Reset the game state
        round = 1;
        waitingForUserInput = false;
        currentSequenceIndex = 0;
        // Display the game is over.
        // Show a popup message
        int choice = JOptionPane.showOptionDialog(this,
                "Game Over!",
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{"Restart", "Exit"}, // The two options
                "default");

        if (choice == JOptionPane.YES_OPTION) {
            restartGame(); 
        } else if (choice == JOptionPane.NO_OPTION) {
            System.exit(0);  // Close the application
        }

        
    }
    
    public void restartGame() {
        // Reset all your game's variables and components
        round = 1;
        waitingForUserInput = false;
        currentSequenceIndex = 0;
        //TODO score reset toevoegen
        colourSequence = new ColourSequence(round);
        startGame(); //restart a new game.
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
        if(sequenceTimer != null) {
            sequenceTimer.stop();
        }
        if(countdownTimer != null) {
            countdownTimer.stop();
        }
    
        double countdown = 10;
        timer = new PlayerTimer(countdown);
    
        countdownTimer = new javax.swing.Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timer.update();
                repaint();
    
                if (timer.getCountdown() == 0) {
                    countdownTimer.stop();
                    gameOver();
                }
            }
        });
    
        countdownTimer.start();
    }
    
    

    private void displaySequence() {
        if(countdownTimer != null) {
            countdownTimer.stop();
        }
        if(sequenceTimer != null) {
            sequenceTimer.stop();
        }
        currentSequenceIndex = 0; // Reset the index
    
        sequenceTimer = new javax.swing.Timer(1500, e -> {
            if (currentSequenceIndex < colourSequence.getSequence().length) {
                Color currentColor = colourSequence.getSequence()[currentSequenceIndex];
    
                for (JButton btn : buttonPanelRef.getColorButtons()) {
                    btn.setVisible(false); // Hide all buttons first
                }
    
                for (JButton btn : buttonPanelRef.getColorButtons()) {
                    if (btn.getBackground().equals(currentColor)) {
                        btn.setVisible(true);
    
                        javax.swing.Timer hideTimer = new javax.swing.Timer(500, event -> {
                            btn.setVisible(false);
                        });
                        hideTimer.setRepeats(false);
                        hideTimer.start();
                    }
                }
                currentSequenceIndex++;
            } else {
                sequenceTimer.stop();
                countdownTimer.start();
                waitingForUserInput = true;
    
                // Make all buttons visible again for the player's turn
                for (JButton button : buttonPanelRef.getColorButtons()) {
                    button.setVisible(true);
                }
            }
        });
        sequenceTimer.start();
    }
    
    
    //Check if user input equals sequence colour
    public void playerPressedColor(Color color) {
        if (waitingForUserInput) {
            // Compare the pressed button's color to the current color in the sequence
            if (!color.equals(colourSequence.getSequence()[currentSequenceIndex])) {
                countdownTimer.stop();
                gameOver(); // If the colors don't match, game over
                return;
            }
    
            currentSequenceIndex++;
    
            if (currentSequenceIndex == colourSequence.getSequence().length) {
                countdownTimer.stop();
                waitingForUserInput = false;
                if (player1Turn) {
                    scorePoint1 = scorePoint1 + 10;
                    player1Turn = false;
                    JOptionPane.showMessageDialog(this, "Player 2's turn");
                } else {
                    scorePoint2 = scorePoint2 + 10;
                    player1Turn = true;
                    round++;
                    if (round > 10) {
                        JOptionPane.showMessageDialog(this, "Congratulations! Both players have completed all rounds.");
                        gameOver();
                        return;
                    }
                }
                currentSequenceIndex = 0;
                colourSequence = new ColourSequence(round);
                displaySequence();
            }
        
            }
        }
    
    
    
    //button pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
    }

}