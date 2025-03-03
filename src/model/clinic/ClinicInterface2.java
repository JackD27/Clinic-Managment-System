package model.clinic;

import model.patient.Patient;
import model.room.Room;
import model.staff.Staff;

/**
 * ClinicInterface2 interface that represents a clinic. A clinic has a name, rooms,
 * and staff members.
 */
public interface ClinicInterface2 extends ClinicInterface {
  /**
   * Returns the room with the given room number.
   *
   * @param roomNumber the room number
   * @return the room with the given room number
   */
  Room getRoom(int roomNumber);

  /**
   * Returns the patient room for the given patient.
   *
   * @param patient the patient
   * @return the patient room for the given patient
   */
  Room getPatientRoom(Patient patient);

  /**
   * Returns the room number for the given patient.
   *
   * @param patient the patient
   * @return the room number for the given patient
   */
  int getPatientRoomInt(Patient patient);

  /**
   * Registers a new patient and puts them in the primary waiting room.
   *
   * @param patient the patient to add to the clinic
   */
  void registerNewPatient(Patient patient);

  /**
   * Registers a new staff member.
   *
   * @param staffMember the staff member to add to the clinic
   */
  void registerNewStaffMember(Staff staffMember);

  /**
   * Gets details for a specific room.
   *
   * @param roomNumber the room number to get details for
   */
  String getSpecificRoomDetails(int roomNumber);

  /**
   * Displays the list of clinical staff members.
   *
   */
  void displayClinicalStaffList();

  /**
   * Shows the list of all rooms.
   *
   */
  void displayRoomList();

  /**
   * Displays the details of a specific patient.
   *
   * @param patient the patient to display details for
   */
  void displayPatientDetails(Patient patient);
}
