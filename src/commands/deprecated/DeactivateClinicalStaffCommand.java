package commands.deprecated;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import model.clinic.Clinic;
import model.staff.ClinicalStaff;

/**
 * Represents a command to deactivate a clinical staff member.
 */
public class DeactivateClinicalStaffCommand implements Command {
  private final Clinic clinic;
  private final Logger logger;

  /**
   * Creates a new DeactivateClinicalStaffCommand with the given clinic and logger.
   *
   * @param clinic the clinic to deactivate the clinical staff member
   * @param logger the logger to log the deactivation
   */
  public DeactivateClinicalStaffCommand(Clinic clinic, Logger logger) {
    this.clinic = clinic;
    this.logger = logger;
  }

  @Override
  public void execute(Scanner scanner) {
    try {
      System.out.println("Enter the number of the clinical staff member to deactivate:");
      List<ClinicalStaff> clinicalStaff = clinic.getClinicalStaff();
      for (int i = 0; i < clinicalStaff.size(); i++) {
        System.out.println((i + 1) + ". " + clinicalStaff.get(i).getFullNameWithTitle());
      }
      final int staffNumber = scanner.nextInt() - 1;
      scanner.nextLine();
      clinic.deactivateClinicalStaffMember(clinicalStaff.get(staffNumber));
      logger.info("Clinical staff " + clinicalStaff.get(staffNumber).getFullNameWithTitle()
              + " deactivated.");
    } catch (IllegalArgumentException e) {
      logger.severe(e.getMessage());
      System.out.println("Clinical staff deactivation failed. Please try again.");
    }

  }
}
