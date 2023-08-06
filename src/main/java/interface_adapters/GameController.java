package interface_adapters;

import use_cases.gameplay.AnagramGameInputBoundary;

public class GameController {
    private final AnagramGameInputBoundary gameUseCase;

    public GameController(AnagramGameInputBoundary gameUseCase) {
        this.gameUseCase = gameUseCase;
    }

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