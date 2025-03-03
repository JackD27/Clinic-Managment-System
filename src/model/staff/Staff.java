package model.staff;

import java.util.Objects;

/**
 * Staff class that represents a staff member in a clinic. A staff member has a job title, first
 * name, last name, education level, and extra field.
 */
public class Staff implements StaffInterface {
  private final JobTitle jobTitle;
  private final String jobName;
  private final String firstName;
  private final String lastName;
  private final EducationLevel educationLevel;

  /**
   * Staff constructor that initializes the job title, first name, last name, education level, and
   * extra field of the staff.
   *
   * @param jobName the job title of the staff
   * @param firstName the first name of the staff
   * @param lastName the last name of the staff
   * @param educationLevel the education level of the staff
   */
  public Staff(String jobName, String firstName, String lastName, String educationLevel) {
    this.jobTitle = makeJobTitle(jobName);
    this.jobName = jobName;
    this.firstName = firstName;
    this.lastName = lastName;
    this.educationLevel = makeEducationLevel(educationLevel.toLowerCase());
  }

  private static EducationLevel makeEducationLevel(String educationLevel) {
    return switch (educationLevel) {
      case "doctoral" -> EducationLevel.DOCTORAL;
      case "masters" -> EducationLevel.MASTERS;
      case "allied" -> EducationLevel.ALLIED;
      default -> null;
    };
  }

  private static JobTitle makeJobTitle(String jobTitle) {
    return switch (jobTitle) {
      case "physician" -> JobTitle.PHYSICIAN;
      case "nurse" -> JobTitle.NURSE;
      default -> null;
    };
  }

  @Override
  public JobTitle getJobTitle() {
    return jobTitle;
  }

  /**
   * Returns the job name of the staff member.
   *
   * @return the job name of the staff member.
   */
  public String getJobName() {
    return jobName;
  }

  @Override
  public String getFullName() {
    return firstName + " " + lastName;
  }

  /**
   * Returns the first name of the staff member.
   *
   * @return the first name of the staff member.
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Returns the last name of the staff member.
   *
   * @return the last name of the staff member.
   */
  public String getLastName() {
    return lastName;
  }

  @Override
  public EducationLevel getEducationLevel() {
    return educationLevel;
  }

  @Override
  public String toString() {
    return getFullName();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Staff staff = (Staff) o;
    return Objects.equals(firstName, staff.firstName) && Objects.equals(lastName, staff.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName);
  }
}
