package interface_adapters.commands;

/**
 * An interface that several Command classes implement. This is the Command design pattern, where multiple classes send
 * commands from StartGameMenu to GameController or System, with no concern for what the details of these commands.
 */
public interface GameCommand {

    /**
     * Calls another class' method to pass the command along.
     */
    void execute();
}
