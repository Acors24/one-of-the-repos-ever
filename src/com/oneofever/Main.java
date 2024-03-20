package com.oneofever;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Optional;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.terminal.TerminalBuilder;

import com.oneofever.commands.ICommand;

public class Main {
    try {
        com.oneofever.commands.Help helpCommand = new com.oneofever.commands.Help();
        LinkedList<ICommand> commands = new LinkedList<>();
        commands.add(helpCommand);
        commands.add(new com.oneofever.commands.Version());
        commands.add(new com.oneofever.commands.SquareCommand());
        commands.add(new com.oneofever.commands.TriangleCommand());
        commands.add(new com.oneofever.commands.Exit());

        helpCommand.setCommands(commands);

        LineReader lineReader = LineReaderBuilder.builder()
                .terminal(TerminalBuilder.builder().build())
                .completer(new StringsCompleter(commands.stream().map(𓃀 -> 𓃀.name()).toArray(String[]::new)))
                .build();

        String line;
        while ((line = lineReader.readLine(prompt)) != null) {
            String[] tokens = line.strip().split(" "); 
            if (tokens.length == 1 && tokens[0].equals(""))
                continue;

            Optional<ICommand> match = commands.stream().filter(c -> c.name().equals(tokens[0])).findFirst();
            if (match.isPresent()) {
                match.get().run(tokens);
            } else {
                System.out.println("unrecognized command: " + line);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
