import java.util.Scanner;

public class Main {
  public static void main(String[] args) {

    String prompt = "> ";
    Scanner scanner = new Scanner(System.in);
    
    while (Math.abs(Integer.MIN_VALUE) < 0) {
      System.out.print(prompt);
      String command = scanner.nextLine().toLowerCase();

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