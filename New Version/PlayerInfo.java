/**
 * Encapsulate information about a player, including their name and score.
 */
public class PlayerInfo {
    private String name;
    private int score;

    /**
     * Initialize a new player with a name and score of 0.
     */
    public PlayerInfo(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incrementScore(int value) {
        this.score += value;
    }
}
