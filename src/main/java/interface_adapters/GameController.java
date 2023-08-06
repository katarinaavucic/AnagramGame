package interface_adapters;

import use_cases.AnagramGameUseCase;

import java.util.Scanner;

public class GameController {
    private final Scanner scanner;
    private final AnagramGameUseCase gameUseCase;

    public GameController(Scanner scanner, AnagramGameUseCase gameUseCase) {
        this.scanner = scanner;
        this.gameUseCase = gameUseCase;
    }

    public void startGame() {
        System.out.println("Welcome to the Anagram Game!");
        int totalScore = 0;

        while (true) {
            String difficulty = gameUseCase.getDifficulty(scanner);

            if (difficulty.equalsIgnoreCase("quit")) {
                break;
            }

            String word1 = gameUseCase.getRandomWord(difficulty);
            String word2 = gameUseCase.shuffleWord(word1);

            System.out.println("Solve the anagram: " + word2);

            long startTime = System.currentTimeMillis();

            String userAnswer = scanner.nextLine();

            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;
            int roundScore = gameUseCase.calculateScore(elapsedTime, difficulty);

            if (userAnswer.equalsIgnoreCase(word1)) {
                System.out.println("Congratulations! You solved the anagram.");
                System.out.println("Round score: " + roundScore);
                totalScore += roundScore;
            } else {
                System.out.println("Sorry, the correct answer was: " + word1);
                System.out.println("Round score: 0");
            }

            System.out.println("Total score: " + totalScore);

            if (gameUseCase.isHighScore(difficulty, roundScore)) {
                System.out.println("New high score for " + difficulty + ": " + roundScore);
                gameUseCase.updateHighScore(difficulty, roundScore);
                gameUseCase.saveHighScores(); // Save updated high scores
            } else {
                System.out.println("High score for " + difficulty + ": " + gameUseCase.getHighScore(difficulty));
            }

            System.out.print("Type 'quit' to exit or press Enter to continue: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("quit")) {
                break;
            }
        }

        System.out.println("Thank you for playing! Final score: " + totalScore);
    }
}