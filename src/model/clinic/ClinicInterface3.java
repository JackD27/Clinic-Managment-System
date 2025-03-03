package model.clinic;

import java.util.List;
import model.patient.Patient;
import model.room.Room;
import model.staff.ClinicalStaff;
import model.staff.Staff;

/**
 * ClinicInterface3 interface that represents a clinic. A clinic has a name, rooms,
 * and staff members.
 */
public interface ClinicInterface3 extends ClinicInterface2 {

  /**
   * Deactivates a clinical staff member from the clinic.
   *
   * @param staff the clinical staff member
   */
  void deactivateClinicalStaffMember(ClinicalStaff staff);

  /**
   * Displays the patients for a clinical staff member.
   *
   * @param staff the clinical staff member
   */
  void displayPatientsForClinicalStaff(ClinicalStaff staff);

  /**
   * Displays all the clinical staff members and their total number of patients they had in the
   * past.
   */
  void displayTotalNumberOfPastAssignees();

  /**
   * Removes a clinical staff member from a patient, but not from the clinic.
   *
   * @param staff   the clinical staff member to remove from the patient.
   * @param patient the patient to remove the clinical staff member from.
   */
  void removeClinicalStaffFromPatient(ClinicalStaff staff, Patient patient);

  /**
   * Displays all the patients with two or more visits in the past 365 days.
   */
  void displayRecentFrequentVisitors();

  /**
   * Displays patients who have not visited in the past 365 days but have visited one or more times.
   */
  void displayNonRecentFrequentVisitors();

  /**
   * Returns the rooms in the clinic as a list.
   */
  List<Room> getRoomsList();

  /**
   * Returns the staff members in the clinic as a list.
   */
  List<Staff> getStaff();

  /**
   * Returns the name of the Clinic.
   */
  String getClinicName();

  /**
   * Returns the recent frequent visitors in the past 365 days.
   */
  List<Patient> getRecentFrequentVisitors();

  /**
   * Returns the patients assigned to a clinical staff member.
   */
  List<Patient> getPatientsFromClinicalStaff(ClinicalStaff staff);

  /**
   * Returns the non-recent frequent visitors in the past 365 days.
   */
  List<Patient> getNonRecentFrequentVisitors();
}