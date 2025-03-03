package model.patient;

import java.util.Set;
import model.staff.ClinicalStaff;

/**
 * Interface for Patient class. Contains methods to get patient's first name, last name, full name,
 * date of birth, room, staff helpers, add staff helper, and remove staff helper.
 */
public interface PatientInterface {

  /**
   * Add a staff member as a helper for the patient.
   *
   * @param staff the staff to add as a helper for the patient.
   */
  void addStaffHelper(ClinicalStaff staff);

  /**
   * Remove a staff member from the patient.
   *
   * @param staff the staff to remove from the patient.
   */
  void removeStaffHelper(ClinicalStaff staff);

  /**
   * Returns the patient's staff helpers.
   *
   * @return the patient's staff helpers.
   */
  Set<ClinicalStaff> getStaffHelpers();

  /**
   * Add a visit record to the patient's visit history.
   *
   * @param visitRecord the visit record to add to the patient's visit history.
   */
  void addRecordVisit(VisitRecord visitRecord);

  /**
   * Returns the most recent visit record for the patient.
   *
   * @return the most recent visit record for the patient.
   */
  VisitRecord getMostRecentVisit();

  /**
   * Returns the patient's visit history.
   *
   * @return the patient's visit history.
   */
  Set<VisitRecord> getVisitHistory();
}
