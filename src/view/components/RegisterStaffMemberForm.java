package view.components;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Represents a form to register a new staff member.
 */
public class RegisterStaffMemberForm extends JPanel {
  private final JTextField staffFirstName;
  private final JTextField staffLastName;
  private final JComboBox<String> educationLevelBox;
  private final JComboBox<String> jobNameComboBox;
  private final JButton registerStaffButton;

  /**
   * Creates a new RegisterStaffMemberForm.
   */
  public RegisterStaffMemberForm() {
    setLayout(new GridBagLayout());
    GridBagConstraints gbc = createGridBagConstraints();

    setBorder(BorderFactory.createTitledBorder("Register Staff Member"));
    JLabel label = new JLabel("Please enter the following information to register a "
            + "new staff member.");
    label.setFont(new Font("Arial", Font.PLAIN, 13));
    add(label, gbc);
    gbc.gridy = 0;
    add(new JLabel("Staff Information."), gbc);

    addLabeledField(gbc, "Staff First Name:", 1, staffFirstName = new JTextField());
    addLabeledField(gbc, "Staff Last Name:", 2, staffLastName = new JTextField());

    // Job Title ComboBox
    String[] jobTitles = {"Doctoral", "Masters", "Allied"};
    educationLevelBox = new JComboBox<>(jobTitles);
    addLabeledField(gbc, "Select Education level:", 4, educationLevelBox);

    // Job Name ComboBox
    String[] jobNames = {"Physician", "Nurse"};
    jobNameComboBox = new JComboBox<>(jobNames);
    addLabeledField(gbc, "Select Job Title:", 5, jobNameComboBox);

    addButton(gbc, 6, registerStaffButton = new JButton("Register Staff Member"));
  }

  private GridBagConstraints createGridBagConstraints() {
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    return gbc;
  }

  private void addLabeledField(GridBagConstraints gbc, String label, int y, JComponent field) {
    gbc.gridx = 0;
    gbc.gridy = y;
    add(new JLabel(label), gbc);

    gbc.gridx = 1;
    field.setPreferredSize(new Dimension(200, 25));
    add(field, gbc);
  }

  private void addButton(GridBagConstraints gbc, int y, JButton button) {
    gbc.gridx = 0;
    gbc.gridy = y;
    gbc.gridwidth = 2;
    add(button, gbc);
  }

  /**
   * Returns the first name of the staff member.
   *
   * @return the first name of the staff member
   */
  public String getFirstName() {
    return staffFirstName.getText();
  }

  /**
   * Returns the last name of the staff member.
   *
   * @return the last name of the staff member
   */
  public String getLastName() {
    return staffLastName.getText();
  }

  /**
   * Returns the education level of the staff member.
   *
   * @return the education level of the staff member
   */
  public String getEducationLevel() {
    return (String) educationLevelBox.getSelectedItem();
  }

  /**
   * Returns the job name of the staff member.
   *
   * @return the job name of the staff member
   */
  public String getJobName() {
    return (String) jobNameComboBox.getSelectedItem();
  }

  /**
   * Returns the register staff button.
   *
   * @return the register staff button
   */
  public JButton getRegisterStaffButton() {
    return registerStaffButton;
  }

  /**
   * Clears the fields in the form.
   */
  public void clearFields() {
    staffFirstName.setText("");
    staffLastName.setText("");
  }
}
