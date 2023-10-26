import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.Random;

public class HomeFrame extends JFrame implements ActionListener {

    JButton mode1Button = new JButton("Single Player");
    JButton mode2Button= new JButton("MultiPlayer");
    JButton startGameButton = new JButton("Start Game");

    JTextField name1Field = new JTextField();
    JTextField name2Field = new JTextField();

    JLabel name1Label = new JLabel("Player 1:");
    JLabel name2Label = new JLabel("Player 2:");
    JLabel messageLabel = new JLabel("New Game:");

    public static String name_player1;
    public static String name_player2;

    int GAME_WIDTH;
    int GAME_HEIGHT;

    GamePanel gamePanel; // Add this
    ButtonPanel buttonPanel; // Add this

    HomeFrame() {

		name1Label.setBounds(50,100,75,25);
		name2Label.setBounds(50,150,75,25);
		
		messageLabel.setBounds(125,50,250,35);
		messageLabel.setFont(new Font(null,Font.ITALIC,25));
		
		name1Field.setBounds(125,100,200,25);
		name2Field.setBounds(125,150,200,25);
		
		mode1Button.setBounds(125,200,200,25);
		mode1Button.setFocusable(false);
		mode1Button.addActionListener(this);
		
		mode2Button.setBounds(125,225,200,25);
		mode2Button.setFocusable(false);
		mode2Button.addActionListener(this);

        startGameButton.setBounds(125,300,200,25);
		startGameButton.setFocusable(false);
		startGameButton.addActionListener(this);
        
        name1Label.setVisible(false);
        name2Label.setVisible(false);
        name1Field.setVisible(false);
        name2Field.setVisible(false);
        startGameButton.setVisible(false);
    
		this.add(name1Label);
		this.add(name2Label);
		this.add(messageLabel);
		this.add(name1Field);
		this.add(name2Field);
		this.add(mode1Button);
		this.add(mode2Button);
        this.add(startGameButton);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(420,420);
        this.setLayout(null);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        // Shows name input part, upon selecting multiplayer mode.
        if (e.getSource() == mode2Button) {
            name1Label.setVisible(true);
            name2Label.setVisible(true);
            name1Field.setVisible(true);
            name2Field.setVisible(true);
            mode1Button.setVisible(false);
            mode2Button.setVisible(false);
            startGameButton.setVisible(true);
            messageLabel.setText("Type in your names:");
        }

        // Shows the game, upon clicking start.
        if (e.getSource() == startGameButton) {
            name_player1 = name1Field.getText();
            name_player2 = name2Field.getText();

            gamePanel = new GamePanel();
            buttonPanel = new ButtonPanel();
            gamePanel.setButtonPanel(buttonPanel);  // Set the ButtonPanel reference in GamePanel
            buttonPanel.setGamePanel(gamePanel);   // Set the GamePanel reference in ButtonPanel
     
            new GameFrame();
            this.setVisible(false);
        }

    }

}

