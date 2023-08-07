package use_cases.saving;

import java.util.Map;

/**
 * A class used to manage all functionalities related to calculating and saving high scores. It contains a local copy of
 * the highScores that is generated before the game, and a FileHighScoreGateway storage object that it calls to save the
 * highScores.
 */
public class HighScoreManagerUseCase implements HighScoreManagerInputBoundary{
    private final Map<String, Integer> highScores;
    private final HighScoreGatewayBoundary storage;

    /**
     * Creates a new HighScoreManagerUseCase with the given data.
     * @param storage a FileHighScoreGateway object that saves the high score data
     */
    public HighScoreManagerUseCase(HighScoreGatewayBoundary storage) {
        this.storage = storage;
        this.highScores = storage.loadHighScores();
    }

    /**
     * Checks if a score is a high score for the specified difficulty.
     * @param difficulty a String containing the difficulty level
     * @param score an int representing the score the user got on this round
     * @return a boolean indicating True if score is a high score and False if not.
     */
    @Override
    public boolean isHighScore(String difficulty, int score) {
        return highScores.containsKey(difficulty) && score > highScores.get(difficulty);
    }

    /**
     * Updates the local highScores dictionary in this session.
     * @param difficulty a String containing the difficulty level
     * @param score an int representing the score the user got on this round
     */
    @Override
    public void updateHighScore(String difficulty, int score) {
        if (!highScores.containsKey(difficulty) || score > highScores.get(difficulty)) {
            highScores.put(difficulty, score);
        }
    }

    /**
     * Gets the high score for a specific difficulty level in the local highScores dictionary.
     * @param difficulty a String containing the difficulty level
     * @return an int containing the high score
     */
    @Override
    public int getHighScore(String difficulty) {
        return highScores.getOrDefault(difficulty, 0);
    }

    /**
     * Calls the saveHighScores method in the storage to save the highScores.
     */
    @Override
    public void saveHighScores() {
        storage.saveHighScores(highScores);
    }
}