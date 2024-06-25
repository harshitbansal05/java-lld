package sa.com.barraq.commands;

import sa.com.barraq.OutputPrinter;
import sa.com.barraq.model.Command;
import sa.com.barraq.services.ParkingLotService;

public class ExitCommandExecutor extends CommandExecutor {
    public static String COMMAND_NAME = "exit";

    public ExitCommandExecutor(final ParkingLotService parkingLotService, final OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validate(final Command command) {
        return command.getParams().isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final Command command) {
        outputPrinter.end();
    }
}
