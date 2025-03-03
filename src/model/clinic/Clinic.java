package model.clinic;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import model.patient.Patient;
import model.patient.VisitRecord;
import model.room.Room;
import model.room.RoomType;
import model.staff.ClinicalStaff;
import model.staff.Staff;

/**
 * Clinic record that represents a clinic with a clinic name, rooms, and staff list. A clinic has a
 * primary waiting room, clinical staff, and patients. A clinic can register new patients and staff
 * members, assign patients to rooms, send patients home, assign staff to patients, and deactivate
 * staff members.
 */
public record Clinic(String clinicName, Map<Integer, Room> rooms,
                     Map<String, Staff> staffList) implements ClinicInterface3 {

  @Override
  public Room getPrimaryWaitingRoom() {
    return rooms.values().stream()
            .filter(r -> r.getRoomType() == RoomType.WAITING)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Primary waiting room does not exist"));
  }

  @Override
  public List<ClinicalStaff> getClinicalStaff() {
    return staffList.values().stream()
            .filter(s -> s instanceof ClinicalStaff)
            .map(s -> (ClinicalStaff) s)
            .toList();
  }

  @Override
  public Patient doesPatientExist(Patient patient) {
    return null;
  }

  @Override
  public List<Staff> getStaff() {
    return staffList.values().stream()
            .toList();
  }

  @Override
  public List<Patient> getPatients() {
    return rooms.values().stream()
            .flatMap(r -> r.getPatients().stream())
            .toList();
  }

  @Override
  public Room getRoom(int roomNumber) {
    return rooms.get(roomNumber);
  }

  @Override
  public int getPatientRoomInt(Patient patient) {
    return rooms.values().stream()
            .filter(r -> r.getPatients().contains(patient))
            .findFirst()
            .map(Room::getRoomNumber)
            .orElseThrow(() -> new IllegalArgumentException("Patient does not exist in clinic"));
  }

  @Override
  public Room getPatientRoom(Patient patient) {
    return rooms.values().stream()
            .filter(r -> r.getPatients().contains(patient))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Patient does not exist in clinic"));
  }

  @Override
  public String getClinicName() {
    return clinicName;
  }

  @Override
  public void movePatient(Patient patient, int fromRoom, int toRoom) {
    try {
      Room from = rooms.get(fromRoom);
      Room to = rooms.get(toRoom);
      if (from == null || to == null) {
        throw new IllegalArgumentException("Invalid room number");
      }
      if (to.getRoomType() != RoomType.WAITING && !to.getPatients().isEmpty()) {
        throw new IllegalArgumentException("Cannot move patient because room is full.");
      }
      if (from.getPatients().contains(patient)) {
        from.removePatient(patient);
        to.addPatient(patient);
      } else {
        throw new IllegalArgumentException("Patient does not exist in room");
      }
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  @Override
  public void registerNewPatient(Patient patient) {
    if (getPatients().contains(patient)) {
      throw new IllegalArgumentException("Patient already exists");
    }
    Room waitingRoom = getPrimaryWaitingRoom();
    waitingRoom.addPatient(patient);
  }

  @Override
  public void registerNewStaffMember(Staff staffMember) {
    if (staffList.containsKey(staffMember.getFullName())) {
      throw new IllegalArgumentException("Staff member already exists");
    }
    staffList.put(staffMember.getFullName(), staffMember);
  }

  @Override
  public void sendPatientHome(Patient patient, ClinicalStaff staff) {
    Optional<Room> room = rooms.values().stream()
            .filter(r -> r.getPatients().contains(patient))
            .findFirst();
    ClinicalStaff clinicalStaff = (ClinicalStaff) staffList().get(staff.getFullName());
    if (room.isPresent()) {
      room.get().removePatient(patient);
      patient.removeStaffHelper(clinicalStaff);
    } else {
      throw new IllegalArgumentException("Patient does not exist in clinic");
    }
  }

  @Override
  public void assignStaffToPatient(Patient patient, ClinicalStaff staff) {
    if (staffList.get(staff.getFullName()) == null) {
      throw new IllegalArgumentException("Staff member does not exist");
    }
    if (patient.getStaffHelpers().contains(staff)) {
      throw new IllegalArgumentException("Staff member already assigned to patient");
    }
    patient.addStaffHelper(staff);
    staff.addAssignedPatient(patient);
    System.out.println("Staff member " + staff.getFullName() + " has been assigned to patient "
            + patient.getFullName());
  }

  @Override
  public void assignRecordToPatient(Patient patient, VisitRecord record) {
    patient.addRecordVisit(record);
  }

  @Override
  public String getSpecificRoomDetails(int roomNumber) {
    Room room = rooms.get(roomNumber);
    if (room == null) {
      throw new IllegalArgumentException("Room does not exist");
    }
    return room.getSpecificDetails();
  }

  @Override
  public String seatingChart() {
    StringBuilder seatingChart = new StringBuilder();
    rooms.values().forEach(room -> {
      seatingChart.append(String.format("%s (Room #: %d)\nPatients:\n", room.getRoomName(),
              room.getRoomNumber()));
      room.getPatients().forEach(patient ->
              seatingChart.append(String.format("- %s\n", patient.getFullName()))
      );
      seatingChart.append("\n");
    });
    return seatingChart.toString();
  }

  @Override
  public void deactivateStaffMember(Staff staffMember) {
    String fullName = staffMember.getFullName();
    if (!staffList.containsKey(fullName)) {
      throw new IllegalArgumentException("Staff member does not exist");
    }
    staffList.remove(fullName);
    if (staffMember instanceof ClinicalStaff) {
      rooms.values().forEach(room ->
              room.getPatients().forEach(patient ->
                      patient.getStaffHelpers().remove(staffMember)));
    }
  }

  @Override
  public void deactivateClinicalStaffMember(ClinicalStaff staffMember) {
    String fullName = staffMember.getFullName();
    if (!staffList.containsKey(fullName)) {
      throw new IllegalArgumentException("Clinical Staff member does not exist");
    }
    staffList.remove(fullName);
    rooms.values().forEach(r -> r.getPatients().forEach(p -> p.getStaffHelpers()
            .remove(staffMember)));
    System.out.println("Staff member " + fullName + " has been deactivated");
  }

  @Override
  public void displayClinicalStaffList() {
    System.out.println("Clinical Staff List:");
    getClinicalStaff().forEach(staff -> System.out.println(staff.getFullNameWithTitle()));
  }

  @Override
  public void displayRoomList() {
    System.out.println("Room List:");
    rooms.values().forEach(r -> System.out.println(r.getRoomName()
            + " (Room #: " + r.getRoomNumber() + ")"));
  }

  @Override
  public void displayPatientAndHelpers() {
    System.out.println("Patient List:");
    for (Room room : rooms.values()) {
      room.getPatients().forEach(patient -> printPatientAndHelpers(patient, room));
    }
  }

  private void printPatientAndHelpers(Patient patient, Room room) {
    System.out.println(patient.getFullName() + " Room #: " + room.getRoomNumber());
    System.out.println(patient.toStringStaffHelpers());
  }

  @Override
  public void displayPatientDetails(Patient patient) {
    System.out.println(patient.toString());
    System.out.println(patient.toStringWithVisits());
    System.out.println(patient.toStringStaffHelpers());
  }

  @Override
  public List<ClinicalStaff> getRemainingClinicalStaffNotUsedByPatient(Patient patient) {
    return getClinicalStaff().stream()
            .filter(s -> !patient.getStaffHelpers().contains(s))
            .toList();
  }

  @Override
  public void displayPatientList() {
    System.out.println("Patient List:");
    rooms.values().forEach(r ->
            r.getPatients().forEach(p ->
                    System.out.println(p.getFullName() + " Room #: " + r.getRoomNumber()
                            + "Staff Helpers:" + p.getStaffHelpers().size())));
  }

  @Override
  public String displayEverything() {
    StringBuilder everything = new StringBuilder();
    everything.append("Clinic Name: ").append(clinicName).append("\n");
    everything.append("Rooms:\n");
    rooms.values().forEach(r -> everything.append(r.getRoomName()).append(" (Room #: ")
            .append(r.getRoomNumber()).append(")\n"));
    everything.append("Staff:\n");
    staffList.values().forEach(s -> everything.append(s.getFullName()).append("\n"));
    everything.append("Patients:\n");
    rooms.values().forEach(r -> r.getPatients().forEach(p -> everything.append(p.getFullName())
            .append(" Room #: ").append(r.getRoomNumber()).append("\n")));
    everything.append("Staff Helpers:\n");
    rooms.values().forEach(r -> r.getPatients().forEach(p -> p.getStaffHelpers().forEach(s ->
            everything.append(s.getFullNameWithTitle()).append("\n"))));
    return everything.toString();
  }

  @Override
  public List<Patient> getPatientsFromClinicalStaff(ClinicalStaff staff) {
    return getPatients().stream()
            .filter(p -> p.getStaffHelpers().contains(staff))
            .toList();
  }

  @Override
  public void displayPatientsForClinicalStaff(ClinicalStaff staff) {
    System.out.println("Assigned Patients for " + staff.getFullNameWithTitle() + ":");
    List<Patient> assignedPatients = getPatientsFromClinicalStaff(staff);
    if (assignedPatients.isEmpty()) {
      System.out.println("No patients assigned.");
    } else {
      assignedPatients.forEach(p -> System.out.println("- " + p.getFullName()));
    }
  }

  @Override
  public void displayTotalNumberOfPastAssignees() {
    System.out.println("Total Number of Past Assignees:");
    getClinicalStaff().forEach(staff -> System.out.println(staff.getFullNameWithTitle()
            + ": " + staff.getNumPatientsAssignedFromPast()));

  }

  @Override
  public void removeClinicalStaffFromPatient(ClinicalStaff staff, Patient patient) {
    patient.removeStaffHelper(staff);
  }

  @Override
  public List<Patient> getRecentFrequentVisitors() {
    LocalDate oneYearAgo = LocalDate.now().minusDays(365);
    List<Patient> patients = getPatients();
    for (Patient patient : patients) {
      long recentVisitCount = patient.getVisitHistory().stream()
              .filter(v -> v.getRegistrationTime().isAfter(oneYearAgo.atStartOfDay()))
              .count();
      if (recentVisitCount >= 2) {
        return List.of(patient);
      }
    }
    return List.of();
  }

  @Override
  public List<Patient> getNonRecentFrequentVisitors() {
    LocalDate oneYearAgo = LocalDate.now().minusDays(365);
    List<Patient> patients = getPatients();
    for (Patient patient : patients) {
      if (patient.getVisitHistory().stream()
              .noneMatch(v -> v.getRegistrationTime()
                      .isBefore(oneYearAgo.atStartOfDay()))) {
        return List.of(patient);
      }
    }
    return List.of();
  }

  @Override
  public void displayRecentFrequentVisitors() {
    List<Patient> recentFrequentVisitors = getRecentFrequentVisitors();
    if (recentFrequentVisitors.isEmpty()) {
      System.out.println("No recent frequent visitors.");
    } else {
      System.out.println("Recent Frequent Visitors:");
      recentFrequentVisitors.forEach(p -> System.out.println("- " + p.getFullName()));
    }
  }

  @Override
  public void displayNonRecentFrequentVisitors() {
    List<Patient> nonRecentFrequentVisitors = getNonRecentFrequentVisitors();
    if (nonRecentFrequentVisitors.isEmpty()) {
      System.out.println("No non-recent frequent visitors.");
    } else {
      System.out.println("Non-Recent Frequent Visitors:");
      nonRecentFrequentVisitors.forEach(p -> System.out.println("- " + p.getFullName()));
    }
  }

  @Override
  public List<Room> getRoomsList() {
    return rooms.values().stream()
            .toList();
  }

  @Override
  public int hashCode() {
    return clinicName.hashCode() + rooms.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Clinic other)) {
      return false;
    }
    return clinicName.equals(other.clinicName) && rooms.equals(other.rooms);
  }
}
