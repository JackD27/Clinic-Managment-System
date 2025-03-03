package commands.deprecated;

import java.util.Scanner;
import java.util.logging.Logger;
import model.clinic.Clinic;

/**
 * Represents a command to view patients and their assigned staff members.
 */
public class ViewPatientsAndHelpersCommand implements Command {

  private final Clinic clinic;
  private final Logger logger;

  /**
   * Creates a new ViewPatientsAndHelpersCommand with the given clinic and logger.
   *
   * @param clinic the clinic to view patients and their assigned staff members
   * @param logger the logger to log the viewing of patients and their assigned staff members
   */
  public ViewPatientsAndHelpersCommand(Clinic clinic, Logger logger) {
    this.clinic = clinic;
    this.logger = logger;
  }

  @Override
  public void execute(Scanner scanner) {
    try {
      System.out.println("Displaying patients and their assigned staff members...");
      clinic.displayPatientAndHelpers();
      logger.info("Patients and their assigned staff members displayed.");
    } catch (IllegalArgumentException e) {
      logger.severe(e.getMessage());
      System.out.println("Patients and their assigned staff members could not be displayed. "
              + "Please try again.");
    }

  }
}
