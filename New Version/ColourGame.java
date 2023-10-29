import javax.swing.*; //Swing components for GUI creation
import java.awt.*; //AWT components for graphical and user interface elements
import java.awt.event.*; //AWT classes used for event handling
import java.util.Random; //Utility class for randomization
import java.util.ArrayList; //Utility class used for resizable array to store the colour sequences

//Create the main game frame
public class ColourGame extends JFrame {

    //Components for main game setup and gameplay options
    private GamePanel gamePanel; 
    private JButton singlePlayerButton;
    private JButton multiplayerButton;
    private JTextArea gameExplanation;
    
    //Initialize the main game window
    public ColourGame() {
        setTitle("Colour Game");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        gamePanel = new GamePanel();
        singlePlayerButton = new JButton("Single Player");
        multiplayerButton = new JButton("Multiplayer");

        //Button to choose gamemode: singleplayer
        singlePlayerButton.addActionListener(e -> {
            getContentPane().removeAll();
            setLayout(new BorderLayout());
            add(gamePanel, BorderLayout.CENTER);
            gamePanel.startGame();
            revalidate();
            repaint();
        });
        
        //Button to choose gamemode: multiplayer
        multiplayerButton.addActionListener(e -> {
            JTextField player1Field = new JTextField(10);
            JTextField player2Field = new JTextField(10);
        
            JPanel namePanel = new JPanel();
            namePanel.add(new JLabel("Player 1:"));
            namePanel.add(player1Field);
            namePanel.add(Box.createHorizontalStrut(15));  // A spacer for visual clarity
            namePanel.add(new JLabel("Player 2:"));
            namePanel.add(player2Field);
        
            int result = JOptionPane.showConfirmDialog(null, namePanel, 
                    "Enter Player Names", JOptionPane.OK_CANCEL_OPTION);
        
            if (result == JOptionPane.OK_OPTION) {
                gamePanel.setMultiplayerMode(true);
                gamePanel.setPlayerNames(player1Field.getText().isEmpty() ? "Player 1" : player1Field.getText(), 
                                            player2Field.getText().isEmpty() ? "Player 2" : player2Field.getText());
                getContentPane().removeAll();
                setLayout(new BorderLayout());
                add(gamePanel, BorderLayout.CENTER);
                gamePanel.startGame();
                revalidate();
                repaint();
            }
        });
        
        //Game Explanation text area initalization
        gameExplanation = new JTextArea();
        gameExplanation.setEditable(false);
        gameExplanation.setWrapStyleWord(true);
        gameExplanation.setLineWrap(true);
        gameExplanation.setOpaque(false);
        gameExplanation.setFocusable(false);
        gameExplanation.setBackground(UIManager.getColor("Label.background"));
        gameExplanation.setFont(UIManager.getFont("Label.font"));
        gameExplanation.setBorder(UIManager.getBorder("Label.border"));
        
        String explanationText = "Welcome to the Colour Game!\n\n" +
                                 "In this game, a sequence of colors will flash on the screen. " +
                                 "Your objective is to memorize and replicate the sequence by " +
                                 "clicking on the respective colors in the exact order, within 10 seconds. \n\n" + 
                                 "Every two rounds, the length of the sequence increases to make it more difficult.\n\n" +
                                 "Complete the sequence within 4 seconds to earn more points!\n\n" +
                                 "For multiplayer only: fill in your names after selecting the game mode to see whose turn it is.\n\n" +
                                 "Choose a game mode to start playing!";
        gameExplanation.setText(explanationText);

        add(gameExplanation, BorderLayout.NORTH); //Center the text
                
        JPanel modePanel = new JPanel(new GridLayout(1, 2, 10, 10));
        singlePlayerButton.setPreferredSize(new Dimension(300, 100));
        multiplayerButton.setPreferredSize(new Dimension(300, 100));
        modePanel.add(singlePlayerButton);
        modePanel.add(multiplayerButton);
        add(modePanel, BorderLayout.CENTER);
    }

    //Entry point of the game
    public static void main(String[] args) {
        //Safety measure: GUI is built on Event-Dispatch Thread (EDT) to avoid issues 
        //should it occur that multiple threads interact with Swing components
        SwingUtilities.invokeLater(() -> {
            new ColourGame().setVisible(true);
        });
    }
}
