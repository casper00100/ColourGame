import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class ButtonPanel extends JPanel implements ActionListener {

    //declare things
    static final int Sequence_WIDTH = 1400;
    static final int Sequence_HEIGHT = 100;
    static final Dimension SCREEN2_SIZE = new Dimension(Sequence_WIDTH, Sequence_HEIGHT);

    int minimumButtons = 3;
    int amountButtons = 5;
    int maximumButtons = minimumButtons + amountButtons;//8
    JButton[] colorButtons = new JButton[maximumButtons];

    public JButton[] getColorButtons() {
        return colorButtons;
    }

    // static final int BUTTON_SIZE = 100;
    // static final int MAX_BUTTONS = 12;
    // static final int DELAY = 75; //higher -> slower game
    // final int x[] = new int[MAX_BUTTONS]; //x coordinate of button
    // int minButtons = 2;
    // int rightColour;
    // boolean replicating = false; //whether the Mode is observing or replicating
    // //maybe 2 seperate arrays for sequence
    // int randomX[] = new int[MAX_BUTTONS];
    // int color1;

    // Image image;
	// Graphics graphics;

    // Timer timer;
	Random random;

    // PlayerTurn playerTurn;
    // Score score;
    // Level level;
    // ColourSequence colourSequence;
    // Mode mode;




    //constructor
    ButtonPanel() {

        random = new Random();

        this.setPreferredSize(new Dimension(Sequence_WIDTH, Sequence_HEIGHT));
        this.setBackground(Color.WHITE);
        this.setFocusable(true);
        this.setLayout(new GridLayout());

        //TODO assign random color to button
        //add color buttons
        for (int i = 0; i < minimumButtons; i++) {
            colorButtons[i] = new JButton();
            this.add(colorButtons[i]);
            colorButtons[i].setFont(new Font("MV Boli",Font.BOLD,100));
            colorButtons[i].setFocusable(true);
            colorButtons[i].addActionListener(this);
            colorButtons[i].setSize(100, 100);

            int rn = random.nextInt(1,6);
            if (rn == 1) {
                colorButtons[i].setBackground(Color.RED);
            } else if (rn == 2) {
                colorButtons[i].setBackground(Color.GREEN);
            } else if (rn == 3) {
                colorButtons[i].setBackground(Color.BLUE);
            } else if (rn == 4) {
                colorButtons[i].setBackground(Color.MAGENTA);
            } else if (rn == 5) {
                colorButtons[i].setBackground(Color.YELLOW);
            }

        }

        //TODO change this part to, when the Level.java (correct replication) is completed amount of buttons increases.
        for (int i = minimumButtons; i < maximumButtons; i++) {
            colorButtons[i] = new JButton();
            this.add(colorButtons[i]);
            colorButtons[i].setFont(new Font("MV Boli",Font.BOLD,100));
            colorButtons[i].setFocusable(false);
            colorButtons[i].addActionListener(this);
            colorButtons[i].setSize(100, 100);

            int rn = random.nextInt(1,6);
            if (rn == 1) {
                colorButtons[i].setBackground(Color.RED);
            } else if (rn == 2) {
                colorButtons[i].setBackground(Color.GREEN);
            } else if (rn == 3) {
                colorButtons[i].setBackground(Color.BLUE);
            } else if (rn == 4) {
                colorButtons[i].setBackground(Color.MAGENTA);
            } else if (rn == 5) {
                colorButtons[i].setBackground(Color.YELLOW);
            }
            
        
        }

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}