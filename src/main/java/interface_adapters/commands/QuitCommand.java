package interface_adapters.commands;

/**
 * A class that passes a command from the StartGameMenu to the System.
 */
public class QuitCommand implements GameCommand{
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        System.out.println("Quitting the game...");
        System.exit(0); // Terminate the program
    }
}