package commands;

public interface ICommand {
  
  String name();

  String description();

  void run(String[] tokens);

}
