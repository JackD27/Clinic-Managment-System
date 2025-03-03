package model.staff;

import java.util.HashSet;
import model.patient.Patient;

/**
 * ClinicalStaff class that represents a clinical staff member in a clinic. A clinical staff member
 * has a job title, first name, last name, education level, and extra field.
 */
public class ClinicalStaff extends Staff implements ClinicalStaffInterface {
  private final String nationalProviderId;
  private final HashSet<Patient> patientsAssignedFromPast;

  /**
   * Staff constructor that initializes the job title, first name, last name, education level, and
   * extra field of the staff.
   *
   * @param jobTitle       the job title of the staff
   * @param firstName      the first name of the staff
   * @param lastName       the last name of the staff
   * @param educationLevel the education level of the staff
   * @param nationalProviderId    the extra field of the staff
   */
  public ClinicalStaff(String jobTitle, String firstName, String lastName, String educationLevel,
                       String nationalProviderId) {
    super(jobTitle, firstName, lastName, educationLevel);
    this.nationalProviderId = nationalProviderId;
    this.patientsAssignedFromPast = new HashSet<>();
  }

  /**
   * Returns full name of the clinical staff member with their job title.
   *
   * @return the full name of the clinical staff member with their job title.
   */
  public String getFullNameWithTitle() {
    return super.getJobTitle().getTitle() + " " + super.getFullName();
  }

  @Override
  public String toString() {
    return getFullNameWithTitle() + "NPI: " + nationalProviderId;
  }

  /**
   * Returns the staff type of the clinical staff member.
   *
   * @return the staff type of the clinical staff member.
   */
  public StaffType getStaffType() {
    return StaffType.CLINICAL;
  }

  @Override
  public void addAssignedPatient(Patient patient) {
    if (patient == null) {
      throw new IllegalArgumentException("Patient cannot be null");
    }
    patientsAssignedFromPast.add(patient);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ClinicalStaff that)) {
      return false;
    }
    return nationalProviderId.equals(that.nationalProviderId);
  }

  @Override
  public int hashCode() {
    return nationalProviderId.hashCode();
  }

  /**
   * Returns the number of patients assigned to the clinical staff member from the past.
   *
   * @return the number of patients assigned to the clinical staff member from the past.
   */
  public int getNumPatientsAssignedFromPast() {
    return patientsAssignedFromPast.size();
  }
}
