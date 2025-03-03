package commands;

import java.util.logging.Logger;
import model.clinic.Clinic;
import model.patient.Patient;
import model.patient.VisitRecord;

/**
 * Represents a command to add a record to an existing patient.
 */
public class AddRecordToExistingPatientCommand implements Command2 {

  private final Clinic clinic;
  private final Logger logger;
  private final Patient patient;
  private final VisitRecord visitRecord;

  /**
   * Creates a new AddRecordToExistingPatientCommand with the given clinic and logger.
   *
   * @param clinic the clinic to add the record to an existing patient
   * @param logger the logger to log the record addition
   */
  public AddRecordToExistingPatientCommand(Clinic clinic, Logger logger,
                                           Patient patient, VisitRecord visitRecord) {
    this.clinic = clinic;
    this.logger = logger;
    this.patient = patient;
    this.visitRecord = visitRecord;
  }

  @Override
  public void execute() throws IllegalArgumentException {
    if (patient == null || visitRecord == null) {
      throw new IllegalArgumentException("Patient or visit record not found.");
    }
    clinic.assignRecordToPatient(patient, visitRecord);
    logger.info("Record added to patient " + patient.getFullName() + ". Record: "
            + visitRecord);
  }
}
