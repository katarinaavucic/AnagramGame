package interface_adapters;

import use_cases.saving.HighScoreGatewayBoundary;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * A class that executes the loading a saving functionalities for highScores. It contains a string filename
 * representing the file to save and load highScores.
 */
public class FileHighScoreGateway implements HighScoreGatewayBoundary {
    private final String fileName;

    /**
     * Creates a new instance of FileHighScoreGateway with specified data.
     * @param fileName a String containing the name of the file that will be used.
     */
    public FileHighScoreGateway(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Loads the highScores from file.
     * @return a Map containing the highScores
     */
    @Override
    public Map<String, Integer> loadHighScores() {
        Map<String, Integer> loadedHighScores = new HashMap<>();
        // tries to read file specified by filename
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String difficulty = parts[0];
                    int score = Integer.parseInt(parts[1]);
                    loadedHighScores.put(difficulty, score);
                }
            }
        // if it fails, sets all highScores to 0
        } catch (IOException e) {
            loadedHighScores.put("easy", 0);
            loadedHighScores.put("medium", 0);
            loadedHighScores.put("hard", 0);
        }
        return loadedHighScores;
    }

    /**
     * Saves the local highScores to a .txt file.
     * @param highScores a Map containing the local highScores
     */
    @Override
    public void saveHighScores(Map<String, Integer> highScores) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Map.Entry<String, Integer> entry : highScores.entrySet()) {
                writer.println(entry.getKey() + "," + entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
