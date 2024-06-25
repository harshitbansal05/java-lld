package sa.com.barraq.model;

import lombok.Getter;
import sa.com.barraq.exceptions.InvalidCommandException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Command {

    private static final String SPACE = " ";
    private final String commandName;
    private final List<String> params;

    /**
     * Constructor. It takes the input line and parses the command name and param out of it. If the
     * command or its given params are not valid, then {@link InvalidCommandException} is thrown.
     *
     * @param inputLine Given input command line.
     */
    public Command(final String inputLine) {
        final List<String> tokensList = Arrays.stream(inputLine.trim().split(SPACE))
                .map(String::trim)
                .filter(token -> (!token.isEmpty())).collect(Collectors.toList());

        if (tokensList.isEmpty()) {
            throw new InvalidCommandException();
        }

        commandName = tokensList.getFirst().toLowerCase();
        tokensList.removeFirst();
        params = tokensList;
    }
}
