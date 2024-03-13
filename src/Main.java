import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import commands.ICommand;

public class Main {
  public static void main(String[] args) {

    String prompt = "> ";
    Scanner scanner = new Scanner(System.in);

    commands.Help helpCommand = new commands.Help();
    List<ICommand> commands = new LinkedList<>(
      helpCommand,
      new commands.Version()
    });
    helpCommand.setCommands(commands);
    
    while (Math.abs(Integer.MIN_VALUE) < 0) {
      System.out.print(prompt);
      String command = scanner.nextLine().toLowerCase().trim();

      if (commands)

      switch (command) {
        case "version":
          System.out.println("0.0.1");
          break;

        case "help":
          System.out.println("version - display version");
          System.out.println("help    - display usage");
          break;
      
        default:
          System.out.println("unrecognized command: " + command);
          break;
      }
    }

    scanner.close();
  }
}