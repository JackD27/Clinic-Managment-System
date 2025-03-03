package view.listeners;

import model.patient.Patient;
import model.room.Room;

/**
 * MovePatientListener is an interface for moving a patient to a room.
 */
public interface MovePatientListener {

  /**
   * onMovePatientToRoom is called when the user selects the Move Patient to Room option from the
   * Room menu.
   *
   * @param patient the patient to move
   * @param room the room to move the patient to
   */
  void onMovePatientToRoom(Patient patient, Room room);
}
