package use_cases;

import entities.AnagramChecker;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class AnagramGameUseCase {
    private final AnagramChecker anagramChecker;
    private final Map<String, Integer> highScores; // Difficulty level to high score mapping
    private static final String HIGH_SCORES_FILE = "high_scores.txt";

    public AnagramGameUseCase(AnagramChecker anagramChecker) {
        this.anagramChecker = anagramChecker;
        this.highScores = loadHighScores();
    }

    public String getDifficulty(Scanner scanner) {
        System.out.println("Choose difficulty level (easy, medium, hard) or type 'quit' to exit:");
        return scanner.nextLine().toLowerCase();
    }

    public String getRandomWord(String difficulty) {
        // Implement your word list retrieval based on the selected difficulty
        // For simplicity, we'll use predefined lists here
        String[] easyWords = {"apple", "cat", "dog", "sun", "tree"};
        String[] mediumWords = {"banana", "elephant", "guitar", "mountain", "keyboard"};
        String[] hardWords = {"independence", "chocolate", "juxtaposition", "university", "sophisticated"};

        String[] selectedWords;

        switch (difficulty) {
            case "easy":
                selectedWords = easyWords;
                break;
            case "medium":
                selectedWords = mediumWords;
                break;
            case "hard":
                selectedWords = hardWords;
                break;
            default:
                selectedWords = easyWords; // Default to easy words
                break;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(selectedWords.length);
        return selectedWords[randomIndex];
    }

    public String shuffleWord(String word) {
        char[] characters = word.toCharArray();
        Random random = new Random();

        for (int i = 0; i < characters.length; i++) {
            int randomIndex = random.nextInt(characters.length);
            char temp = characters[i];
            characters[i] = characters[randomIndex];
            characters[randomIndex] = temp;
        }

        return new String(characters);
    }

    public int calculateScore(long elapsedTime, String difficulty) {
        int baseScore;
        switch (difficulty) {
            case "easy":
                baseScore = 100;
                break;
            case "medium":
                baseScore = 150;
                break;
            case "hard":
                baseScore = 200;
                break;
            default:
                baseScore = 100; // Default to easy level
                break;
        }
        double timeMultiplier = 1.0 / (elapsedTime / 1000.0); // Higher score for faster solving
        return (int) (baseScore * timeMultiplier);
    }

    public boolean isHighScore(String difficulty, int score) {
        return highScores.containsKey(difficulty) && score > highScores.get(difficulty);
    }

    public void updateHighScore(String difficulty, int score) {
        if (!highScores.containsKey(difficulty) || score > highScores.get(difficulty)) {
            highScores.put(difficulty, score);
        }
    }

    public int getHighScore(String difficulty) {
        return highScores.getOrDefault(difficulty, 0);
    }

    public void saveHighScores() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(HIGH_SCORES_FILE))) {
            for (Map.Entry<String, Integer> entry : highScores.entrySet()) {
                writer.println(entry.getKey() + "," + entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<String, Integer> loadHighScores() {
        Map<String, Integer> loadedHighScores = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(HIGH_SCORES_FILE))) {
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