package model.room;

import java.util.List;
import model.patient.Patient;

/**
 * Represents a room in a clinic. A room can be of type exam, waiting, or procedure. A room can
 * contain patients.
 */
public interface RoomInterface {

  /**
   * Adds a patient to the room.
   *
   * @param patient the patient to add
   */
  void addPatient(Patient patient);

  /**
   * Removes a patient from the room.
   *
   * @param patient the patient to remove
   */
  void removePatient(Patient patient);

  /**
   * Returns the patients in the room.
   *
   * @return the patients in the room
   */
  List<Patient> getPatients();

  /**
   * Returns the room number.
   *
   * @return the room number
   */
  int getRoomNumber();

  /**
   * Returns the type of the room.
   *
   * @return the type of the room
   */
  RoomType getRoomType();

  /**
   * Returns the name of the room.
   *
   * @return the name of the room
   */
  String getRoomName();

  /**
   * Returns the specific details of the room.
   *
   * @return the specific details of the room
   */
  String getSpecificDetails();


}
