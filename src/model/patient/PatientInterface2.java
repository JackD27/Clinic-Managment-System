package model.patient;

/**
 * Interface for Patient class. Contains methods to get patient's first name, last name, full name,
 * date of birth, room, staff helpers, add staff helper, and remove staff helper.
 */
public interface PatientInterface2 extends PatientInterface {

  /**
   * Returns toString() of the patient with visits or "No recent visits will be shown." if the
   * patient has no visits.
   *
   * @return the string representation of the patient with visits
   */
  String toStringWithVisits();

  /**
   * Returns toString() of the patient's visits.
   *
   * @return the string representation of the patient's visits
   */
  String toStringVisitHistory();
}
