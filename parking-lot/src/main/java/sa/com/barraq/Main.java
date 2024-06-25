package sa.com.barraq;

import sa.com.barraq.commands.CommandExecutorFactory;
import sa.com.barraq.exceptions.InvalidModeException;
import sa.com.barraq.mode.InteractiveMode;
import sa.com.barraq.services.ParkingLotService;

import java.io.IOException;

public class Main {
    public static void main(final String[] args) throws IOException {
        final ParkingLotService parkingLotService = new ParkingLotService();
        final CommandExecutorFactory commandExecutorFactory = new CommandExecutorFactory(parkingLotService);

        if (isInteractiveMode(args)) {
            new InteractiveMode(commandExecutorFactory).process();
        } else {
            throw new InvalidModeException();
        }
    }

    private static boolean isInteractiveMode(final String[] args) {
        return args.length == 0;
    }
}
