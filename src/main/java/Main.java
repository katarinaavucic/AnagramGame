import interface_adapters.FileHighScoreGateway;
import interface_adapters.GameController;
import views.StartGameMenu;
import use_cases.*;
import entities.AnagramChecker;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HighScoreGatewayBoundary highScoreGateway = new FileHighScoreGateway("high_scores.txt");
        HighScoreManagerInputBoundary highScoreManager = new HighScoreManagerUseCase(highScoreGateway);
        AnagramGameInputBoundary gameUseCase = new AnagramGameUseCase(new AnagramChecker(), scanner, highScoreManager);
        GameController gameController = new GameController(gameUseCase);
        StartGameMenu startGame = new StartGameMenu(gameController);
        startGame.playAnagramGame();
        scanner.close();
    }
}