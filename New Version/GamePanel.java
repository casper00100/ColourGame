import java.awt.*; //AWT components for graphical and user interface elements
import java.awt.event.*; //AWT classes used for event handling
import java.util.ArrayList; //Utility class used for resizable array to store the colour sequences
import java.util.Random; //Utility class for randomization
import javax.swing.*; //Swing components for GUI creation

//Game Panel where most game play interactions occur
class GamePanel extends JPanel {
    //The colour options used in the game
    private final Color[] buttonColors = {
        Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA};

    //Gameplay variables
    private ArrayList<Color> sequence = new ArrayList<>();
    private ArrayList<JButton> colorButtons = new ArrayList<>();
    private int currentRound = 0;
    private int score = 0;
    private JLabel scoreLabel = new JLabel("Score: 0");
    private JLabel timerLabel = new JLabel("Time: 10");
    private JLabel roundLabel = new JLabel("Round: 1");
    private JLabel turnLabel = new JLabel("Current Turn: Player 1");
    private Timer timer;
    private int timeLeft = 10; //Player has 10 seconds per round to replicate the sequence
    private int currentPlayer = 1; //Game starts with player 1

    //Game mode and player details
    private boolean isMultiplayer = false;
    private PlayerInfo player1 = new PlayerInfo("Player 1");
    private PlayerInfo player2 = new PlayerInfo("Player 2");

    //Set correct gamemode
    public void setMultiplayerMode(boolean isMultiplayer) {
        this.isMultiplayer = isMultiplayer;
    }
    
    /**
     * Set the player names for multiplayer mode.
     */
    public void setPlayerNames(String name1, String name2) {
        player1.setName(name1);
        player2.setName(name2);
    }


    /**
     * Initialize the game panel layout and components.
     * Create and configure the info panel containing score, timer, and round labels.
     * Create and configure the buttons panel containing color buttons.
     * Add action listeners to the color buttons for handling player input.
    */
    public GamePanel() {
        setLayout(new BorderLayout());

        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 10));
        infoPanel.add(scoreLabel);
        infoPanel.add(timerLabel);
        infoPanel.add(roundLabel);
        infoPanel.add(turnLabel);
        add(infoPanel, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel(new GridLayout(3, 2, 20, 20));
        for (Color color : buttonColors) {
            JButton button = new JButton();
            button.setBackground(color);
            button.setPreferredSize(new Dimension(150, 150)); // Set button size
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    checkSequence(color);
                }
            });
            colorButtons.add(button);
            buttonsPanel.add(button);
        }
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.add(buttonsPanel);
        add(centerWrapper, BorderLayout.CENTER);
    }

    /**
     * Initializes game variables for a new game.
     * Resets player scores and the overall score.
     * Calls nextRound() to start the first round.
     */
    public void startGame() {      
        player1.setScore(0); //Reset variable
        player2.setScore(0); //Reset variable
        score = 0; // Reset the overall score too, if necessary
        scoreLabel.setText("Score: 0"); //Reset variable
        nextRound();
    }

    //Generates a new colour sequence to be displayed
    private void nextRound() {
        updateTurnLabel(); // Update turnLabel with the first player's name
        currentRound++;
        roundLabel.setText("Round: " + currentRound);
        if (currentRound > 10) {
            gameOver("You won!");
            return;
        }

        //Each round becomes more difficult as the sequence gets longer
        int sequenceLength = 3 + (currentRound - 1) / 2; 
        sequence.clear();
        Random rand = new Random();
        for (int i = 0; i < sequenceLength; i++) {
            sequence.add(buttonColors[rand.nextInt(buttonColors.length)]);
        }

        showSequence();
    }

    //Displays the sequence by lighting up the buttons in order, 
    //player can observe it to replicate afterwards
    private void showSequence() {
        disableAllButtons(); 
        //Does not allow user to push the buttons before the sequence has been fully shown

        new Thread(() -> {
            try {
                for (Color color : sequence) {
                    JButton button = colorButtons
                        .stream().filter(b -> b.getBackground() == color).findFirst().orElse(null);
                    if (button != null) {
                        button.setBackground(Color.WHITE);
                        Thread.sleep(800); // The color will flash for 800 milliseconds
                        button.setBackground(color);
                        Thread.sleep(800);
                    }
                }
                enableAllButtons(); //Enable buttons after showin sequence
                // Start the timer after showing the sequence
                startTimer();
            } catch (InterruptedException e) {
                //During thread.sleep an interruption can occur, 
                //then this is logged to the terminal to know where the error originated from
                e.printStackTrace(); 
            }
        }).start();
    }

    //Method to start the timer, is called after the sequence has been shown
    private void startTimer() {
        timeLeft = 10;
        timerLabel.setText("Time: " + timeLeft);
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timeLeft--; //Keep track of the time elapsed for the player to replicate
                timerLabel.setText("Time: " + timeLeft);
                if (timeLeft <= 0) {
                    gameOver("Game Over!");
                    timer.stop();
                    updateTurnLabel();
                }
            }
        });
        timer.start();
    }

    // Method to disable all buttons
    private void disableAllButtons() {
        for (JButton button : colorButtons) {
            button.setEnabled(false);
        }
    }

    // Method to enable all buttons
    private void enableAllButtons() {
        for (JButton button : colorButtons) {
            button.setEnabled(true);
        }
    }        

    // Update the turnLabel with the current player's name
    private void updateTurnLabel() {
        if (isMultiplayer) {
            if (currentPlayer == 1) {
                turnLabel.setText("Current Turn: " + player1.getName());
            } else if (currentPlayer == 2) {
                turnLabel.setText("Current Turn: " + player2.getName());
            }
            scoreLabel.setText("Score: " + currentPlayerScore().getScore());
        }
    }

    //Check if player input equals the colour sequence displayed before
    private void checkSequence(Color color) {
        if (sequence.isEmpty() || !color.equals(sequence.remove(0))) {
            gameOver("Wrong sequence!");
            return;
        }

        // Award more points if sequence is completed within 4 (10-6=4) seconds
        if (sequence.isEmpty()) {
            timer.stop();
            if (timeLeft >= 6) { 
                currentPlayerScore().incrementScore(200);
            } else {
                currentPlayerScore().incrementScore(100);
            }
            scoreLabel.setText("Score: " + currentPlayerScore().getScore());
            nextRound();
        }
    }

    // Helper method to get the current player's score
    private PlayerInfo currentPlayerScore() {
        return (currentPlayer == 1) ? player1 : player2;
    }

    /*
     * Determines what happens if:
     * 1) Wrong sequence is replicated 
     * 2) A new round is started in multiplayer mode
     */
    private void gameOver(String message) {
        timer.stop();
        if (isMultiplayer) {
            if (currentPlayer == 1) {
                player1.setScore(score);
                currentPlayer = 2;
                updateTurnLabel();
                player2.setScore(0); // For resetting Player 2's score
                JOptionPane.showMessageDialog(this, 
                    player1.getName() + "'s turn is over. " + player2.getName() + " get ready!");
                nextRound();
            } else if (currentPlayer == 2) {
                player2.setScore(score);
                if (player1.getScore() > player2.getScore()) {
                    JOptionPane.showMessageDialog(this, 
                        player1.getName() + " wins with a score of " + player1.getScore() 
                        + " against "
                        + player2.getName() + " with a score of " + player2.getScore());
                } else if (player2.getScore() > player1.getScore()) {
                    JOptionPane.showMessageDialog(this, 
                        player2.getName() + " wins with a score of " + player2.getScore() 
                        + " against "
                        + player1.getName() + " with a score of " + player1.getScore());
                } else {
                    JOptionPane.showMessageDialog(this, 
                        "It's a tie between: "
                        + player1.getName() + " with a score of " + player1.getScore()
                        + " and "
                        + player2.getName() + " with a score of " + player2.getScore());
                        
                }
                System.exit(0);
            }
        } else {
            JOptionPane.showMessageDialog(this, message + " Your score is: " + score);
            System.exit(0);
        }
    }
}
