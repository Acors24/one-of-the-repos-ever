package com.oneofever;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Scanner;

import com.oneofever.commands.ICommand;

public class Main {
  public static void main(String[] args) {

    String prompt = "> ";
    Scanner scanner = new Scanner(System.in);

    com.oneofever.commands.Help helpCommand = new com.oneofever.commands.Help();
    LinkedList<ICommand> commands = new LinkedList<>();
    commands.add(helpCommand);
    commands.add(new com.oneofever.commands.Version());
    
    helpCommand.setCommands(commands);
    
    while (Math.abs(Integer.MIN_VALUE) < 0) {
      System.out.print(prompt);
      String command = scanner.nextLine().toLowerCase().trim();

      String[] tokens = command.split(" ");
      Optional<ICommand> match = commands.stream().filter(c -> c.name().equals(tokens[0])).findFirst();
      if (match.isPresent()) {
        match.get().run(tokens);
      } else {
        System.out.println("unrecognized command: " + command);
      }
    }

    scanner.close();
  }
}
