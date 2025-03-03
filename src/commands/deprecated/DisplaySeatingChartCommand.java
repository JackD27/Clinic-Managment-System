package commands.deprecated;

import java.util.Scanner;
import java.util.logging.Logger;
import model.clinic.Clinic;

/**
 * Represents a command to display the seating chart.
 */
public class DisplaySeatingChartCommand implements Command {

  private final Clinic clinic;
  private final Logger logger;

  /**
   * Creates a new DisplaySeatingChartCommand with the given clinic and logger.
   *
   * @param clinic the clinic to display the seating chart
   * @param logger the logger to log the seating chart display
   */
  public DisplaySeatingChartCommand(Clinic clinic, Logger logger) {
    this.clinic = clinic;
    this.logger = logger;
  }

  @Override
  public void execute(Scanner scanner) {
    try {
      System.out.println("Displaying seating chart...");
      clinic.seatingChart();
      logger.info("Seating chart displayed.");
    } catch (IllegalArgumentException e) {
      logger.severe(e.getMessage());
      System.out.println("Seating chart could not be displayed. Please try again.");
    }

  }
}
