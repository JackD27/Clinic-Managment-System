package model.staff;

/**
 * StaffInterface is an interface that contains the methods that are implemented in the Staff class.
 */
public interface StaffInterface {
  /**
   * Returns the full name of the staff member.
   *
   * @return the full name of the staff member
   */
  String getFullName();

  /**
   * Returns the education level of the staff member.
   *
   * @return the education level of the staff member
   */
  EducationLevel getEducationLevel();

  /**
   * Returns the job title of the staff member.
   *
   * @return the job title of the staff member
   */
  JobTitle getJobTitle();
}
