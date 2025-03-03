package commands.deprecated;

import commands.Command2;
import java.util.logging.Logger;
import model.clinic.Clinic;
import model.patient.Patient;
import model.staff.ClinicalStaff;

/**
 * Represents a command to remove a staff member from a patient.
 */
public class RemoveStaffFromPatientCommand implements Command2 {

  private final Clinic clinic;
  private final Logger logger;
  private final Patient patient;
  private final ClinicalStaff staff;

  /**
   * Creates a new RemoveStaffFromPatientCommand with the given clinic and logger.
   *
   * @param clinic the clinic to remove the staff member from the patient
   * @param logger the logger to log the removal
   */
  public RemoveStaffFromPatientCommand(Clinic clinic, Logger logger, Patient patient,
                                       ClinicalStaff staff) {
    this.clinic = clinic;
    this.logger = logger;
    this.patient = patient;
    this.staff = staff;
  }

  @Override
  public void execute() throws IllegalArgumentException {
    if (patient == null || staff == null) {
      throw new IllegalArgumentException("Patient or staff member not found.");
    }
    clinic.removeClinicalStaffFromPatient(staff, patient);
    logger.info("Staff member " + staff.getFullNameWithTitle()
            + " removed from patient " + patient.getFullName());
  }
}
