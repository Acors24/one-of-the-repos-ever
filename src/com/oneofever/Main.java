/* (C)2024 - one-of-the-teams-ever */
package com.oneofever;

import com.oneofever.commands.Command;
import com.oneofever.parsing.Parser;
import java.io.IOException;
import java.util.ArrayList;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.terminal.TerminalBuilder;

public class Main {
    public static void main(String[] args) {
        final String PROMPT = "> ";
        try {
            com.oneofever.commands.Help helpCommand = new com.oneofever.commands.Help();
            ArrayList<Command> commands = new ArrayList<>();
            commands.add(helpCommand);
            commands.add(new com.oneofever.commands.Version());
            commands.add(new com.oneofever.commands.HistoryCommand());
            commands.add(new com.oneofever.commands.SquareCommand());
            commands.add(new com.oneofever.commands.TriangleCommand());
            commands.add(new com.oneofever.commands.RectangleCommand());
            commands.add(new com.oneofever.commands.RhombusCommand());
            commands.add(new com.oneofever.commands.Exit());
            helpCommand.setCommands(commands);
            LineReader lineReader =
                    LineReaderBuilder.builder()
                            .terminal(TerminalBuilder.builder().build())
                            .completer(
                                    new StringsCompleter(
                                            commands.stream()
                                                    .map(c -> c.name())
                                                    .toArray(String[]::new)))
                            .build();
            String line;
            Parser parser = new Parser(commands);
            while ((line = lineReader.readLine(PROMPT)) != null) {
                try {
                    parser.parse(line);
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
