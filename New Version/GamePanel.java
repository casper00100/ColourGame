import javax.swing.*; //Swing components for GUI creation
import java.awt.*; //AWT components for graphical and user interface elements
import java.awt.event.*; //AWT classes used for event handling
import java.util.Random; //Utility class for randomization
import java.util.ArrayList; //Utility class used for resizable array to store the colour sequences

//Game Panel where most game play interactions occur
class GamePanel extends JPanel {
    //The colour options used in the game
    private final Color[] COLORS = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA};

    //Gameplay variables
    private ArrayList<Color> sequence = new ArrayList<>();
    private ArrayList<JButton> colorButtons = new ArrayList<>();
    private int currentRound = 0;
    private int score = 0;
    private JLabel scoreLabel = new JLabel("Score: 0");
    private JLabel timerLabel = new JLabel("Time: 10");
    private JLabel roundLabel = new JLabel("Round: 1");
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
    
    //Set player names
    public void setPlayerNames(String name1, String name2) {
        player1.setName(name1);
        player2.setName(name2);
    }


    //Constructor which sets up the playable game panel
    public GamePanel() {
        setLayout(new BorderLayout());

        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 10));
        infoPanel.add(scoreLabel);
        infoPanel.add(timerLabel);
        infoPanel.add(roundLabel);
        add(infoPanel, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel(new GridLayout(3, 2, 20, 20));
        for (Color color : COLORS) {
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

    //Starts a game by setting the round
    public void startGame() {
        currentPlayer = 1; //Reset variable
        player1.setScore(0); //Reset variable
        player2.setScore(0); //Reset variable
        score = 0; // Reset the overall score too, if necessary
        scoreLabel.setText("Score: 0"); //Reset variable
        nextRound();
    }

    //Generates a new colour sequence to be displayed
    private void nextRound() {
        currentRound++;
        roundLabel.setText("Round: " + currentRound);
        if (currentRound > 10) {
            gameOver("You won!");
            return;
        }

        int sequenceLength = 3 + (currentRound - 1) / 2; //Each round becomes more difficult as the sequence gets longer
        sequence.clear();
        Random rand = new Random();
        for (int i = 0; i < sequenceLength; i++) {
            sequence.add(COLORS[rand.nextInt(COLORS.length)]);
        }

        showSequence();
    }

    //Displays the sequence by lighting up the buttons in order, 
    //player can observe it to replicate afterwards
    private void showSequence() {
        disableAllButtons(); //Does not allow user to push the buttons before the sequence has been fully shown

        new Thread(() -> {
            try {
                for (Color color : sequence) {
                    JButton button = colorButtons.stream().filter(b -> b.getBackground() == color).findFirst().orElse(null);
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

    //TODO add a message whose turn it is.

    //Check if player input equals the colour sequence displayed before
    private void checkSequence(Color color) {
        if (sequence.isEmpty() || !color.equals(sequence.remove(0))) {
            gameOver("Wrong sequence!");
            return;
        }
    
        if (sequence.isEmpty()) {
            timer.stop();
            if (timeLeft >= 6) { // Award more points if sequence is completed within 4 (10-6=4) seconds
                score += 200;
            } else {
                score += 100;
            }
            scoreLabel.setText("Score: " + score);
            nextRound();
        }
    }

    //Determines what happens if 1) wrong sequence is replicated 
    //2) a new round is started in multiplayer mode
    private void gameOver(String message) {
        timer.stop();
        if (isMultiplayer) {
            if (currentPlayer == 1) {
                player1.setScore(score);
                currentPlayer = 2;
                player2.setScore(0); // For resetting Player 2's score
                JOptionPane.showMessageDialog(this, player1.getName() + "'s turn is over. " + player2.getName() + " get ready!");
                startGame();
            } else if (currentPlayer == 2) {
                player2.setScore(score);
                if (player1.getScore() > player2.getScore()) {
                    JOptionPane.showMessageDialog(this, player1.getName() + " wins with a score of " + player1.getScore() + " vs " + player2.getScore());
                } else if (player2.getScore() > player1.getScore()) {
                    JOptionPane.showMessageDialog(this, player2.getName() + " wins with a score of " + player2.getScore() + " vs " + player1.getScore());
                } else {
                    JOptionPane.showMessageDialog(this, "It's a tie with a score of " + player1.getScore());
                }
                System.exit(0);
            }
        } else {
            JOptionPane.showMessageDialog(this, message + " Your score is: " + score);
            System.exit(0);
        }
    }
}
