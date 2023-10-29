import java.awt.*;
import java.util.Random;

public class ColourSequence {

    //declare
    Color[] sequence; // Dynamic size based on current round

    Random random;

    ColourSequence(int round) {
        // Determine the size of the sequence based on the round
        int sequenceSize = getSequenceSizeForRound(round);
        sequence = new Color[sequenceSize];
        random = new Random();
        generateRandomSequence();
    }

    private int getSequenceSizeForRound(int round) {
        if (round == 1 || round == 2) {
            return 3;
        } else if (round == 3 || round == 4) {
            return 4;
        } else if (round == 5 || round == 6) {
            return 5;
        } else if (round == 7 || round == 8) {
            return 6;
        } else {  // round 9 or 10
            return 7;
        }
    }

    private void generateRandomSequence() {
        Color[] possibleColors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.MAGENTA};
        for (int i = 0; i < sequence.length; i++) {
            sequence[i] = possibleColors[random.nextInt(possibleColors.length)];
        }
    }

    public Color[] getSequence() {
        return sequence;
    }
}