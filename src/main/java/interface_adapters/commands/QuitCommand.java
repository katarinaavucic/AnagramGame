package interface_adapters.commands;

public class QuitCommand implements GameCommand{
    @Override
    public void execute() {
        System.out.println("Quitting the game...");
        System.exit(0); // Terminate the program
    }
}