package use_cases.saving;

import java.util.Map;

public class HighScoreManagerUseCase implements HighScoreManagerInputBoundary{
    private final Map<String, Integer> highScores;
    private final HighScoreGatewayBoundary storage;

    public HighScoreManagerUseCase(HighScoreGatewayBoundary storage) {
        this.storage = storage;
        this.highScores = storage.loadHighScores();
    }
    @Override
    public boolean isHighScore(String difficulty, int score) {
        return highScores.containsKey(difficulty) && score > highScores.get(difficulty);
    }
    @Override
    public void updateHighScore(String difficulty, int score) {
        if (!highScores.containsKey(difficulty) || score > highScores.get(difficulty)) {
            highScores.put(difficulty, score);
        }
    }
    @Override
    public int getHighScore(String difficulty) {
        return highScores.getOrDefault(difficulty, 0);
    }

    @Override
    public void saveHighScores() {
        storage.saveHighScores(highScores);
    }
}