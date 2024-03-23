package com.oneofever.parsing;

import com.oneofever.commands.Command;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;

public class Parser {
    ArrayList<Command> commands;

    public Parser(ArrayList<Command> commands) {
        this.commands = commands;
    }

    public void parse(String input) throws IllegalArgumentException {
        Iterator<String> split =
                Arrays.asList(input.toLowerCase().trim().split("\\s+")).stream()
                        .map(str -> str.trim())
                        .iterator();

        String commandName = split.next();
        Optional<Command> match =
                commands.stream().filter(com -> com.name().equals(commandName)).findFirst();
        if (match.isEmpty()) {
            throw new IllegalArgumentException("Unrecognized command: " + commandName);
        }

        Command command = match.get();
        Fulfillable tree = command.getArgumentTree();
        ArgumentHandler handler = new ArgumentHandler(tree);

        while (split.hasNext()) {
            String token = split.next();

            try {
                Double value = Double.parseDouble(token);
                handler.handleValue(value);
            } catch (NumberFormatException ex1) {
                try {
                    handler.handleArgumentName(token);
                } catch (IllegalArgumentException ex2) {
                    System.err.println(ex2.getMessage());
                    return;
                }
            } catch (IllegalArgumentException ex) {
                System.err.println(ex.getMessage());
                return;
            }
        }

        if (handler.argumentTree.isFulfilled(handler.values())) {
            command.run(handler.values());
        } else {
            System.out.println("Wrong amount of data.");
            System.out.println(command.usage());
        }
    }
}
