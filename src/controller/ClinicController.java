package controller;

import commands.AddRecordToExistingPatientCommand;
import commands.AssignPatientToRoomCommand;
import commands.Command2;
import commands.DeactivateStaffMemberCommand;
import commands.RegisterClinicalStaffCommand;
import commands.RegisterNewPatientCommand;
import commands.SendPatientHomeCommand;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.clinic.Clinic;
import model.patient.Patient;
import model.patient.VisitRecord;
import model.room.Room;
import model.staff.ClinicalStaff;
import model.staff.Staff;
import utils.LoadFile;
import view.MainView;
import view.components.AddVisitRecordForm;
import view.components.MovePatientMapView;
import view.components.RoomView;
import view.components.SendPatientHomeDialog;
import view.components.ViewStaffHelpers;
import view.components.ViewVisitRecords;
import view.listeners.FileMenu;
import view.listeners.MovePatientListener;
import view.listeners.PatientActionListener;
import view.listeners.RoomSelectionListener;
import view.listeners.StaffMemberActionListener;
import view.listeners.VisitRecordActionListener;

/**
 * Represents a controller for the clinic application.
 */
public class ClinicController implements RoomSelectionListener, PatientActionListener,
        VisitRecordActionListener, MovePatientListener, FileMenu, StaffMemberActionListener {
  private static final Logger logger = Logger.getLogger("Milestone-4");
  private static final String LOG_DIR = "res/milestone4_logs";
  private Clinic clinic;
  private final MainView mainView;

  /**
   * Creates a new ClinicController object with the given clinic and clinic view.
   *
   * @param clinic the clinic - The main model object.
   * @param mainView the main view - The main view object.
   */
  public ClinicController(Clinic clinic, MainView mainView) {
    this.clinic = clinic;
    this.mainView = mainView;
    this.mainView.getMapView().setRoomSelectionListener(this);
    this.mainView.getFireStaffMemberTable().setStaffMemberActionListener(this);
    initializeLogger();
    setupListeners();
  }

  private void setupListeners() {
    registerMenuListeners();
    registerPatientListeners();
    registerStaffListeners();
  }

  private void registerMenuListeners() {
    addActionListener(mainView.getLoadClinicFileItem(), this::loadClinicFile);
    addActionListener(mainView.getQuitItem(), this::systemQuit);
    addActionListener(mainView.getClearRecordsItem(), this::clearRecords);
  }

  private void registerPatientListeners() {
    addActionListener(mainView.getRegisterPatientForm().getRegisterPatientButton(),
            this::registerNewPatient);
  }

  private void registerStaffListeners() {
    addActionListener(mainView.getRegisterStaffMemberForm().getRegisterStaffButton(),
            this::registerNewStaffMember);
  }

  private void addActionListener(JComponent component, Runnable action) {
    if (component instanceof AbstractButton button) {
      button.addActionListener(e -> action.run());
    }
  }

  private void registerNewPatient() {
    executeCommand(
            new RegisterNewPatientCommand(clinic, logger, mainView.getRegisterPatientForm()),
            "Patient registered successfully!",
            "New patient registered.",
            "Please enter correct registration."
    );
  }

  private void registerNewStaffMember() {
    executeCommand(
            new RegisterClinicalStaffCommand(clinic, logger, mainView.getRegisterStaffMemberForm()),
            "Staff member registered successfully!",
            "New staff member registered.",
            "Please enter correct registration."
    );
    updateStaffMembers();
  }

  private void executeCommand(Command2 command, String successMessage, String logMessage,
                              String errorMessage) {
    try {
      command.execute();
      showInfoDialog(successMessage);
      mainView.appendToOutput(logMessage);
    } catch (IllegalArgumentException e) {
      showErrorDialog(e.getMessage());
    }
  }

  private void updateRooms() {
    mainView.getMapView().setRooms(clinic.getRoomsList());
  }

  private void updateStaffMembers() {
    mainView.getFireStaffMemberTable().clearStaffMembers();
    mainView.getFireStaffMemberTable().setStaffMembers(clinic.getStaff());
  }

  private File showFileChooser() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files (*.txt)", "txt"));
    return fileChooser.showOpenDialog(mainView) == JFileChooser.APPROVE_OPTION
            ? fileChooser.getSelectedFile()
            : null;
  }

  private void initializeLogger() {
    try {
      Files.createDirectories(Paths.get(LOG_DIR));
      String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
      FileHandler fileHandler = new FileHandler(LOG_DIR
              + "/clinic_log_" + timestamp + ".txt", true);
      fileHandler.setFormatter(new SimpleFormatter());
      logger.addHandler(fileHandler);
      logger.setUseParentHandlers(false);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void logClinicState() {
    logger.info("Closing application. LIST OF ALL ROOMS, PATIENTS, AND STAFF:");
    logger.info(clinic.displayEverything());
  }

  private void showInfoDialog(String message) {
    JOptionPane.showMessageDialog(mainView, message, "Success", JOptionPane.INFORMATION_MESSAGE);
  }

  private void showErrorDialog(String message) {
    JOptionPane.showMessageDialog(mainView, message, "Error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void systemQuit() {
    logClinicState();
    System.exit(0);
  }

  @Override
  public void clearRecords() {
    clinic = new Clinic("", new HashMap<>(0), new HashMap<>(0));
    updateRooms();
    updateStaffMembers();
  }

  @Override
  public void loadClinicFile() {
    File file = showFileChooser();
    if (file != null) {
      LoadFile loadFile = new LoadFile();
      loadFile.loadClinicData(file.getAbsolutePath());
      clinic = loadFile.getClinic();
      updateRooms();
      updateStaffMembers();
      mainView.appendToOutput("Loaded: " + file.getAbsolutePath());
    }
  }

  @Override
  public void onRoomSelected(Room room) {
    if (room.getPatients().isEmpty()) {
      showInfoDialog("No patients in " + room.getRoomName());
    } else {
      mainView.appendToOutput("Viewing patients in " + room.getRoomName());
      new RoomView(mainView, room.getRoomName(), room.getPatients(), this);
    }
  }

  @Override
  public void onMovePatientToRoom(Patient patient, Room room) {
    executeCommand(
            new AssignPatientToRoomCommand(clinic, logger, patient, room),
            patient.getFullName() + " moved to " + room.getRoomName(),
            "Patient moved to " + room.getRoomName(),
            "Failed to move patient."
    );
  }

  @Override
  public void onViewVisitRecords(Patient patient) {
    mainView.appendToOutput("Viewing visit records.");
    new ViewVisitRecords(mainView, patient, this);
  }

  @Override
  public void onViewStaffHelpers(Patient patient) {
    mainView.appendToOutput("Viewing staff helpers.");
    new ViewStaffHelpers(mainView, patient, clinic.getClinicalStaff());
  }

  @Override
  public void onMovePatient(Patient patient) {
    mainView.appendToOutput("Moving patient to another room.");
    new MovePatientMapView(mainView, clinic.getRoomsList(), patient, this);
  }

  @Override
  public void onViewSendPatientHome(Patient patient) {
    mainView.appendToOutput("To send home patient view.");
    new SendPatientHomeDialog(mainView, clinic.getClinicalStaff(), patient, this);
  }

  @Override
  public void onSendPatientHome(Patient patient, ClinicalStaff staffMember) {
    executeCommand(
            new SendPatientHomeCommand(clinic, logger, patient, staffMember),
            patient.getFullName() + " sent home by " + staffMember.getFullNameWithTitle(),
            patient.getFullName() + " sent home by " + staffMember.getFullNameWithTitle(),
            "Failed to send patient home."
    );
    updateRooms();
  }

  @Override
  public void onViewAddVisitRecordForm(Patient patient) {
    mainView.appendToOutput("Adding visit record to patient.");
    new AddVisitRecordForm(mainView, patient, this);
  }

  @Override
  public void addVisitRecord(Patient patient, LocalDateTime visitRecordTime,
                             String visitRecordCompliant, double visitRecordTemperature) {
    VisitRecord visitRecord = new VisitRecord(visitRecordTime, visitRecordCompliant,
            visitRecordTemperature);
    executeCommand(
            new AddRecordToExistingPatientCommand(clinic, logger, patient, visitRecord),
            "Visit record added successfully!",
            "Visit record added to patient.",
            "Please enter correct visit record."
    );
  }

  @Override
  public void onSendStaffMemberHome(Staff staffMember) {
    executeCommand(
            new DeactivateStaffMemberCommand(clinic, logger, staffMember),
            staffMember.getFullName() + " sent home.",
            staffMember.getFullName() + " sent home.",
            "Failed to send staff member home."
    );
    updateStaffMembers();
  }
}
