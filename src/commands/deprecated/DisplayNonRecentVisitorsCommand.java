package commands.deprecated;

import java.util.Scanner;
import java.util.logging.Logger;
import model.clinic.Clinic;

/**
 * Represents a command to display non-recent visitors.
 */
public class DisplayNonRecentVisitorsCommand implements Command {

  private final Clinic clinic;
  private final Logger logger;

  /**
   * Creates a new DisplayNonRecentVisitorsCommand with the given clinic and logger.
   *
   * @param clinic the clinic to display the non-recent visitors
   * @param logger the logger to log the non-recent visitors display
   */
  public DisplayNonRecentVisitorsCommand(Clinic clinic, Logger logger) {
    this.clinic = clinic;
    this.logger = logger;
  }

  @Override
  public void execute(Scanner scanner) {
    try {
      System.out.println("Displaying non-recent visitors. "
              + "Patients who have not visited in the last 365 days.");
      clinic.displayNonRecentFrequentVisitors();
      logger.info("Non-recent visitors displayed.");
    } catch (IllegalArgumentException e) {
      logger.severe(e.getMessage());
      System.out.println("Non-recent visitors could not be displayed. Please try again.");
    }
  }
}
