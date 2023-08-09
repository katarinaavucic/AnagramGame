package use_cases.saving;
/**
 * An interface that the HighScoreManagerUseCase implements to maintain the Dependency Inversion Principle.
 */
public interface HighScoreManagerInputBoundary {
    /**
     * Checks if a score is a high score for the specified difficulty.
     * @param difficulty a String containing the difficulty level
     * @param score an int representing the score the user got on this round
     * @return a boolean indicating True if score is a high score and False if not.
     */
    boolean isHighScore(String difficulty, int score);

    /**
     * Updates the local highScores dictionary in this session.
     * @param difficulty a String containing the difficulty level
     * @param score an int representing the score the user got on this round
     */
    void updateHighScore(String difficulty, int score);

    /**
     * Gets the high score for a specific difficulty level in the local highScores dictionary.
     * @param difficulty a String containing the difficulty level
     * @return an int containing the high score
     */
    int getHighScore(String difficulty);

    /**
     * Calls the saveHighScores method in the storage to save the highScores.
     */
    void saveHighScores();
}