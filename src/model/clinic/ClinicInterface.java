package model.clinic;

import java.util.List;
import java.util.Map;
import model.patient.Patient;
import model.patient.VisitRecord;
import model.room.Room;
import model.staff.ClinicalStaff;
import model.staff.Staff;

/**
 * ClinicInterface interface that represents a clinic. A clinic has a name, rooms,
 * and staff members.
 */
public interface ClinicInterface {
  /**
   * Returns the name of the clinic.
   *
   * @return the name of the clinic
   */
  String clinicName();

  /**
   * Returns the rooms in the clinic.
   *
   * @return the rooms in the clinic
   */
  Map<Integer, Room> rooms();

  /**
   * Returns the staff members in the clinic.
   *
   * @return the staff members in the clinic
   */
  Map<String, Staff> staffList();

  /**
   * Returns the primary waiting room in the clinic.
   *
   * @return the primary waiting room in the clinic
   */
  Room getPrimaryWaitingRoom();

  /**
   * Returns the clinical staff members in the clinic.
   *
   * @return the clinical staff members in the clinic
   */
  List<ClinicalStaff> getClinicalStaff();

  /**
   * Checks if a patient exists in the clinic.
   *
   * @param patient the patient to check
   * @return the patient if they exist, null otherwise
   */
  Patient doesPatientExist(Patient patient);

  /**
   * Assigns a patient to a new room.
   *
   * @param patient the patient to assign
   * @param fromRoom the room the patient is currently in
   * @param toRoom the room to assign the patient to
   */
  void movePatient(Patient patient, int fromRoom, int toRoom);

  /**
   * Sends a patient home from the clinic/removes them from the clinic.
   *
   * @param patient the patient to send home
   * @param staff the staff member who is sending the patient home
   */
  void sendPatientHome(Patient patient, ClinicalStaff staff);

  /**
   * Assigns a staff member to a patient.
   *
   * @param patient the patient to assign the staff member to
   * @param staff the staff member to assign to the patient
   */
  void assignStaffToPatient(Patient patient, ClinicalStaff staff);


  /**
   * Assigns a visit record to a patient.
   *
   * @param patient the patient to assign the visit record to
   * @param record the visit record to assign to the patient
   */
  void assignRecordToPatient(Patient patient, VisitRecord record);

  /**
   * Returns the patients in the clinic.
   *
   * @return the patients in the clinic
   */
  List<Patient> getPatients();

  /**
   * Returns the seating chart of the clinic.
   *
   * @return the seating chart of the clinic
   */
  String seatingChart();

  /**
   * Deactivates/removes a staff member.
   *
   * @param staff the staff member to deactivate/remove
   */
  void deactivateStaffMember(Staff staff);

  /**
   * Displays the patients and their helpers.
   */
  void displayPatientAndHelpers();

  /**
   * Returns the remaining clinical staff not used by the patient.
   *
   * @param patient the patient to check for remaining clinical staff to pick from
   * @return the remaining clinical staff not used by the patient
   */
  List<ClinicalStaff> getRemainingClinicalStaffNotUsedByPatient(Patient patient);

  /**
   * Displays the patient list. with name, room number, and staff helpers.
   */
  void displayPatientList();

  /**
   * Displays Clininc name, rooms, staff, patients, and staff helpers.
   *
   * @return the clinic name, rooms, staff, patients, and staff helpers
   */
  String displayEverything();

}
