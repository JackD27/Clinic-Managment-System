package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import view.components.FireStaffMemberTable;
import view.components.MapView;
import view.components.RegisterPatientForm;
import view.components.RegisterStaffMemberForm;
import view.components.WelcomeScreenView;

/**
 * The main view of the application.
 */
public class MainView extends JFrame {
  private JTextArea outputArea;
  private JMenuItem loadClinicFileItem;
  private JMenuItem clearRecordsItem;
  private JMenuItem quitItem;
  private final MapView mapView;
  private final RegisterPatientForm registerPatientForm;
  private final RegisterStaffMemberForm registerStaffMemberForm;
  private final FireStaffMemberTable fireStaffMemberTable;

  /**
   * Creates a new MainView object.
   */
  public MainView() {
    registerPatientForm = new RegisterPatientForm();
    mapView = new MapView();
    registerStaffMemberForm = new RegisterStaffMemberForm();
    fireStaffMemberTable = new FireStaffMemberTable();
    showWelcomeDialog();

    initializeFrame();

    initializeMenu();
    JTabbedPane tabbedPane = new JTabbedPane();
    tabbedPane.addTab("Map of Clinic", mapView);
    tabbedPane.addTab("Register Patient", registerPatientForm);
    tabbedPane.addTab("Register Staff Member", registerStaffMemberForm);
    tabbedPane.addTab("Fire Staff Member", fireStaffMemberTable);

    initializeOutputArea();
    add(tabbedPane, BorderLayout.CENTER);

    setLocationRelativeTo(null);
    setVisible(true);
  }

  private void initializeFrame() {
    setTitle("Clinic Management System");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setMinimumSize(new Dimension(800, 800));
    setLayout(new BorderLayout());
  }

  private void initializeMenu() {
    loadClinicFileItem = new JMenuItem("Load Clinic File");
    clearRecordsItem = new JMenuItem("Clear All Records");
    quitItem = new JMenuItem("Quit");
    JMenu fileMenu = new JMenu("File");
    fileMenu.add(loadClinicFileItem);
    fileMenu.add(clearRecordsItem);
    fileMenu.add(quitItem);
    JMenuBar menuBar = new JMenuBar();
    menuBar.add(fileMenu);
    setJMenuBar(menuBar);
  }

  private void initializeOutputArea() {
    outputArea = new JTextArea(10, 10);
    outputArea.setEditable(false);
    add(new JScrollPane(outputArea), BorderLayout.SOUTH);
  }

  private void showWelcomeDialog() {
    WelcomeScreenView dialog = new WelcomeScreenView(this);
    dialog.showDialog();
  }

  /**
   * Appends the given text to the output area.
   *
   * @param text the text to append
   */
  public void appendToOutput(String text) {
    outputArea.append(text + "\n");
  }

  /**
   * Returns the load clinic file menu item.
   */
  public JMenuItem getLoadClinicFileItem() {
    return loadClinicFileItem;
  }

  /**
   * Returns the clear records menu item.
   */
  public JMenuItem getClearRecordsItem() {
    return clearRecordsItem;
  }

  /**
   * Returns the quit menu item.
   */
  public JMenuItem getQuitItem() {
    return quitItem;
  }

  /**
   * Returns the map view.
   */
  public MapView getMapView() {
    return mapView;
  }

  /**
   * Returns the register patient form.
   */
  public RegisterPatientForm getRegisterPatientForm() {
    return registerPatientForm;
  }

  /**
   * Returns the register staff member form.
   */
  public RegisterStaffMemberForm getRegisterStaffMemberForm() {
    return registerStaffMemberForm;
  }

  /**
   * Returns the fire staff member table.
   */
  public FireStaffMemberTable getFireStaffMemberTable() {
    return fireStaffMemberTable;
  }
}
