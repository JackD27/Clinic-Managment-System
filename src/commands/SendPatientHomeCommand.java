package commands;

import java.util.logging.Logger;
import model.clinic.Clinic;
import model.patient.Patient;
import model.staff.ClinicalStaff;

/**
 * Represents a command to send a patient home.
 */
public class SendPatientHomeCommand implements Command2 {
  private final Clinic clinic;
  private final Logger logger;
  private final Patient patient;
  private final ClinicalStaff staff;

  /**
   * Creates a new SendPatientHomeCommand with the given clinic and logger.
   *
   * @param clinic the clinic to send the patient home
   * @param logger the logger to log the patient being sent home
   */
  public SendPatientHomeCommand(Clinic clinic, Logger logger, Patient patient,
                                ClinicalStaff staff) {
    this.clinic = clinic;
    this.logger = logger;
    this.patient = patient;
    this.staff = staff;
  }

  @Override
  public void execute() throws IllegalArgumentException {
    try {
      clinic.sendPatientHome(patient, staff);
      logger.info("Patient " + patient.getFullName() + " has been "
              + "sent home by " + staff.getFullNameWithTitle());
    } catch (IllegalArgumentException e) {
      logger.severe(e.getMessage());
      System.out.println("Patient could not be sent home. Please try again.");
    }
  }
}
