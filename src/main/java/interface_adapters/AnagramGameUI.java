package interface_adapters;

import use_cases.AnagramGame;

import java.util.Scanner;

public class AnagramGameUI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Anagram Game!");
        System.out.print("Enter the first word: ");
        String word1 = scanner.nextLine();

        System.out.print("Enter the second word: ");
        String word2 = scanner.nextLine();

        AnagramGame anagramGame = new AnagramGame();
        String result = anagramGame.playAnagramGame(word1, word2);

        System.out.println(result);

        scanner.close();
    }
}
