import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import javax.swing.JFrame;

public class GameFrame extends JFrame {
    
    //instance
    GamePanel panel;
    ButtonPanel panel2;
    

    //constructor
    GameFrame(){

        //finish intantiating withing constructor
        panel = new GamePanel();   // Create GamePanel without a ButtonPanel reference
        panel2 = new ButtonPanel();  // Create ButtonPanel without a GamePanel reference

        panel.setButtonPanel(panel2);  // Set the ButtonPanel reference in GamePanel
        panel2.setGamePanel(panel);  // Set the GamePanel reference in ButtonPanel

        this.add(panel, BorderLayout.NORTH);
        this.add(panel2, BorderLayout.SOUTH);

        this.setTitle("Colour Game");
		this.setResizable(false);
		this.setBackground(Color.GRAY);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// this.pack();
        this.setSize(1400,800);
        // this.setLayout(new BorderLayout());

		this.setVisible(true);
		this.setLocationRelativeTo(null);

    }

}