package use_cases;

import entities.AnagramChecker;

import java.util.Random;
import java.util.Scanner;

public class AnagramGameUseCase implements AnagramGameInputBoundary {
    private final AnagramChecker anagramChecker;

    public AnagramGameUseCase(AnagramChecker anagramChecker) {
        this.anagramChecker = anagramChecker;
    }

    @Override
    public String getDifficulty(Scanner scanner) {
        System.out.println("Choose difficulty level (easy, medium, hard) or type 'quit' to exit:");
        return scanner.nextLine().toLowerCase();
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
    public boolean checkAnagrams(String word1, String word2) {
        return anagramChecker.areAnagrams(word1, word2);
    }

}