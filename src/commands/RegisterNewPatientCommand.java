package commands;

import java.util.logging.Logger;
import model.clinic.Clinic;
import model.patient.Patient;
import model.patient.VisitRecord;
import view.components.RegisterPatientForm;

/**
 * Represents a command to register a new patient.
 */
public class RegisterNewPatientCommand implements Command2 {
  private final Clinic clinic;
  private final Logger logger;
  private final RegisterPatientForm registerPatientForm;

  /**
   * Creates a new RegisterNewPatientCommand with the given clinic and logger.
   *
   * @param clinic the clinic to register the patient
   * @param logger the logger to log the registration
   */
  public RegisterNewPatientCommand(Clinic clinic, Logger logger,
                                   RegisterPatientForm registerPatientForm) {
    this.clinic = clinic;
    this.logger = logger;
    this.registerPatientForm = registerPatientForm;
  }

  @Override
  public void execute() throws IllegalArgumentException {
    if (clinic.getRoomsList().isEmpty()) {
      logger.severe("Patient registration failed. Please try again.");
      throw new IllegalArgumentException("Load a clinic text file first.");
    }
    if (registerPatientForm.getFirstName().isEmpty()
            || registerPatientForm.getLastName().isEmpty()) {
      logger.severe("Patient registration failed. Please try again.");
      throw new IllegalArgumentException("Patient registration failed. Please try again.");
    }

    if (registerPatientForm.getVisitRecordDateTime() == null
            || registerPatientForm.getVisitRecordComplaint().isEmpty()) {
      logger.severe("Patient registration failed. Please try again.");
      throw new IllegalArgumentException("Visit record registration failed. Please try again.");
    }
    Patient patient = getPatient();
    logger.info("Patient " + patient.getFullName() + " registered.");
    logger.severe("Patient registration failed. Please try again.");
  }

  private Patient getPatient() {
    String firstName = registerPatientForm.getFirstName().trim();
    String lastName = registerPatientForm.getLastName().trim();
    String dob = registerPatientForm.getDob();
    VisitRecord visitRecord = new VisitRecord(
            registerPatientForm.getVisitRecordDateTime(),
            registerPatientForm.getVisitRecordComplaint(),
            registerPatientForm.getVisitRecordTemperature());
    Patient patient = new Patient(firstName, lastName, dob, visitRecord);
    clinic.registerNewPatient(patient);
    registerPatientForm.clearFields();
    return patient;
  }
}
