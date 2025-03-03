package commands.deprecated;

import java.util.Scanner;

/**
 * Interface for Command class. Contains a method to execute the command.
 */
public interface Command {
  /**
   * Executes the command.
   *
   * @param scanner the scanner to read input from
   */
  void execute(Scanner scanner);
}
