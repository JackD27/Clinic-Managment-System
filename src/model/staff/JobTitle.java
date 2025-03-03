package model.staff;

/**
 * Enum class that represents the job title of a staff member.
 */
public enum JobTitle {
  NURSE("Nurse"),
  PHYSICIAN("Dr.");

  private final String title;

  JobTitle(String title) {
    this.title = title;
  }

  /**
   * Returns the title of the job.
   *
   * @return the title of the job
   */
  public String getTitle() {
    return title;
  }
}
