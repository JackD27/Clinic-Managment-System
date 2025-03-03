package commands;

import java.util.logging.Logger;
import model.clinic.Clinic;
import model.staff.Staff;

/**
 * Represents a command to deactivate a staff member.
 */
public class DeactivateStaffMemberCommand implements Command2 {
  private final Clinic clinic;
  private final Logger logger;
  private final Staff staffMember;

  /**
   * Creates a new DeactivateClinicalStaffCommand with the given clinic and logger.
   *
   * @param clinic the clinic to deactivate the clinical staff member
   * @param logger the logger to log the deactivation
   * @param staffMember the staff member to deactivate
   */
  public DeactivateStaffMemberCommand(Clinic clinic, Logger logger, Staff staffMember) {
    this.clinic = clinic;
    this.logger = logger;
    this.staffMember = staffMember;
  }

  @Override
  public void execute() throws IllegalArgumentException {
    if (clinic.getRoomsList().isEmpty()) {
      throw new IllegalArgumentException("Load a clinic file.");
    }
    if (staffMember == null) {
      throw new IllegalArgumentException("Staff member not found.");
    }
    clinic.deactivateStaffMember(staffMember);
    logger.info("Staff " + staffMember.getFullName() + " deactivated.");
  }
}

