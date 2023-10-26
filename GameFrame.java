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
        panel2 = new ButtonPanel();
        panel = new GamePanel(panel2);  // Passing panel2 to GamePanel
        
        this.add(panel,BorderLayout.NORTH);
        this.add(panel2,BorderLayout.SOUTH);

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