package interface_adapters;

import use_cases.HighScoreGatewayBoundary;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileHighScoreGateway implements HighScoreGatewayBoundary {
    private final String fileName;

    public FileHighScoreGateway(String fileName) {
        this.fileName = fileName;
    }

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
    @Override
    public Map<String, Integer> loadHighScores() {
        Map<String, Integer> loadedHighScores = new HashMap<>();
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
        } catch (IOException e) {
            // Ignore if the file doesn't exist or there's an issue reading it
        }
        return loadedHighScores;
    }
}
