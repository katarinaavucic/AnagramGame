package views;

import interface_adapters.GameController;
import interface_adapters.commands.AnswerCommand;
import interface_adapters.commands.GameCommand;
import interface_adapters.commands.QuitCommand;

import java.util.Scanner;

public class StartGameMenu {
    private final GameController gameController;

    public StartGameMenu(GameController gameController) {
        this.gameController = gameController;
    }

    public void playAnagramGame() {
        System.out.println("Welcome to the Anagram Game!");

        while (true) {
            System.out.println("Select an option:\n1. Play an anagram game\n2. Check if words are anagrams\n3. Quit");
            Scanner scanner = new Scanner(System.in);
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                case "2":
                    AnswerCommand answerCommand = new AnswerCommand(gameController, option);
                    answerCommand.execute();
                    break;
                case "3":
                    GameCommand quitCommand = new QuitCommand();
                    quitCommand.execute();
                    break;
                default:
                    System.out.println("Invalid option. Please select again.");
            }
        }
    }
}
