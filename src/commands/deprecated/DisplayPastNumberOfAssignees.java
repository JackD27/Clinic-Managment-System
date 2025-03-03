package commands.deprecated;

import java.util.Scanner;
import java.util.logging.Logger;
import model.clinic.Clinic;

/**
 * Represents a command to display the past number of assignees.
 */
public class DisplayPastNumberOfAssignees implements Command {

  private final Clinic clinic;
  private final Logger logger;

  /**
   * Creates a new DisplayPastNumberOfAssignees with the given clinic and logger.
   *
   * @param clinic the clinic to display the past number of assignees
   * @param logger the logger to log the past number of assignees display
   */

  public DisplayPastNumberOfAssignees(Clinic clinic, Logger logger) {
    this.clinic = clinic;
    this.logger = logger;
  }

  @Override
  public void execute(Scanner scanner) {
    try {
      clinic.displayTotalNumberOfPastAssignees();
      logger.info("Past number of assignees displayed.");
    } catch (IllegalArgumentException e) {
      logger.severe(e.getMessage());
      System.out.println("Past number of assignees could not be displayed. Please try again.");
    }
  }
}
