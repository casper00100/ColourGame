import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class ButtonPanel extends JPanel implements ActionListener {

    //declare things
    static final int Sequence_WIDTH = 1400;
    static final int Sequence_HEIGHT = 100;
    static final Dimension SCREEN2_SIZE = new Dimension(Sequence_WIDTH, Sequence_HEIGHT);

    JButton[] colorButtons = new JButton[5];  // Five buttons

    // Reference to the GamePanel
    private GamePanel gamePanelReference;

    public JButton[] getColorButtons() {
        return colorButtons;
    }

    //constructor
    ButtonPanel() {
        initButtons();
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanelReference = gamePanel;
    }

    private void initButtons() {
        this.setPreferredSize(new Dimension(Sequence_WIDTH, Sequence_HEIGHT));
        this.setBackground(Color.WHITE);
        this.setFocusable(true);
        this.setLayout(new GridLayout());

        Color[] buttonColors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.MAGENTA};

        for (int i = 0; i < colorButtons.length; i++) {
            colorButtons[i] = new JButton();
            this.add(colorButtons[i]);
            colorButtons[i].setFont(new Font("MV Boli",Font.BOLD,100));
            colorButtons[i].setFocusable(true);
            colorButtons[i].addActionListener(this);
            colorButtons[i].setSize(100, 100);
            colorButtons[i].setBackground(buttonColors[i]);
            
            // Add action listeners to your buttons
            colorButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton sourceButton = (JButton) e.getSource();
                    if (gamePanelReference != null) {
                        gamePanelReference.playerPressedColor(sourceButton.getBackground());
                    }
                }
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}