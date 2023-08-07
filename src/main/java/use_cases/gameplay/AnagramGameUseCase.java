package use_cases.gameplay;

import entities.AnagramChecker;
import use_cases.saving.HighScoreManagerInputBoundary;
import use_cases.scoring.EasyScoringStrategy;
import use_cases.scoring.HardScoringStrategy;
import use_cases.scoring.MediumScoringStrategy;
import use_cases.scoring.ScoringStrategy;

import java.util.Random;
import java.util.Scanner;

/**
 * A class that executes both anagram games. It implements AnagramGameInputBoundary, and contains an anagramChecker to
 * check for anagrams, a scanner to read the user input, and a highScoreManager to delegate high score functionalities.
 */
public class AnagramGameUseCase implements AnagramGameInputBoundary {
    private final AnagramChecker anagramChecker;
    private final Scanner scanner;
    private final HighScoreManagerInputBoundary highScoreManager;

    /**
     * Creates a new instance of AnagramGameUseCase with the given inputs.
     * @param anagramChecker an AnagramChecker for checking anagrams
     * @param scanner a Scanner for reading user input
     * @param highScoreManager a HighScoreManagerUseCase for delegating high score functionalities
     */
    public AnagramGameUseCase(AnagramChecker anagramChecker, Scanner scanner, HighScoreManagerInputBoundary highScoreManager) {
        this.anagramChecker = anagramChecker;
        this.scanner = scanner;
        this.highScoreManager = highScoreManager;
    }

    /**
     * Creates a new round of the anagram game.
     */
    @Override
    public void playAnagramGame() {
        System.out.println("Anagram Game:");
        int totalScore = 0;

        // loops until user types "quit"
        while (true) {
            String difficulty = getDifficulty(scanner);

            if (difficulty.equalsIgnoreCase("quit")) {
                break;
            }

            String word = getRandomWord(difficulty);
            String shuffledWord = shuffleWord(word);

            System.out.println("Solve the anagram: " + shuffledWord);

            // counts time it takes for user to answer and calculates score
            long startTime = System.currentTimeMillis();
            String userAnswer = scanner.nextLine();
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;
            int roundScore = calculateScore(elapsedTime, difficulty);

            // calculates score for this round
            if (userAnswer.equalsIgnoreCase(word)) {
                System.out.println("Congratulations! You solved the anagram.");
                System.out.println("Round score: " + roundScore);
                totalScore += roundScore;
            } else {
                System.out.println("Sorry, the correct answer was: " + word);
                System.out.println("Round score: 0");
            }

            // calculates total score for this session
            System.out.println("Total score: " + totalScore);

            // checks if score is a high score and retrieves high score
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

    /**
     * Creates a new round of the anagram checker.
     */
    @Override
    public void checkAnagrams() {
        System.out.print("Enter the first word: ");
        String word1 = scanner.nextLine();
        System.out.print("Enter the second word: ");
        String word2 = scanner.nextLine();

        // finds if two words are anagrams
        boolean areAnagrams = checkAnagrams(word1, word2);

        if (areAnagrams) {
            System.out.println(word1 + " and " + word2 + " are anagrams!");
        } else {
            System.out.println(word1 + " and " + word2 + " are not anagrams.");
        }
    }

    /**
     * Gets the difficulty level based on user input
     * @param scanner a Scanner to see user input
     * @return a String representing the difficulty level
     */
    private String getDifficulty(Scanner scanner) {
        System.out.println("Choose difficulty level (easy, medium, hard) or type 'quit' to exit:");
        return scanner.nextLine().toLowerCase();
    }

    /**
     * Gets a random word from a set of generates words.
     * @param difficulty a String representing the difficulty level
     * @return a String representing the random word
     */
    private String getRandomWord(String difficulty) {
        // for simplicity, we'll use predefined lists here
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
            default: // default is easy (so not a case)
                selectedWords = easyWords;
                break;
        }
        // selects a random word
        Random random = new Random();
        int randomIndex = random.nextInt(selectedWords.length);
        return selectedWords[randomIndex];
    }

    /**
     * Shuffles the word.
     * @param word a String representing the random word
     * @return a String representing the shuffled word
     */
    private String shuffleWord(String word) {
        char[] characters = word.toCharArray();
        Random random = new Random();

        // shuffles the word
        for (int i = 0; i < characters.length; i++) {
            int randomIndex = random.nextInt(characters.length);
            char temp = characters[i];
            characters[i] = characters[randomIndex];
            characters[randomIndex] = temp;
        }
        return new String(characters);
    }

    /**
     * Calculates the score by calling on the correct ScoringStrategy class according to difficulty.
     * @param elapsedTime a long representing how long it took
     * @param difficulty a String representing the difficulty level
     * @return an int representing the score
     */
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

    /**
     * Checks if two words are anagrams by calling the anagramChecker's areAnagrams method.
     * @param word1 the first word
     * @param word2 the second word
     * @return a boolean that is true if they are anagrams and false if not
     */
    private boolean checkAnagrams(String word1, String word2) {
        return anagramChecker.areAnagrams(word1, word2);
    }
}