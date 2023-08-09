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
     * {@inheritDoc}
     */
    @Override
    public boolean isHighScore(String difficulty, int score) {
        return highScores.containsKey(difficulty) && score > highScores.get(difficulty);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateHighScore(String difficulty, int score) {
        if (!highScores.containsKey(difficulty) || score > highScores.get(difficulty)) {
            highScores.put(difficulty, score);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHighScore(String difficulty) {
        return highScores.getOrDefault(difficulty, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveHighScores() {
        storage.saveHighScores(highScores);
    }
}