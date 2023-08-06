import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Anagram Game!");
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

            System.out.print("Type 'quit' to exit or press Enter to continue: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("quit")) {
                break;
            }
        }

        System.out.println("Thank you for playing! Final score: " + totalScore);
        scanner.close();
    }

    public static String getDifficulty(Scanner scanner) {
        System.out.println("Choose difficulty level (easy, medium, hard) or type 'quit' to exit:");
        return scanner.nextLine().toLowerCase();
    }

    public static String getRandomWord(String difficulty) {
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

    public static String shuffleWord(String word) {
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

    public static int calculateScore(long elapsedTime, String difficulty) {
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
}
