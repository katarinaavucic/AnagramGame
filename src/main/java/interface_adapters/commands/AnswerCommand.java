package interface_adapters.commands;

import interface_adapters.GameController;

public class AnswerCommand implements GameCommand {
    private final GameController gameController;
    private final String userAnswer;

    public AnswerCommand(GameController gameController, String userAnswer) {
        this.gameController = gameController;
        this.userAnswer = userAnswer;
    }

    @Override
    public void execute() {
        gameController.executeCommand(userAnswer);
    }
}
