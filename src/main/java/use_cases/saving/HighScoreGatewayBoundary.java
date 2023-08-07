package use_cases.saving;

import java.util.Map;

/**
 * An interface that the FileHighScoreGateway implements to maintain the Dependency Inversion Principle.
 */
public interface HighScoreGatewayBoundary {

    /**
     * Loads the highScores from file.
     * @return a Map containing the highScores
     */
    Map<String, Integer> loadHighScores();

    /**
     * Saves the local highScores to a .txt file.
     * @param highScores a Map containing the local highScores
     */
    void saveHighScores(Map<String, Integer> highScores);
}