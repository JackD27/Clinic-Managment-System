package view.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import model.patient.Patient;
import view.MainView;
import view.listeners.PatientActionListener;

/**
 * Represents a view for a room in the clinic.
 */
public class RoomView extends JDialog {

  private final JTable patientTable;

  /**
   * Creates a new RoomView with the given owner, room name, patients, and action listener.
   *
   * @param owner the owner of the dialog
   * @param roomName the name of the room
   * @param patients the patients in the room
   * @param actionListener the action listener for the dialog
   */
  public RoomView(MainView owner, String roomName, List<Patient> patients,
                  PatientActionListener actionListener) {
    super((JFrame) SwingUtilities.getWindowAncestor(owner), roomName, true);
    setLayout(new BorderLayout());
    setMinimumSize(new Dimension(650, 300));
    setSize(650, 400);


    // Create table model and table
    DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Full Name",
        "First Name",
        "Last Name",
        "Date Of Birth",
        "# of Visits",
        "# of Helpers"}, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    patientTable = new JTable(tableModel);

    // Populate the table with patients
    for (Patient patient : patients) {
      tableModel.addRow(new Object[]{
              patient.getFullName(),
              patient.getFirstName(),
              patient.getLastName(),
              patient.getDob(),
              patient.getVisitHistory().size(),
              patient.getStaffHelpers().size()
      });
    }

    // Add table to a scroll pane
    JScrollPane scrollPane = new JScrollPane(patientTable);
    add(scrollPane, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
    add(buttonPanel, BorderLayout.EAST);
    JButton viewVisitRecordsButton = new JButton("View Visit Records");
    JButton toSendPatientHomeButton = new JButton("Send Patient Home");
    toSendPatientHomeButton.setBackground(Color.PINK);
    buttonPanel.add(viewVisitRecordsButton);
    buttonPanel.add(Box.createVerticalStrut(8));
    JButton viewStaffHelpersButton = new JButton("View Staff Helpers");
    JButton movePatientButton = new JButton("Move Patient to Another Room");
    buttonPanel.add(viewStaffHelpersButton);
    buttonPanel.add(Box.createVerticalStrut(8));
    buttonPanel.add(movePatientButton);
    buttonPanel.add(Box.createVerticalStrut(8));
    buttonPanel.add(toSendPatientHomeButton);

    viewVisitRecordsButton.addActionListener(e -> {
      if (patientTable.getSelectedRow() == -1) {
        JOptionPane.showMessageDialog(this, "Please select a patient to view visit records for.");
        return;
      }
      actionListener.onViewVisitRecords(patients.get(patientTable.getSelectedRow()));
      dispose();
    });

    viewStaffHelpersButton.addActionListener(e -> {
      if (patientTable.getSelectedRow() == -1) {
        JOptionPane.showMessageDialog(this, "Please select a patient to view staff helpers for.");
        return;
      }
      actionListener.onViewStaffHelpers(patients.get(patientTable.getSelectedRow()));
      dispose();
    });

    movePatientButton.addActionListener(e -> {
      if (patientTable.getSelectedRow() == -1) {
        JOptionPane.showMessageDialog(this, "Please select a patient to move to another room.");
        return;
      }
      actionListener.onMovePatient(patients.get(patientTable.getSelectedRow()));
      dispose();
    });

    toSendPatientHomeButton.addActionListener(e -> {
      if (patientTable.getSelectedRow() == -1) {
        JOptionPane.showMessageDialog(this, "Please select a patient to send home.");
        return;
      }
      actionListener.onViewSendPatientHome(patients.get(patientTable.getSelectedRow()));
      dispose();
    });

    // Add close button
    JButton closeButton = new JButton("Close");
    closeButton.addActionListener(e -> dispose());
    add(closeButton, BorderLayout.SOUTH);

    // Center the dialog on screen
    setLocationRelativeTo(owner);
    setVisible(true);
  }
}
