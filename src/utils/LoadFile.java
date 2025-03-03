package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import model.clinic.Clinic;
import model.patient.Patient;
import model.room.Room;
import model.staff.ClinicalStaff;
import model.staff.NonClinicalStaff;
import model.staff.Staff;

/**
 * Represents a class to load clinic data from a file.
 */
public class LoadFile {
  private Clinic clinic;
  private final Map<Integer, Room> rooms;
  private final Map<String, Staff> staffMap;

  /**
   * Creates a new LoadFile object with an empty set of rooms and an empty map of staff.
   */
  public LoadFile() {
    this.rooms = new HashMap<>();
    this.staffMap = new HashMap<>();
    this.clinic = null;
  }

  /**
   * Loads clinic data from a file and creates a new clinic with the given clinic name, rooms, and
   * staff members.
   *
   * @param filePath the file path to load clinic data from
   */
  public void loadClinicData(String filePath) {
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String clinicName = reader.readLine();
      int numRooms = Integer.parseInt(reader.readLine().trim());;
      for (int i = 0; i < numRooms; i++) {
        String[] roomInfo = reader.readLine().trim().split("\\s+");
        int roomNumber = i + 1;
        int x1 = Integer.parseInt(roomInfo[0]);
        int y1 = Integer.parseInt(roomInfo[1]);
        int x2 = Integer.parseInt(roomInfo[2]);
        int y2 = Integer.parseInt(roomInfo[3]);
        String roomType = roomInfo[4];
        String roomName = roomInfo[5];
        rooms.put(roomNumber, new Room(roomNumber, x1, y1, x2, y2, roomType, roomName));
      }
      int numStaff = Integer.parseInt(reader.readLine().trim());
      for (int i = 0; i < numStaff; i++) {
        String[] staffInfo = reader.readLine().trim().split("\\s+");
        String jobTitle = staffInfo[0];
        String firstName = staffInfo[1];
        String lastName = staffInfo[2];
        String educationLevel = staffInfo[3];
        String extraField = staffInfo[4];
        String name = firstName + " " + lastName;
        if (extraField.length() > 4) {
          staffMap.put(name, new ClinicalStaff(jobTitle, firstName, lastName, educationLevel,
                  extraField));
        } else {
          staffMap.put(name, new NonClinicalStaff(jobTitle, firstName, lastName, educationLevel,
                  extraField));
        }
      }
      clinic = new Clinic(clinicName, rooms, staffMap);

      int numPatients = Integer.parseInt(reader.readLine().trim());
      for (int i = 0; i < numPatients; i++) {
        String[] patientInfo = reader.readLine().trim().split("\\s+");
        String patientRoom = patientInfo[0];
        String firstName = patientInfo[1];
        String lastName = patientInfo[2];
        String dob = patientInfo[3];
        Patient patient = new Patient(firstName, lastName, dob);
        Room room = clinic.getRoom(Integer.parseInt(patientRoom));
        if (room != null) {
          room.addPatient(patient);
        } else {
          System.out.println("Room not found for patient " + patient.getFullName());
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Returns the clinic.
   *
   * @return the clinic
   */
  public Clinic getClinic() {
    return clinic;
  }
}
