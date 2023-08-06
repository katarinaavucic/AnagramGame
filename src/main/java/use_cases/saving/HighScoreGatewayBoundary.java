package use_cases.saving;

import java.util.Map;

public interface HighScoreGatewayBoundary {
    Map<String, Integer> loadHighScores();
    void saveHighScores(Map<String, Integer> highScores);
}