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

        while (true) {
            System.out.println("Select an option:\n1. Play anagram game\n2. Check if two words are anagrams\n3. Quit");
            String option = scanner.nextLine();

            if (option.equals("1")) {
                playAnagramGame();
            } else if (option.equals("2")) {
                checkAnagrams();
            } else if (option.equals("3")) {
                break;
            } else {
                System.out.println("Invalid option. Please select again.");
            }
        }

        System.out.println("Thank you for playing!");
    }

    public void playAnagramGame() {
        System.out.println("Anagram Game:");
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
                gameUseCase.saveHighScores();
            } else {
                System.out.println("High score for " + difficulty + ": " + gameUseCase.getHighScore(difficulty));
            }

            System.out.print("Type 'quit' to exit or press Enter to continue: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("quit")) {
                break;
            }
        }
    }

    public void checkAnagrams() {
        System.out.print("Enter the first word: ");
        String word1 = scanner.nextLine();

        System.out.print("Enter the second word: ");
        String word2 = scanner.nextLine();

        boolean areAnagrams = gameUseCase.checkAnagrams(word1, word2);

        if (areAnagrams) {
            System.out.println(word1 + " and " + word2 + " are anagrams!");
        } else {
            System.out.println(word1 + " and " + word2 + " are not anagrams.");
        }
    }
}
