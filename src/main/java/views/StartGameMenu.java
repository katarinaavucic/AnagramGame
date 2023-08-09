package views;
import interface_adapters.GameController;
import interface_adapters.commands.AnswerCommand;
import interface_adapters.commands.GameCommand;
import interface_adapters.commands.QuitCommand;
import java.util.Scanner;

/**
 * A class that acts as the main menu and delegates commands to the GameController or System depending on what the user
 * would like to do. It contains a GameController object to execute the games.
 */
public class StartGameMenu {
    private final GameController gameController;

    /**
     * Creates a new instance of StartGameMenu with the specified inputs.
     * @param gameController a GameController that will be used to execute commands
     */
    public StartGameMenu(GameController gameController) {
        this.gameController = gameController;
    }

    /**
     * Reads user input and processes commands based off the response.
     */
    public void executeUserInput() {
        System.out.println("Welcome to the Anagram Game!");
        while (true) {
            System.out.println("Select an option:\n1. Play an anagram game\n2. Check if words are anagrams\n3. Quit");
            Scanner scanner = new Scanner(System.in);
            String option = scanner.nextLine();

            switch (option) {
                // if case 1 or 2, user wants to play a game
                case "1":
                case "2":
                    AnswerCommand answerCommand = new AnswerCommand(gameController, option);
                    answerCommand.execute();
                    break;
                // if case 3, user wants to quit
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