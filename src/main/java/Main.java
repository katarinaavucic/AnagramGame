import interface_adapters.FileHighScoreGateway;
import interface_adapters.GameController;
import use_cases.*;
import entities.AnagramChecker;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AnagramGameInputBoundary gameUseCase = new AnagramGameUseCase(new AnagramChecker());
        HighScoreGatewayBoundary highScoreGateway = new FileHighScoreGateway("high_scores.txt");
        HighScoreManagerInputBoundary highScoreManager = new HighScoreManagerUseCase(highScoreGateway);
        GameController gameController = new GameController(scanner, gameUseCase, highScoreManager);
        gameController.startGame();
        scanner.close();
    }
}

