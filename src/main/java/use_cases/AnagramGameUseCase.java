package use_cases;

import entities.AnagramChecker;

import java.util.Random;
import java.util.Scanner;

public class AnagramGameUseCase implements AnagramGameInputBoundary {
    private final AnagramChecker anagramChecker;
    private final Scanner scanner;
    private final HighScoreManagerInputBoundary highScoreManager;

    public AnagramGameUseCase(AnagramChecker anagramChecker, Scanner scanner, HighScoreManagerInputBoundary highScoreManager) {
        this.anagramChecker = anagramChecker;
        this.scanner = scanner;
        this.highScoreManager = highScoreManager;
    }

    @Override
    public void playAnagramGame() {
        System.out.println("Anagram Game:");
        int totalScore = 0;

        while (true) {
            String difficulty = getDifficulty(scanner);

            if (difficulty.equalsIgnoreCase("quit")) {
                break;
            }

            String word1 = getRandomWord(difficulty);
            String word2 = shuffleWord(word1);

            System.out.println("Solve the anagram: " + word2);

            long startTime = System.currentTimeMillis();

            String userAnswer = scanner.nextLine();

            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;
            int roundScore = calculateScore(elapsedTime, difficulty);

            if (userAnswer.equalsIgnoreCase(word1)) {
                System.out.println("Congratulations! You solved the anagram.");
                System.out.println("Round score: " + roundScore);
                totalScore += roundScore;
            } else {
                System.out.println("Sorry, the correct answer was: " + word1);
                System.out.println("Round score: 0");
            }

            System.out.println("Total score: " + totalScore);

            if (highScoreManager.isHighScore(difficulty, roundScore)) {
                System.out.println("New high score for " + difficulty + ": " + roundScore);
                highScoreManager.updateHighScore(difficulty, roundScore);
                highScoreManager.saveHighScores();
            } else {
                System.out.println("High score for " + difficulty + ": " + highScoreManager.getHighScore(difficulty));
            }

            System.out.print("Type 'quit' to exit or press Enter to continue: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("quit")) {
                break;
            }
        }
    }

    @Override
    public void checkAnagrams() {
        System.out.print("Enter the first word: ");
        String word1 = scanner.nextLine();

        System.out.print("Enter the second word: ");
        String word2 = scanner.nextLine();

        boolean areAnagrams = checkAnagrams(word1, word2);

        if (areAnagrams) {
            System.out.println(word1 + " and " + word2 + " are anagrams!");
        } else {
            System.out.println(word1 + " and " + word2 + " are not anagrams.");
        }
    }

    private String getDifficulty(Scanner scanner) {
        System.out.println("Choose difficulty level (easy, medium, hard) or type 'quit' to exit:");
        return scanner.nextLine().toLowerCase();
    }

    private String getRandomWord(String difficulty) {
        // Implement your word list retrieval based on the selected difficulty
        // For simplicity, we'll use predefined lists here
        String[] easyWords = {"apple", "cat", "dog", "sun", "tree"};
        String[] mediumWords = {"banana", "elephant", "guitar", "mountain", "keyboard"};
        String[] hardWords = {"independence", "chocolate", "juxtaposition", "university", "sophisticated"};

        String[] selectedWords;

        switch (difficulty) {
            case "medium":
                selectedWords = mediumWords;
                break;
            case "hard":
                selectedWords = hardWords;
                break;
            default: // default is easy
                selectedWords = easyWords; // Default to easy words
                break;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(selectedWords.length);
        return selectedWords[randomIndex];
    }

    private String shuffleWord(String word) {
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

    private int calculateScore(long elapsedTime, String difficulty) {
        ScoringStrategy scoringStrategy;
        switch (difficulty) {
            case "medium":
                scoringStrategy = new MediumScoringStrategy();
                break;
            case "hard":
                scoringStrategy = new HardScoringStrategy();
                break;
            default: // default is easy
                scoringStrategy = new EasyScoringStrategy();
                break;
        }
        return scoringStrategy.calculateScore(elapsedTime);
    }

    private boolean checkAnagrams(String word1, String word2) {
        return anagramChecker.areAnagrams(word1, word2);
    }
}