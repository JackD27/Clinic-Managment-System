package commands.deprecated;

import java.util.Scanner;
import java.util.logging.Logger;
import model.clinic.Clinic;

/**
 * Represents a command to display the recent frequent visitors within the past 365 days.
 */
public class DisplayRecentFrequentVisitorsCommand implements Command {

  private final Clinic clinic;
  private final Logger logger;

  /**
   * Creates a new DisplayRecentFrequentVisitorsCommand with the given clinic and logger.
   *
   * @param clinic the clinic to display the recent frequent visitors
   * @param logger the logger to log the recent frequent visitors display
   */
  public DisplayRecentFrequentVisitorsCommand(Clinic clinic, Logger logger) {
    this.clinic = clinic;
    this.logger = logger;
  }

  @Override
  public void execute(Scanner scanner) {
    try {
      System.out.println("Displaying the Clinic's most recent frequent visitors "
              + "in the past 365 days.");
      clinic.displayRecentFrequentVisitors();
      logger.info("Recent frequent visitors displayed.");
    } catch (IllegalArgumentException e) {
      logger.severe(e.getMessage());
      System.out.println("Recent frequent visitors could not be displayed. Please try again.");
    }
  }
}
