package model.staff;

/**
 * Represents a non-clinical staff member in a clinic.
 */
public class NonClinicalStaff extends Staff {

  private final CprLevels cprLevel;

  /**
   * Staff constructor that initializes the job title, first name, last name, education level, and
   * extra field of the staff.
   *
   * @param jobName        the job title of the staff
   * @param firstName      the first name of the staff
   * @param lastName       the last name of the staff
   * @param educationLevel the education level of the staff
   * @param cprLevel       the extra field of the staff
   */
  public NonClinicalStaff(String jobName, String firstName, String lastName, String educationLevel,
                          String cprLevel) {
    super(jobName, firstName, lastName, educationLevel);
    this.cprLevel = makeCprLevel(cprLevel);
  }

  private static CprLevels makeCprLevel(String cprLevel) {
    return switch (cprLevel) {
      case "A" -> CprLevels.A;
      case "B" -> CprLevels.B;
      case "C" -> CprLevels.C;
      case "BLS" -> CprLevels.BLS;
      default -> null;
    };
  }

  /**
   * Returns the CPR level of the non-clinical staff member.
   *
   * @return the CPR level of the non-clinical staff member.
   */
  public CprLevels getCprLevel() {
    return cprLevel;
  }

  @Override
  public String toString() {
    return getFullName() + "CPR Level: " + cprLevel;
  }

  /**
   * Returns the staff type of the non-clinical staff member.
   *
   * @return the staff type of the non-clinical staff member.
   */
  public StaffType getStaffType() {
    return StaffType.NONCLINICAL;
  }
}