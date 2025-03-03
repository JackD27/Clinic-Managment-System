package commands.deprecated;

import java.util.Scanner;
import java.util.logging.Logger;
import model.clinic.Clinic;
import model.patient.Patient;

/**
 * Represents a command to assign a staff member to a patient.
 */
public class AssignStaffToPatientCommand implements Command {
  private final Clinic clinic;
  private final Logger logger;

  /**
   * Creates a new AssignStaffToPatientCommand with the given clinic and logger.
   *
   * @param clinic the clinic to assign the staff member to a patient
   * @param logger the logger to log the assignment
   */
  public AssignStaffToPatientCommand(Clinic clinic, Logger logger) {
    this.clinic = clinic;
    this.logger = logger;
  }

  @Override
  public void execute(Scanner scanner) {
    try {
      System.out.println("Enter the number of the patient to assign a staff member to:");
      for (int i = 0; i < clinic.getPatients().size(); i++) {
        System.out.println((i + 1) + ". " + clinic.getPatients().get(i).getFullName() + " Number "
                + "of staff assigned: " + clinic.getPatients().get(i).getStaffHelpers().size());
      }
      final int patientNumber = scanner.nextInt() - 1;
      scanner.nextLine();
      Patient patient = clinic.getPatients().get(patientNumber);
      System.out.println("Enter the number of the staff member to assign to the patient:");
      if (clinic.getRemainingClinicalStaffNotUsedByPatient(patient).isEmpty()) {
        System.out.println("No staff members available to assign to the patient.");
        return;
      }
      for (int i = 0; i < clinic.getRemainingClinicalStaffNotUsedByPatient(patient).size(); i++) {
        System.out.println((i + 1) + ". "
                + clinic.getRemainingClinicalStaffNotUsedByPatient(patient)
                .get(i).getFullNameWithTitle());
      }
      final int staffNumber = scanner.nextInt() - 1;
      scanner.nextLine();
      clinic.assignStaffToPatient(clinic.getPatients().get(patientNumber),
              clinic.getRemainingClinicalStaffNotUsedByPatient(patient).get(staffNumber));
      logger.info("Staff " + clinic.getClinicalStaff().get(staffNumber).getFullNameWithTitle()
              + " assigned to patient " + clinic.getPatients().get(patientNumber).getFullName());
    } catch (IllegalArgumentException e) {
      logger.severe(e.getMessage());
      System.out.println("Staff could not be assigned to the patient. Please try again.");
    }

  }
}
