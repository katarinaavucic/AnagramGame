package interface_adapters;
import use_cases.gameplay.AnagramGameInputBoundary;
import use_cases.saving.HighScoreManagerInputBoundary;
import java.util.Scanner;

/**
 * A class that executes the command from the StartGameMenu. It contains an AnagramGameUseCase class to execute gameplay
 * functionalities, a highScoreManager to delegate high score functionalities, and a scanner to read the user input.
 */
public class GameController {
    private final AnagramGameInputBoundary gameUseCase;
    private final HighScoreManagerInputBoundary highScoreUseCase;
    private final Scanner scanner;

    /**
     * Creates a new GameController with the specified data.
     * @param gameUseCase an AnagramGameUseCase object that it will call to create a new round of an anagram game
     * @param highScoreManagerUseCase a HighScoreManagerUseCase object that will deal with scoring functionalities
     * @param scanner a Scanner to read user input
     */
    public GameController(AnagramGameInputBoundary gameUseCase, HighScoreManagerInputBoundary highScoreManagerUseCase,
                          Scanner scanner) {
        this.gameUseCase = gameUseCase;
        this.highScoreUseCase = highScoreManagerUseCase;
        this.scanner = scanner;
    }

    /**
     * Executes the command from StartGameMenu to create one of the anagram games.
     * @param userAnswer a String containing the user's input of which game they would like to play
     */
    public void executeCommand(String userAnswer) {
        switch (userAnswer) {
            case "1":
                playAnagramGame();
                break;
            case "2":
                checkAnagrams();
                break;
            default:
                System.out.println("Invalid option. Please select again.");
                break;
        }
    }

    /**
     * Creates a new round of the anagram game.
     */
    public void playAnagramGame() {
        System.out.println("Anagram Game:");
        int totalScore = 0;

        // loops until user types "quit"
        while (true) {
            System.out.println("Choose difficulty level (easy, medium, hard) or type 'quit' to exit:");
            String difficulty = scanner.nextLine().toLowerCase();

            if (difficulty.equalsIgnoreCase("quit")) {
                break;
            }

            String word = gameUseCase.getRandomWord(difficulty);
            String shuffledWord = gameUseCase.shuffleWord(word);

            System.out.println("Solve the anagram: " + shuffledWord);

            // counts time it takes for user to answer and calculates score
            long startTime = System.currentTimeMillis();
            String userAnswer = scanner.nextLine();
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;
            int roundScore = gameUseCase.calculateScore(elapsedTime, difficulty);

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
            if (highScoreUseCase.isHighScore(difficulty, roundScore)) {
                System.out.println("New high score for " + difficulty + ": " + roundScore);
                highScoreUseCase.updateHighScore(difficulty, roundScore);
                highScoreUseCase.saveHighScores();
            } else {
                System.out.println("High score for " + difficulty + ": " + highScoreUseCase.getHighScore(difficulty));
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
    public void checkAnagrams() {
        System.out.print("Enter the first word: ");
        String word1 = scanner.nextLine();
        System.out.print("Enter the second word: ");
        String word2 = scanner.nextLine();

        // finds if two words are anagrams
        boolean areAnagrams = gameUseCase.checkAnagrams(word1, word2);

        if (areAnagrams) {
            System.out.println(word1 + " and " + word2 + " are anagrams!");
        } else {
            System.out.println(word1 + " and " + word2 + " are not anagrams.");
        }
    }
}