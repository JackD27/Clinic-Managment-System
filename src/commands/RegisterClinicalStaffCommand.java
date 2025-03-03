package commands;

import java.util.logging.Logger;
import model.clinic.Clinic;
import model.staff.ClinicalStaff;
import utils.HelperClass;
import view.components.RegisterStaffMemberForm;


/**
 * Represents a command to register a new clinical staff member.
 */
public class RegisterClinicalStaffCommand implements Command2 {
  private final Clinic clinic;
  private final Logger logger;
  private final RegisterStaffMemberForm form;

  /**
   * Creates a new RegisterClinicalStaffCommand with the given clinic and logger.
   *
   * @param clinic the clinic to register the clinical staff member
   * @param logger the logger to log the registration
   */
  public RegisterClinicalStaffCommand(Clinic clinic, Logger logger, RegisterStaffMemberForm form) {
    this.clinic = clinic;
    this.logger = logger;
    this.form = form;
  }

  @Override
  public void execute() throws IllegalArgumentException {
    if (clinic.getRoomsList().isEmpty()) {
      throw new IllegalArgumentException("Clinic has no rooms. Add a clinic text file.");
    }
    if (form.getFirstName().isEmpty() || form.getLastName().isEmpty()
            || form.getJobName().isEmpty() || form.getEducationLevel().isEmpty()) {
      throw new IllegalArgumentException("Please fill out all fields.");
    }
    String firstName = form.getFirstName().trim();
    String lastName = form.getLastName().trim();
    String jobTitle = form.getJobName().trim().toLowerCase();
    String educationLevel = form.getEducationLevel().trim().toLowerCase();
    String id = new HelperClass().generateRandomNumericString(10);
    ClinicalStaff clinicalStaff = new ClinicalStaff(jobTitle, firstName, lastName,
            educationLevel, id);
    clinic.registerNewStaffMember(clinicalStaff);
    form.clearFields();
    logger.info("Clinical staff " + clinicalStaff.getFullNameWithTitle() + " registered.");
  }
}
