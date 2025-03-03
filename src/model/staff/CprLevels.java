package model.staff;

/**
 * Represents the different levels of CPR certification.
 */
public enum CprLevels {
  A("A"),
  B("B"),
  C("C"),
  BLS("BLS");

  private final String cprLevel;

  CprLevels(String cprLevel) {
    this.cprLevel = cprLevel;
  }

  /**
   * Returns the CPR level.
   *
   * @return the CPR level
   */
  public String getCprLevel() {
    return cprLevel;
  }
}

