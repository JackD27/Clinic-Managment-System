package model.room;

import java.util.ArrayList;
import java.util.List;
import model.patient.Patient;

/**
 * Represents a room in a clinic. A room can be of type exam, waiting, or procedure. A room can
 * contain patients.
 */
public class Room implements RoomInterface {
  private final int roomNumber;
  private final Point lowerLeft;
  private final Point upperRight;
  private final RoomType roomType;
  private final String roomName;
  private final List<Patient> patients;

  /**
   * Creates a new Room object with the given room number, coordinates, room type, and room name.
   *
   * @param roomNumber the room number
   * @param x1 the x-coordinate of the lower left corner of the room
   * @param y1 the y-coordinate of the lower left corner of the room
   * @param x2 the x-coordinate of the upper right corner of the room
   * @param y2 the y-coordinate of the upper right corner of the room
   * @param roomType the type of the room
   * @param roomName the name of the room
   */
  public Room(int roomNumber, int x1, int y1, int x2, int y2, String roomType, String roomName) {
    this.roomNumber = roomNumber;
    this.lowerLeft = new Point(x1, y1);
    this.upperRight = new Point(x2, y2);
    this.roomType = makeRoomType(roomType.toLowerCase());
    this.roomName = roomName;
    this.patients = new ArrayList<>();
  }

  private static RoomType makeRoomType(String roomType) {
    return switch (roomType) {
      case "exam" -> RoomType.EXAM;
      case "waiting" -> RoomType.WAITING;
      case "procedure" -> RoomType.PROCEDURE;
      default -> null;
    };
  }

  @Override
  public void addPatient(Patient patient) {
    if (patients.contains(patient)) {
      throw new IllegalArgumentException("Patient already exists in Room");
    }
    if (roomType == RoomType.WAITING) {
      patients.add(patient);
    } else if (patients.isEmpty()) {
      patients.add(patient);
    } else {
      throw new IllegalArgumentException("Room is full");
    }
  }

  @Override
  public void removePatient(Patient patient) {
    patients.remove(patient);
  }

  @Override
  public RoomType getRoomType() {
    return roomType;
  }

  @Override
  public String getRoomName() {
    if (roomType == RoomType.WAITING) {
      return roomName + " Waiting Room";
    } else {
      return roomName;
    }
  }

  @Override
  public int getRoomNumber() {
    return roomNumber;
  }

  /**
   * Returns the lower left corner of the room.
   *
   * @return the lower left corner of the room
   */
  public Point getLowerLeft() {
    return lowerLeft;
  }

  /**
   * Returns the upper right corner of the room.
   *
   * @return the upper right corner of the room
   */
  public Point getUpperRight() {
    return upperRight;
  }

  @Override
  public List<Patient> getPatients() {
    return patients;
  }

  @Override
  public String getSpecificDetails() {
    if (patients.isEmpty()) {
      return "No patients in room";
    }
    StringBuilder sb = new StringBuilder();
    patients.forEach(patient -> {
      sb.append(patient).append("\n");
      String visitHistory = patient.getVisitHistory().isEmpty()
              ? "No visit history\n"
              : String.join("\n", patient.toStringVisitHistory().lines().toList()) + "\n";
      sb.append(visitHistory);
      String staffHelpers = patient.getStaffHelpers().isEmpty()
              ? "No staff assigned\n"
              : String.join("\n", patient.toStringStaffHelpers().lines().toList()) + "\n";
      sb.append(staffHelpers);
    });
    return sb.toString();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Room room)) {
      return false;
    }
    return roomNumber == room.roomNumber;
  }

  @Override
  public int hashCode() {
    return Integer.hashCode(roomNumber);
  }
}
