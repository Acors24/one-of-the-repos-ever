/* (C)2024 - one-of-the-teams-ever */
package com.oneofever;

import com.oneofever.commands.AbstractCommand;
import com.oneofever.parser.ParseException;
import com.oneofever.parser.Parser;
import java.io.IOException;
import java.util.LinkedList;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.terminal.TerminalBuilder;

public class Main {
    public static void main(String[] args) {
        final String PROMPT = "> ";
        try {
            com.oneofever.commands.Help helpCommand = new com.oneofever.commands.Help();
            LinkedList<AbstractCommand> commands = new LinkedList<>();
            commands.add(helpCommand);
            commands.add(new com.oneofever.commands.Version());
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
            Parser parser = new Parser(commands);
            String line;
            while ((line = lineReader.readLine(PROMPT)) != null) {
                String[] tokens = line.strip().split(" ");
                if (tokens.length == 1 && tokens[0].isEmpty()) continue;
                AbstractCommand parsed = null;
                try {
                    parsed = parser.parse(tokens);
                    parsed.run();
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                    parsed = ex.getCommand();
                    if (parsed != null) {
                        String usage = parsed.usage();
                        if (usage != null) System.out.println(usage);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
