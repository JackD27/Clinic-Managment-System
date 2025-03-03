package commands.deprecated;

import java.util.Scanner;
import java.util.logging.Logger;
import model.clinic.Clinic;
import model.staff.ClinicalStaff;

/**
 * Represents a command to display a staff member's patients.
 */
public class DisplayStaffMemberPatientsCommand implements Command {

  private final Clinic clinic;
  private final Logger logger;

  /**
   * Creates a new DisplayStaffMemberPatientsCommand with the given clinic and logger.
   *
   * @param clinic the clinic to display the staff member's patients
   * @param logger the logger to log the staff member's patients display
   */
  public DisplayStaffMemberPatientsCommand(Clinic clinic, Logger logger) {
    this.clinic = clinic;
    this.logger = logger;
  }

  @Override
  public void execute(Scanner scanner) {
    try {
      System.out.println("Enter the number of the staff member to display their patients:");
      for (int i = 0; i < clinic.getClinicalStaff().size(); i++) {
        System.out.println((i + 1) + ". " + clinic.getClinicalStaff().get(i)
                .getFullNameWithTitle());
      }
      final int staffNumber = scanner.nextInt() - 1;
      scanner.nextLine();
      ClinicalStaff staff = clinic.getClinicalStaff().get(staffNumber);
      clinic.displayPatientsForClinicalStaff(staff);
      logger.info("Staff member " + staff.getFullNameWithTitle()
              + "'s patients displayed.");
    } catch (IllegalArgumentException e) {
      logger.severe(e.getMessage());
      System.out.println("Staff member's patients could not be displayed. Please try again.");
    }
  }
}
