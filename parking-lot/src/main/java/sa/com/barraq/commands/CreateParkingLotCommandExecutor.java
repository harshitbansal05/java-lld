package sa.com.barraq.commands;

import sa.com.barraq.OutputPrinter;
import sa.com.barraq.model.Command;
import sa.com.barraq.model.ParkingLot;
import sa.com.barraq.model.parking.strategy.NaturalOrderingParkingStrategy;
import sa.com.barraq.services.ParkingLotService;
import sa.com.barraq.validator.IntegerValidator;

import java.util.List;

public class CreateParkingLotCommandExecutor extends CommandExecutor {
    public static String COMMAND_NAME = "create_parking_lot";

    public CreateParkingLotCommandExecutor(final ParkingLotService parkingLotService, final OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validate(final Command command) {
        final List<String> params = command.getParams();
        if (params.size() != 1) {
            return false;
        }
        return IntegerValidator.isInteger(params.getFirst());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final Command command) {
        final int parkingLotCapacity = Integer.parseInt(command.getParams().getFirst());
        final ParkingLot parkingLot = new ParkingLot(parkingLotCapacity);
        parkingLotService.createParkingLot(parkingLot, new NaturalOrderingParkingStrategy());
        outputPrinter.printWithNewLine("Created a parking lot with " + parkingLot.getCapacity() + " slots");
    }
}
