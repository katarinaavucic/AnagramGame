import interface_adapters.FileHighScoreGateway;
import interface_adapters.GameController;
import use_cases.gameplay.AnagramGameInputBoundary;
import use_cases.gameplay.AnagramGameUseCase;
import use_cases.saving.HighScoreGatewayBoundary;
import use_cases.saving.HighScoreManagerInputBoundary;
import use_cases.saving.HighScoreManagerUseCase;
import views.StartGameMenu;
import entities.AnagramChecker;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // create all necessary classes
        Scanner scanner = new Scanner(System.in);
        HighScoreGatewayBoundary highScoreGateway = new FileHighScoreGateway("high_scores.txt");
        HighScoreManagerInputBoundary highScoreManager = new HighScoreManagerUseCase(highScoreGateway);
        AnagramGameInputBoundary gameUseCase = new AnagramGameUseCase(new AnagramChecker(), scanner, highScoreManager);
        GameController gameController = new GameController(gameUseCase);
        StartGameMenu startGame = new StartGameMenu(gameController);

        // begin game
        startGame.executeUserInput();

        // end game
        scanner.close();
    }
}