package clinic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.clinic.Clinic;
import model.patient.Patient;
import model.patient.VisitRecord;
import model.room.Room;
import model.staff.ClinicalStaff;
import model.staff.NonClinicalStaff;
import model.staff.Staff;
import org.junit.Before;
import org.junit.Test;


/**
 * ClinicTest class that tests the Clinic class.
 */
public class ClinicTest {

  Clinic clinic;
  ClinicalStaff staff;
  NonClinicalStaff staff2;
  ClinicalStaff staff3;
  NonClinicalStaff staff4;
  Room roomExam;
  Room roomWaiting;
  Room roomProcedure;
  Patient patient;
  Map<Integer, Room> rooms;

  /**
   * Sets up the clinic, rooms, and staff for testing.
   */
  @Before
  public void setUp() {
    roomExam = new Room(2, 1, 1, 2, 2, "exam",
            "Exam Room");
    roomWaiting = new Room(1, 3, 3, 4, 4, "waiting",
            "Front");
    roomProcedure = new Room(3, 5, 5, 6, 6, "procedure",
            "Procedure Room");
    rooms = new HashMap<>();
    rooms.put(roomExam.getRoomNumber(), roomExam);
    rooms.put(roomWaiting.getRoomNumber(), roomWaiting);
    rooms.put(roomProcedure.getRoomNumber(), roomProcedure);
    staff = new ClinicalStaff("physician", "Amy", "Anguish",
            "doctoral", "1234567890");
    staff2 = new NonClinicalStaff("reception", "Nancy", "Nurse",
            "bachelor", "A");
    staff3 = new ClinicalStaff("physician", "John", "Doe",
            "doctoral", "1234567891");
    staff4 = new NonClinicalStaff("reception", "Jerry", "Doe",
            "bachelor", "A");
    Map<String, Staff> staffSet = new HashMap<>();
    staffSet.put(staff.getFullName(), staff);
    staffSet.put(staff2.getFullName(), staff2);
    staffSet.put(staff3.getFullName(), staff3);
    staffSet.put(staff4.getFullName(), staff4);
    clinic = new Clinic("Jack's Cool Clinic", rooms, staffSet);
    patient = new Patient("John", "Doe", "01/01/2000");
    clinic.registerNewPatient(patient);
    clinic.movePatient(patient, clinic.getPatientRoomInt(patient), roomExam.getRoomNumber());
  }

  @Test
  public void getRooms() {
    assertEquals(3, clinic.rooms().size());
  }

  @Test
  public void getPrimaryWaitingRoom() {
    assertEquals(roomWaiting, clinic.getPrimaryWaitingRoom());
  }

  @Test
  public void getStaff() {
    assertEquals(4, clinic.getStaff().size());
  }

  @Test
  public void getClinicalStaff() {
    assertEquals(2, clinic.getClinicalStaff().size());
  }

  @Test
  public void registerNewPatient() {
    Patient patient = new Patient("Jack", "Doe", "01/01/2000");
    clinic.registerNewPatient(patient);
    assertEquals(1, clinic.getPrimaryWaitingRoom().getPatients().size());
  }

  @Test
  public void assignPatientToRoom() {
    Patient patient2 = new Patient("Tyler", "Doe", "01/01/2000");
    clinic.registerNewPatient(patient2);
    clinic.movePatient(patient2, clinic.getPatientRoomInt(patient2), roomProcedure.getRoomNumber());
    assertEquals(1, roomProcedure.getPatients().size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void assignPatientToRoomFull() {
    Patient patient2 = new Patient("Tyler", "Doe", "01/01/2000");
    clinic.registerNewPatient(patient2);
    clinic.movePatient(patient2, clinic.getPatientRoomInt(patient2), roomExam.getRoomNumber());
  }

  @Test (expected = IllegalArgumentException.class)
  public void registerNewStaffMemberSetTesting() { // Duplicate staff, size shouldn't change
    ClinicalStaff staff = new ClinicalStaff("physician", "Amy", "Anguish",
            "doctoral", "1234567891");
    clinic.registerNewStaffMember(staff);
    assertEquals(4, clinic.staffList().size());
    NonClinicalStaff staff2 = new NonClinicalStaff("reception", "Na", "Nur",
            "bachelor", "A");
    clinic.registerNewStaffMember(staff2);
    assertEquals(5, clinic.getStaff().size());
  }

  @Test
  public void sendPatientHome() {
    Patient patient = new Patient("Jack", "Davis", "11/03/2000");
    clinic.registerNewPatient(patient);
    assertEquals(1, clinic.getPrimaryWaitingRoom().getPatients().size());
    ClinicalStaff staff = clinic.getClinicalStaff().get(0);
    clinic.sendPatientHome(patient, staff);
    assertEquals(0, clinic.getPrimaryWaitingRoom().getPatients().size());
    assertEquals(4, clinic.getStaff().size());
  }

  @Test
  public void assignStaffToPatient() {
    clinic.assignStaffToPatient(patient, staff);
    assertEquals(1, patient.getStaffHelpers().size());
    ClinicalStaff staff6 = new ClinicalStaff("physician", "Matt", "Johnson",
            "doctoral", "12345676362276876");
    clinic.registerNewStaffMember(staff6);
    clinic.assignStaffToPatient(patient, staff6);
    assertEquals(2, patient.getStaffHelpers().size());
    assertEquals(5, clinic.getStaff().size());
  }

  @Test
  public void deactivateClinicalStaffMember() {
    clinic.deactivateClinicalStaffMember(staff3);
    assertEquals(3, clinic.getStaff().size());
  }

  @Test
  public void deactivateStaffMember() {
    clinic.deactivateStaffMember(staff4);
    assertEquals(3, clinic.getStaff().size());
  }

  @Test (expected = IllegalArgumentException.class)
  public void getSpecificRoomDetails() {
    Patient patient2 = new Patient("Jane", "Doe", "01/01/2000");
    clinic.registerNewPatient(patient2);
    ClinicalStaff staff = new ClinicalStaff("physician", "Amy", "Anguish",
            "doctoral", "1234567890");
    NonClinicalStaff staff2 = new NonClinicalStaff("reception", "Nancy", "Nurs",
            "allied", "A");
    clinic.registerNewStaffMember(staff);
    clinic.registerNewStaffMember(staff2);
    clinic.assignStaffToPatient(patient, staff); // shouldn't work, since already exists
    assertEquals("""
            Patient: John Doe\
             DOB: 01/01/2000
            No visit history
            Staff Helpers:
            - Dr. Amy Anguish
            """, clinic.getSpecificRoomDetails(roomExam.getRoomNumber()));
    assertEquals("""
            Patient: Jane \
            Doe DOB: 01/01/2000
            No visit history
            No staff assigned
            """, clinic.getSpecificRoomDetails(roomWaiting.getRoomNumber()));
    assertEquals("No patients in room", clinic.getSpecificRoomDetails(roomProcedure
            .getRoomNumber()));
  }

  @Test
  public void getSpecificRoomDetails2() {
    LocalDateTime time = LocalDateTime.now();
    VisitRecord visitRecord = new VisitRecord(time, "Patient has covid.", 99.8);
    Patient patient = new Patient("Jane", "Doe", "01/01/2000", visitRecord);
    clinic.registerNewPatient(patient);
    ClinicalStaff staff = new ClinicalStaff("physician", "Amy", "Anguish",
            "doctoral", "1234567890");
    clinic.movePatient(patient, clinic.getPatientRoomInt(patient), roomWaiting.getRoomNumber());
    clinic.assignStaffToPatient(patient, staff);
    LocalDateTime time2 = LocalDateTime.now();
    LocalDateTime time3 = LocalDateTime.now().plusSeconds(10);
    VisitRecord visitRecord3 = new VisitRecord(time3, "Patient is dying.", 60);
    VisitRecord visitRecord2 = new VisitRecord(time2, "Patient has anxiety.", 102.8);
    clinic.assignRecordToPatient(patient, visitRecord2);
    clinic.assignRecordToPatient(patient, visitRecord3);
    assertEquals("Patient: "
            + "Jane Doe DOB: 01/01/2000\n"
            + "Visit History:\n"
            + "- Visit on: " + time + ", Chief Complaint: "
            + "Patient has covid., Body Temperature: 99.8°C\n"
            + "- Visit on: " + time3 + ", Chief Complaint: "
            + "Patient is dying., Body Temperature: 60.0°C\n" + "Staff Helpers:\n"
            + "- Dr. Amy Anguish\n", clinic.getSpecificRoomDetails(roomWaiting.getRoomNumber()));
    assertTrue(patient.getVisitHistory().contains(visitRecord3));
  }

  @Test
  public void getClinicName() {
    assertEquals("Jack's Cool Clinic", clinic.getClinicName());
  }

  @Test
  public void getClinicNameFail() {
    clinic = new Clinic(null, new HashMap<>(), new HashMap<>());
    assertNull(clinic.getClinicName());
  }

  @Test
  public void seatingChart() {
    assertEquals("""
            Front Waiting Room (Room #: 1)
            Patients:
            
            Exam Room (Room #: 2)
            Patients:
            - John Doe
            
            Procedure Room (Room #: 3)
            Patients:
            
            """, clinic.seatingChart());
  }

  @Test
  public void showStaffWhoHavePatients() {
    clinic.assignStaffToPatient(patient, staff);
    assertEquals(1, staff.getNumPatientsAssignedFromPast());
    clinic.sendPatientHome(patient, staff);
    assertEquals(1, staff.getNumPatientsAssignedFromPast());
  }

  @Test
  public void removeStaffFromPatient() {
    clinic.assignStaffToPatient(patient, staff);
    assertEquals(1, patient.getStaffHelpers().size());
    clinic.removeClinicalStaffFromPatient(staff, patient);
    assertEquals(0, patient.getStaffHelpers().size());
  }

  @Test
  public void getFrequentVisitors() {
    LocalDateTime time = LocalDateTime.now();
    VisitRecord visitRecord = new VisitRecord(time, "Patient has covid.", 99.8);
    Patient patient = new Patient("Jane", "Doe", "01/01/2000", visitRecord);
    clinic.registerNewPatient(patient);
    LocalDateTime time2 = LocalDateTime.now();
    VisitRecord visitRecord2 = new VisitRecord(time2, "Patient has covid again.", 100);
    clinic.assignRecordToPatient(patient, visitRecord2);
    List<Patient> patientsFrequentVisitors = clinic.getRecentFrequentVisitors();
    assertEquals(1, patientsFrequentVisitors.size());
  }

  @Test
  public void getNonFrequentVisitors() {
    LocalDateTime time = LocalDateTime.now().minusDays(366);
    VisitRecord visitRecord = new VisitRecord(time, "Patient has covid.", 99.8);
    Patient patient = new Patient("Jane", "Doe", "01/01/2000", visitRecord);
    clinic.registerNewPatient(patient);
    LocalDateTime time2 = LocalDateTime.now().minusDays(366);
    VisitRecord visitRecord2 = new VisitRecord(time2, "Patient has covid again.", 100);
    clinic.assignRecordToPatient(patient, visitRecord2);
    List<Patient> patientsNonFrequentVisitors = clinic.getNonRecentFrequentVisitors();
    assertEquals(1, patientsNonFrequentVisitors.size());
  }

  @Test
  public void getPatientsFromStaff() {
    clinic.assignStaffToPatient(patient, staff);
    List<Patient> patients = clinic.getPatientsFromClinicalStaff(staff);
    assertEquals(1, patients.size());
  }
}