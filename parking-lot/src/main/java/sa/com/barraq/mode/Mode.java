package sa.com.barraq.mode;

import sa.com.barraq.commands.CommandExecutor;
import sa.com.barraq.commands.CommandExecutorFactory;
import sa.com.barraq.exceptions.InvalidCommandException;
import sa.com.barraq.model.Command;

import java.io.IOException;

/**
 * Interface for mode of the program in which it can be run.
 */
public abstract class Mode {

    private final CommandExecutorFactory commandExecutorFactory;

    public Mode(final CommandExecutorFactory commandExecutorFactory) {
        this.commandExecutorFactory = commandExecutorFactory;
    }

    /**
     * Helper method to process a command. It basically uses {@link CommandExecutor} to run the given
     * command.
     *
     * @param command Command to be processed.
     */
    protected void processCommand(final Command command) {
        final CommandExecutor commandExecutor = commandExecutorFactory.getCommandExecutor(command);
        if (commandExecutor.validate(command)) {
            commandExecutor.execute(command);
        } else {
            throw new InvalidCommandException();
        }
    }

    public abstract void process() throws IOException;
}
