package model.patient;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import model.staff.ClinicalStaff;

/**
 * Patient class that represents a patient in a clinic. A patient has a first name, last name, date
 * of birth, and a room.
 */
public class Patient implements PatientInterface2 {
  private final String firstName;
  private final String lastName;
  private final String dob;
  private final Set<ClinicalStaff> staffHelpers;
  private final Set<VisitRecord> visitRecords;

  /**
   * Creates a new Patient object with the given first name, last name, and date of birth.
   *
   * @param firstName the first name of the patient
   * @param lastName the last name of the patient
   * @param dob the date of birth of the patient
   */
  public Patient(String firstName, String lastName, String dob) {
    this(firstName, lastName, dob, null);
  }

  /**
   * Creates a new Patient object with the given first name, last name, date of birth, and visit
   * record.
   *
   * @param firstName the first name of the patient
   * @param lastName the last name of the patient
   * @param dob the date of birth of the patient
   * @param visitRecord the visit record of the patient
   */
  public Patient(String firstName, String lastName, String dob, VisitRecord visitRecord) {
    if (firstName == null || lastName == null || dob == null) {
      throw new IllegalArgumentException("Patient information cannot be empty");
    }
    this.firstName = firstName;
    this.lastName = lastName;
    this.dob = dob;
    this.staffHelpers = new HashSet<>();
    this.visitRecords = new TreeSet<>();
    if (visitRecord != null) {
      visitRecords.add(visitRecord);
    }
  }

  /**
   * Returns the full name of the patient.
   *
   * @return the full name of the patient.
   */
  public String getFullName() {
    return firstName + " " + lastName;
  }

  /**
   * Returns the date of birth of the patient.
   *
   * @return the date of birth of the patient.
   */
  public String getDob() {
    return dob;
  }

  /**
   * Returns the first name of the patient.
   *
   * @return the first name of the patient.
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Returns the last name of the patient.
   *
   * @return the last name of the patient.
   */
  public String getLastName() {
    return lastName;
  }

  @Override
  public Set<ClinicalStaff> getStaffHelpers() {
    return staffHelpers;
  }

  @Override
  public void addRecordVisit(VisitRecord visitRecord) {
    Objects.requireNonNull(visitRecord, "Visit record cannot be null");
    visitRecords.add(visitRecord);
  }

  @Override
  public VisitRecord getMostRecentVisit() {
    return visitRecords.isEmpty() ? null : ((TreeSet<VisitRecord>) visitRecords).last();
  }

  @Override
  public Set<VisitRecord> getVisitHistory() {
    return new TreeSet<>(visitRecords);
  }

  @Override
  public void addStaffHelper(ClinicalStaff staff) {
    Objects.requireNonNull(staff, "Staff member cannot be null");
    staffHelpers.add(staff);
  }

  @Override
  public void removeStaffHelper(ClinicalStaff staff) {
    Objects.requireNonNull(staff, "Staff member cannot be null");
    staffHelpers.remove(staff);
  }

  @Override
  public String toString() {
    return "Patient: " + getFullName() + " DOB: " + dob;
  }

  @Override
  public String toStringWithVisits() {
    return this + "\n"
            + (visitRecords.isEmpty() ? "No visit history" : toStringVisitHistory());
  }

  /**
   * Displays the staff helpers of the patient.
   *
   * @return a string representation of the staff helpers
   */
  public String toStringStaffHelpers() {
    StringBuilder sb = new StringBuilder();
    sb.append("Staff Helpers:\n");
    for (ClinicalStaff staff : staffHelpers) {
      sb.append("- ").append(staff.getFullNameWithTitle()).append("\n");
    }
    return sb.toString();
  }

  @Override
  public String toStringVisitHistory() {
    StringBuilder sb = new StringBuilder();
    sb.append("Visit History:\n");
    for (VisitRecord record : visitRecords) {
      sb.append("- ").append(record.toString()).append("\n");
    }
    return sb.toString();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    return Objects.equals(firstName, ((Patient) obj).firstName)
            && Objects.equals(lastName, ((Patient) obj).lastName)
            && Objects.equals(dob, ((Patient) obj).dob);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, dob);
  }
}





