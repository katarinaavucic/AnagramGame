import interface_adapters.GameController;
import use_cases.AnagramGameUseCase;
import entities.AnagramChecker;

import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AnagramGameUseCase gameUseCase = new AnagramGameUseCase(new AnagramChecker());
        GameController gameController = new GameController(scanner, gameUseCase);
        gameController.startGame();
        scanner.close();
    }
}
