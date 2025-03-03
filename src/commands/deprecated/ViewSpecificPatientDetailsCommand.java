package commands.deprecated;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import model.clinic.Clinic;
import model.patient.Patient;

/**
 * Represents a command to view specific patient details.
 */
public class ViewSpecificPatientDetailsCommand implements Command {

  private final Clinic clinic;
  private final Logger logger;

  /**
   * Creates a new ViewSpecificPatientDetailsCommand with the given clinic and logger.
   *
   * @param clinic the clinic to view the patient details
   * @param logger the logger to log the viewing of patient details
   */
  public ViewSpecificPatientDetailsCommand(Clinic clinic, Logger logger) {
    this.clinic = clinic;
    this.logger = logger;
  }

  @Override
  public void execute(Scanner scanner) {
    try {
      System.out.println("Enter the number of the patient to view details:");
      List<Patient> patients = clinic.getPatients();
      for (int i = 0; i < patients.size(); i++) {
        System.out.println((i + 1) + ". " + patients.get(i).getFullName());
      }
      final int patientNumber = scanner.nextInt() - 1;
      scanner.nextLine();
      clinic.displayPatientDetails(patients.get(patientNumber));
      logger.info("Patient details displayed.");
    } catch (IllegalArgumentException e) {
      logger.severe(e.getMessage());
      System.out.println("Patient details could not be displayed. Please try again.");
    }

  }
}
