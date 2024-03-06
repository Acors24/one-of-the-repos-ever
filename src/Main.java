public class Main {
  public static void main(String[] args) {
    if (args.length > 0) {
      switch (args[0]) {
        case "--version":
          System.out.println("0.0.1");
          break;

        case "--help":
          System.out.println("--version - display version");
          System.out.println("--help    - display usage");
          break;
      
        default:
          System.out.println("unrecognized argument: " + args[0]);
          break;
      }
    } else {
      System.out.println("missing argument; try --help");
    }
  }
}