import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

//Generate the colour sequence for the computer to show
public class ColourSequence extends Rectangle {

    //declare
    static int GAME_WIDTH;
	static int GAME_HEIGHT;
    Random random;
    
        // Maximum sequence is 10.
        Color[] sequence = new Color[10]; 
    
        ColourSequence() {
            random = new Random();
            generateRandomSequence();
        }
    
        private void generateRandomSequence() {
            Color[] possibleColors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};
            
            for (int i = 0; i < sequence.length; i++) {
                sequence[i] = possibleColors[random.nextInt(possibleColors.length)];
            }
        }
    
        public Color[] getSequence() {
            return sequence;
        }
    }