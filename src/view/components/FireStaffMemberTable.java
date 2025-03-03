package view.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.staff.Staff;
import view.listeners.StaffMemberActionListener;

/**
 * Represents a table for firing staff members.
 */
public class FireStaffMemberTable extends JPanel {

  private final JTable staffTable;
  private List<Staff> staffList;
  private StaffMemberActionListener staffMemberActionListener;
  private final DefaultTableModel tableModel;

  /**
   * Creates a new FireStaffMemberTable.
   */
  public FireStaffMemberTable() {
    super();
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setBorder(BorderFactory.createTitledBorder("Fire Staff Member"));

    staffList = new ArrayList<>();

    tableModel = new DefaultTableModel(new String[]{"Full Name",
        "First Name",
        "Last Name",
        "Job Name",
        "Education Level"}, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    staffTable = new JTable(tableModel);

    JButton letGoButton = new JButton("Let go");
    letGoButton.addActionListener(e -> {
      Staff staffMember = getSelectedStaffMember();
      if (staffMember != null) {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to let go of " + staffMember.getFullName() + "?",
                "Confirm",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
          staffMemberActionListener.onSendStaffMemberHome(staffMember);
        }
      }
    });
    letGoButton.setBackground(Color.PINK);


    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
    buttonPanel.add(letGoButton);
    panel.add(buttonPanel, BorderLayout.NORTH);
    JScrollPane scrollPane = new JScrollPane(staffTable);
    panel.add(scrollPane, BorderLayout.CENTER);
    add(panel, BorderLayout.CENTER);
  }

  /**
   * Clears the staff members from the table.
   */
  public void clearStaffMembers() {
    tableModel.setRowCount(0);
  }

  /**
   * Sets the staff members to display in the table.
   *
   * @param staffMembers the staff members to display
   */
  public void setStaffMembers(List<Staff> staffMembers) {
    staffList = staffMembers;
    for (Staff staffMember : staffList) {
      tableModel.addRow(new Object[]{
              staffMember.getFullName(),
              staffMember.getFirstName(),
              staffMember.getLastName(),
              staffMember.getJobName(),
              staffMember.getEducationLevel()
      });
    }
  }

  /**
   * Sets the staff member action listener.
   *
   * @param staffMemberActionListener the staff member action listener
   */
  public void setStaffMemberActionListener(StaffMemberActionListener staffMemberActionListener) {
    this.staffMemberActionListener = staffMemberActionListener;
  }

  /**
   * Gets the selected staff member from the table.
   *
   * @return the selected staff member
   */
  public Staff getSelectedStaffMember() {
    int selectedRow = staffTable.getSelectedRow();
    if (selectedRow >= 0) {
      return staffList.get(selectedRow);
    }
    return null;
  }
}
