package model.staff;

/**
 * Represents the different levels of education.
 */
public enum EducationLevel {
  DOCTORAL("Doctoral"),
  MASTERS("Masters"),
  ALLIED("Allied");

  private final String educationLevel;

  EducationLevel(String educationLevel) {
    this.educationLevel = educationLevel;
  }

  /**
   * Returns the education level.
   *
   * @return the education level
   */
  public String getEducationLevel() {
    return educationLevel;
  }
}
