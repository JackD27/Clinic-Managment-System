package patient;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import model.clinic.Clinic;
import model.patient.Patient;
import model.patient.VisitRecord;
import model.room.Room;
import model.staff.ClinicalStaff;
import model.staff.Staff;
import org.junit.Before;
import org.junit.Test;

/**
 * PatientTest class that tests the Patient class.
 */
public class PatientTest {
  Patient patient;
  Patient patient2;
  Room room;
  Room waitingRoom;
  ClinicalStaff staff;
  LocalDateTime visitTime;
  Clinic clinic;
  Map<Integer, Room> rooms;
  Map<String, Staff> staffMap;

  /**
   * Sets up the patient, room, and staff for testing.
   */
  @Before
  public void setUp() {
    rooms = new HashMap<>();
    staffMap = new HashMap<>();
    room = new Room(1, 1, 1, 1, 1, "exam",
            "Exam Room");

    visitTime = LocalDateTime.now();
    VisitRecord visitRecord = new VisitRecord(visitTime, "Patient has covid.", 99.8);
    VisitRecord visitRecord2 = new VisitRecord(visitTime, "Patient has covid and sun stroke.", 102);
    patient = new Patient("John", "Doe", "01/01/2005", visitRecord);
    patient2 = new Patient("Jackson", "Davis", "11/03/2000", visitRecord2);
    staff = new ClinicalStaff("physician", "Amy", "Anguish",
            "doctoral", "1234567890");
    waitingRoom = new Room(2, 1, 1, 1, 1, "waiting",
            "Waiting Room");
    rooms = new HashMap<>();
    rooms.put(waitingRoom.getRoomNumber(), waitingRoom);
    rooms.put(room.getRoomNumber(), room);
    staffMap.put(staff.getFullName(), staff);
    clinic = new Clinic("Mayo Clinic", rooms, staffMap);
    clinic.registerNewPatient(patient);
    clinic.registerNewPatient(patient2);
    patient2.addStaffHelper(staff);
    clinic.movePatient(patient2, clinic.getPatientRoomInt(patient2), room.getRoomNumber());
  }

  @Test
  public void getRoom() {
    assertEquals(waitingRoom, clinic.getPatientRoom(patient));
    assertEquals(room, clinic.getPatientRoom(patient2));
  }

  @Test
  public void getStaffHelpers() {
    assertEquals(0, patient.getStaffHelpers().size());
    assertEquals(1, patient2.getStaffHelpers().size());
    patient2.removeStaffHelper(staff);
    assertEquals(0, patient2.getStaffHelpers().size());
  }

  @Test
  public void addStaffHelper() {
    assertEquals(0, patient.getStaffHelpers().size());
    patient.addStaffHelper(staff);
    assertEquals(1, patient.getStaffHelpers().size());
  }

  @Test
  public void getSpecificPatientDetails() {
    assertEquals("Patient: Jackson Davis DOB: 11/03/2000\n" + "Visit History:\n"
            + "- Visit on: " + visitTime + ", Chief Complaint: Patient has covid and sun stroke., "
            + "Body Temperature: 102.0°C\n", patient2.toStringWithVisits());
  }
}