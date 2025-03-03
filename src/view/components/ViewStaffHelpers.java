package view.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.patient.Patient;
import model.staff.ClinicalStaff;
import view.MainView;

/**
 * Represents a dialog for viewing staff helpers for a patient.
 */
public class ViewStaffHelpers extends JDialog {
  private final JTable staffHelpersTable;
  private final JTable availableStaffTable;
  private Set<ClinicalStaff> staffHelpers;
  private final List<ClinicalStaff> availableStaff;
  private final List<ClinicalStaff> staffList;

  /**
   * Creates a new ViewStaffHelpers dialog.
   *
   * @param owner the owner of the dialog
   * @param patient the patient to view staff helpers for
   * @param staffList the list of staff members
   */
  public ViewStaffHelpers(MainView owner, Patient patient, List<ClinicalStaff> staffList) {
    super(owner, "Staff Helpers for " + patient.getFullName(), true);
    this.staffList = staffList;
    this.staffHelpers = patient.getStaffHelpers();
    this.availableStaff = getAvailableStaff();

    setLayout(new BorderLayout());
    setMinimumSize(new Dimension(1100, 300));
    setSize(1100, 400);

    staffHelpers = patient.getStaffHelpers();

    // Create table model and table
    DefaultTableModel staffTableModel = new DefaultTableModel(new String[]{
        "Full Name", "Education", "Job Name", "# of Past Patients"}, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    staffHelpersTable = new JTable(staffTableModel);

    DefaultTableModel availableStaffTableModel = new DefaultTableModel(new String[]{
        "Full Name", "Education", "Job Name", "# of Past Patients"}, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };

    availableStaffTable = new JTable(availableStaffTableModel);

    populateStaffHelpersTable(staffTableModel);
    populateAvailableStaffTable(availableStaffTableModel);


    // Add table to a scroll pane
    JScrollPane staffScrollPane = new JScrollPane(staffHelpersTable);
    staffScrollPane.setBorder(BorderFactory.createTitledBorder("Current Staff Helpers"));
    JScrollPane availableStaffScrollPane = new JScrollPane(availableStaffTable);
    availableStaffScrollPane.setBorder(BorderFactory.createTitledBorder("Available Staff to Add"));

    JPanel tablePanel = new JPanel();
    tablePanel.setLayout(new GridLayout(1, 3, 1, 1));
    tablePanel.add(staffScrollPane);
    tablePanel.add(createSwapButtonsPanel());
    tablePanel.add(availableStaffScrollPane);

    add(tablePanel, BorderLayout.CENTER);

    JButton closeButton = new JButton("Save and Close");
    closeButton.addActionListener(e -> dispose());
    add(closeButton, BorderLayout.SOUTH);

    setLocationRelativeTo(owner);
    setVisible(true);
  }

  private void populateAvailableStaffTable(DefaultTableModel availableStaffTableModel) {
    if (availableStaff == null || availableStaff.isEmpty()) {
      availableStaffTableModel.addRow(new Object[]{"No available staff", "", "", ""});
    } else {
      for (ClinicalStaff staff : availableStaff) {
        availableStaffTableModel.addRow(new Object[]{
                staff.getFullNameWithTitle(),
                staff.getEducationLevel(),
                staff.getJobName(),
                staff.getNumPatientsAssignedFromPast()
        });
      }
    }
  }

  private void populateStaffHelpersTable(DefaultTableModel staffTableModel) {
    if (staffHelpers == null || staffHelpers.isEmpty()) {
      staffTableModel.addRow(new Object[]{"No staff helpers", "", "", ""});
    } else {
      for (ClinicalStaff staff : staffHelpers) {
        staffTableModel.addRow(new Object[]{
                staff.getFullNameWithTitle(),
                staff.getEducationLevel(),
                staff.getJobName(),
                staff.getNumPatientsAssignedFromPast()
        });
      }
    }
  }

  private JPanel createSwapButtonsPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    JButton moveToAvailableButton = new JButton("Move to Available Staff");
    JButton moveToStaffHelpersButton = new JButton("Move to Staff Helpers");

    panel.setPreferredSize(new Dimension(100, 200));

    panel.add(moveToAvailableButton);
    panel.add(Box.createVerticalStrut(8));
    panel.add(moveToStaffHelpersButton);
    panel.add(Box.createVerticalStrut(8));

    moveToAvailableButton.addActionListener(e -> {
      int selectedStaffIndex = staffHelpersTable.getSelectedRow();

      if (selectedStaffIndex != -1 && !staffHelpers.isEmpty()) {
        ClinicalStaff selectedStaff = staffHelpers
                .toArray(new ClinicalStaff[0])[selectedStaffIndex];
        moveStaffToAvailable(selectedStaff);
        refreshTable();
      } else {
        JOptionPane.showMessageDialog(this, "Please select a staff member to move.");
      }
    });

    moveToStaffHelpersButton.addActionListener(e -> {
      int selectedAvailableStaffIndex = availableStaffTable.getSelectedRow();

      if (selectedAvailableStaffIndex != -1 && !availableStaff.isEmpty()) {
        ClinicalStaff selectedAvailableStaff = availableStaff.get(selectedAvailableStaffIndex);
        moveStaffToStaffHelpers(selectedAvailableStaff);
        refreshTable();
      } else {
        JOptionPane.showMessageDialog(this, "Please select a staff member to move.");
      }
    });
    return panel;
  }

  private void moveStaffToAvailable(ClinicalStaff selectedStaff) {
    if (staffHelpers.contains(selectedStaff)) {
      staffHelpers.remove(selectedStaff);
      availableStaff.add(selectedStaff);
    }
  }

  private void moveStaffToStaffHelpers(ClinicalStaff selectedAvailableStaff) {
    if (availableStaff.contains(selectedAvailableStaff)) {
      availableStaff.remove(selectedAvailableStaff);
      staffHelpers.add(selectedAvailableStaff);
    }
  }

  private List<ClinicalStaff> getAvailableStaff() {
    List<ClinicalStaff> availableStaff = new ArrayList<>(staffList);
    availableStaff.removeAll(staffHelpers);
    return availableStaff;
  }

  /**
   * Refreshes the table.
   */
  public void refreshTable() {
    DefaultTableModel staffHelpersTableModel = (DefaultTableModel) staffHelpersTable.getModel();
    staffHelpersTableModel.setRowCount(0);
    populateStaffHelpersTable(staffHelpersTableModel);

    DefaultTableModel availableStaffTableModel = (DefaultTableModel) availableStaffTable.getModel();
    availableStaffTableModel.setRowCount(0);
    populateAvailableStaffTable(availableStaffTableModel);
  }
}
