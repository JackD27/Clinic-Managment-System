package commands;

import java.util.logging.Logger;
import model.clinic.Clinic;
import model.patient.Patient;
import model.room.Room;

/**
 * Represents a command to assign a patient to a room.
 */
public class AssignPatientToRoomCommand implements Command2 {
  private final Clinic clinic;
  private final Logger logger;
  private final Patient patient;
  private final Room room;

  /**
   * Creates a new AssignPatientToRoomCommand with the given clinic and logger.
   *
   * @param clinic the clinic to assign the patient to a room
   * @param logger the logger to log the assignment
   */
  public AssignPatientToRoomCommand(Clinic clinic, Logger logger, Patient patient, Room room) {
    this.clinic = clinic;
    this.logger = logger;
    this.patient = patient;
    this.room = room;
  }

  @Override
  public void execute() throws IllegalArgumentException {
    try {
      if (patient == null || room == null) {
        throw new IllegalArgumentException("Patient or room not found.");
      }
      clinic.movePatient(patient, clinic.getPatientRoom(patient).getRoomNumber(),
              room.getRoomNumber());
      logger.info("Patient " + patient.getFullName() + " assigned to room "
              + room.getRoomNumber());
    } catch (IllegalArgumentException e) {
      logger.severe(e.getMessage());
      throw new IllegalArgumentException(e.getMessage());
    }
  }
}
