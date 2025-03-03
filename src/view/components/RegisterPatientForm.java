package view.components;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDateTime;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

/**
 * Represents a form to register a new patient.
 */
public class RegisterPatientForm extends JPanel {
  private final JTextField patientFirstName;
  private final JTextField patientLastName;
  private final JTextField visitRecordComplaint;
  private final JSpinner visitRecordTemperature;
  private final JSpinner dobYearSpinner;
  private final JSpinner dobMonthSpinner;
  private final JSpinner dobDaySpinner;
  private final JSpinner visitRecordYearSpinner;
  private final JSpinner visitRecordMonthSpinner;
  private final JSpinner visitRecordDaySpinner;
  private final JSpinner visitRecordHourSpinner;
  private final JSpinner visitRecordMinuteSpinner;
  private final JSpinner visitRecordSecondSpinner;
  private final JButton registerPatientButton;

  /**
   * Creates a new RegisterPatientForm.
   */
  public RegisterPatientForm() {
    setLayout(new GridBagLayout());
    GridBagConstraints gbc = createGridBagConstraints();

    setBorder(BorderFactory.createTitledBorder("Register Patient"));

    JLabel label = new JLabel("Please enter the following information to register a new patient.");
    label.setFont(new Font("Arial", Font.PLAIN, 13));
    add(label, gbc);

    gbc.gridy = 0;
    add(new JLabel("Patient Information."), gbc);
    addLabeledField(gbc, "Patient First Name:", 1, patientFirstName = new JTextField());
    addLabeledField(gbc, "Patient Last Name:", 2, patientLastName = new JTextField());

    gbc.gridy = 3;
    gbc.gridx = 0;
    add(new JLabel("Date of Birth:"), gbc);

    dobYearSpinner = new JSpinner(new SpinnerNumberModel(2000, 1900, 2100, 1));
    gbc.gridx = 1;
    add(dobYearSpinner, gbc);

    dobMonthSpinner = new JSpinner(new SpinnerNumberModel(11, 1, 12, 1));
    gbc.gridx = 2;
    add(dobMonthSpinner, gbc);

    dobDaySpinner = new JSpinner(new SpinnerNumberModel(3, 1, 31, 1));
    gbc.gridx = 3;
    add(dobDaySpinner, gbc);

    gbc.gridy = 5;
    gbc.gridx = 1;
    add(new JLabel("Visit Record Information"), gbc);
    gbc.gridy = 6;
    gbc.gridx = 0;
    add(new JLabel("Visit Record Date:"), gbc);

    visitRecordYearSpinner = new JSpinner(new SpinnerNumberModel(2024, 1900, 2100, 1));
    gbc.gridx = 1;
    add(visitRecordYearSpinner, gbc);

    visitRecordMonthSpinner = new JSpinner(new SpinnerNumberModel(12, 1, 12, 1));
    gbc.gridx = 2;
    add(visitRecordMonthSpinner, gbc);

    visitRecordDaySpinner = new JSpinner(new SpinnerNumberModel(4, 1, 31, 1));
    gbc.gridx = 3;
    add(visitRecordDaySpinner, gbc);

    gbc.gridy = 7;
    gbc.gridx = 0;
    add(new JLabel("Visit Record Time:"), gbc);

    visitRecordHourSpinner = new JSpinner(new SpinnerNumberModel(10, 0, 23, 1));
    gbc.gridx = 1;
    add(visitRecordHourSpinner, gbc);

    visitRecordMinuteSpinner = new JSpinner(new SpinnerNumberModel(42, 0, 59, 1));
    gbc.gridx = 2;
    add(visitRecordMinuteSpinner, gbc);

    visitRecordSecondSpinner = new JSpinner(new SpinnerNumberModel(36, 0, 59, 1));
    gbc.gridx = 3;
    add(visitRecordSecondSpinner, gbc);

    addLabeledField(gbc, "Visit Record Complaint:", 8, visitRecordComplaint = new JTextField());
    addLabeledField(gbc, "Visit Record Temperature (°C):", 9, visitRecordTemperature
            = new JSpinner(new SpinnerNumberModel(36.0, 0.0, 120.0, 0.1)));

    addButton(gbc, 10, registerPatientButton = new JButton("Register Patient"));
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
    field.setPreferredSize(new Dimension(100, 25));
    add(field, gbc);
  }

  private void addButton(GridBagConstraints gbc, int y, JButton button) {
    gbc.gridx = 0;
    gbc.gridy = y;
    gbc.gridwidth = 2;
    add(button, gbc);
  }

  /**
   * Returns the date of birth of the patient.
   *
   * @return the date of birth of the patient
   */
  public String getDob() {
    int year = (Integer) dobYearSpinner.getValue();
    int month = (Integer) dobMonthSpinner.getValue();
    int day = (Integer) dobDaySpinner.getValue();
    return String.format("%04d-%02d-%02d", year, month, day);
  }

  /**
   * Returns the visit record date and time.
   *
   * @return the visit record date and time
   */
  public LocalDateTime getVisitRecordDateTime() {
    int year = (Integer) visitRecordYearSpinner.getValue();
    int month = (Integer) visitRecordMonthSpinner.getValue();
    int day = (Integer) visitRecordDaySpinner.getValue();
    int hour = (Integer) visitRecordHourSpinner.getValue();
    int minute = (Integer) visitRecordMinuteSpinner.getValue();
    int second = (Integer) visitRecordSecondSpinner.getValue();
    return LocalDateTime.of(year, month, day, hour, minute, second);
  }

  /**
   * Returns the first name of the patient.
   *
   * @return the first name of the patient
   */
  public String getFirstName() {
    return patientFirstName.getText();
  }

  /**
   * Returns the last name of the patient.
   *
   * @return the last name of the patient
   */
  public String getLastName() {
    return patientLastName.getText();
  }

  /**
   * Returns the visit record complaint.
   *
   * @return the visit record complaint
   */
  public String getVisitRecordComplaint() {
    return visitRecordComplaint.getText();
  }

  /**
   * Returns the visit record temperature.
   *
   * @return the visit record temperature
   */
  public double getVisitRecordTemperature() {
    return (Double) visitRecordTemperature.getValue();
  }

  /**
   * Returns the register patient button.
   *
   * @return the register patient button
   */
  public JButton getRegisterPatientButton() {
    return registerPatientButton;
  }

  /**
   * Clears the fields in the form.
   */
  public void clearFields() {
    patientFirstName.setText("");
    patientLastName.setText("");
    visitRecordComplaint.setText("");
    visitRecordTemperature.setValue(36.0);
    dobYearSpinner.setValue(2000);
    dobMonthSpinner.setValue(11);
    dobDaySpinner.setValue(3);
    visitRecordYearSpinner.setValue(2024);
    visitRecordMonthSpinner.setValue(12);
    visitRecordDaySpinner.setValue(4);
    visitRecordHourSpinner.setValue(10);
    visitRecordMinuteSpinner.setValue(42);
    visitRecordSecondSpinner.setValue(36);
  }
}
