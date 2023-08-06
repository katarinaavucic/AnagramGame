package use_cases;

import java.util.Map;

public interface HighScoreGatewayBoundary {
    Map<String, Integer> loadHighScores();
    void saveHighScores(Map<String, Integer> highScores);
}