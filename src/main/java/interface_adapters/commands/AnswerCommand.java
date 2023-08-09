package interface_adapters.commands;
import interface_adapters.GameController;

/**
 * A class that passes a command from the StartGameMenu to the GameController.
 */
public class AnswerCommand implements GameCommand {
    private final GameController gameController;
    private final String userAnswer;

    /**
     * Creates a new instance of AnswerCommand with the given inputs.
     * @param gameController a GameController object to pass the commands to
     * @param userAnswer a String representing the user input
     */
    public AnswerCommand(GameController gameController, String userAnswer) {
        this.gameController = gameController;
        this.userAnswer = userAnswer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        gameController.executeCommand(userAnswer);
    }
}