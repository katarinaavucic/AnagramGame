package use_cases;

public interface HighScoreManagerInputBoundary {
    boolean isHighScore(String difficulty, int score);
    void updateHighScore(String difficulty, int score);
    int getHighScore(String difficulty);
    void saveHighScores();


}
