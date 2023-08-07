package interface_adapters;

import use_cases.gameplay.AnagramGameInputBoundary;

/**
 * A class that executes the command from the StartGameMenu. It contains an AnagramGameUseCase class which it will call
 * to play an anagram game.
 */
public class GameController {
    private final AnagramGameInputBoundary gameUseCase;

    /**
     * Creates a new GameController with the specified data.
     * @param gameUseCase an AnagramGameUseCase object that it will call to create a new round of an anagram game
     */
    public GameController(AnagramGameInputBoundary gameUseCase) {
        this.gameUseCase = gameUseCase;
    }

    /**
     * Executes the command from StartGameMenu to create one of the anagram games.
     * @param userAnswer a String containing the user's input of which game they would like to play
     */
    public void executeCommand(String userAnswer) {
        switch (userAnswer) {
            case "1":
                gameUseCase.playAnagramGame();
                break;
            case "2":
                gameUseCase.checkAnagrams();
                break;
            default:
                System.out.println("Invalid option. Please select again.");
                break;
        }
    }
}