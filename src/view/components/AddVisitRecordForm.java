package view.components;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDateTime;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import model.patient.Patient;
import view.MainView;
import view.listeners.VisitRecordActionListener;
import view.util.UiHelper;

/**
 * A form for adding a visit record to a patient.
 */
public class AddVisitRecordForm extends JDialog {

  private final JTextField visitRecordComplaint;
  private final JSpinner visitRecordTemperature;
  private final JSpinner visitRecordYearSpinner;
  private final JSpinner visitRecordMonthSpinner;
  private final JSpinner visitRecordDaySpinner;
  private final JSpinner visitRecordHourSpinner;
  private final JSpinner visitRecordMinuteSpinner;
  private final JSpinner visitRecordSecondSpinner;
  private final JButton addVisitRecordButton;
  private final JButton backButton;

  /**
   * Creates a new AddVisitRecordForm.
   *
   * @param parent the parent view
   * @param patient the patient to add the visit record to
   * @param actionListener the listener for visit record actions
   */
  public AddVisitRecordForm(MainView parent, Patient patient,
                            VisitRecordActionListener actionListener) {
    super((JFrame) SwingUtilities.getWindowAncestor(parent), "Add Visit Record to "
            + patient.getFullName(), true);
    setSize(450, 350);
    setLocationRelativeTo(parent);
    GridBagConstraints gbc = createGridBagConstraints();
    setLayout(new GridBagLayout());

    JLabel titleLabel = new JLabel("Visit Record Information");
    titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
    add(titleLabel, gbc);
    gbc.gridy = 1;
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

    gbc.gridy = 2;
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

    addLabeledField(gbc, "Visit Record Complaint:", 3, visitRecordComplaint = new JTextField());
    addLabeledField(gbc, "Visit Record Temperature (°C):", 4, visitRecordTemperature
            = new JSpinner(new SpinnerNumberModel(36.0, 0.0, 120.0, 0.1)));

    addVisitRecordButton = addButton(gbc, "Add Visit Record");
    backButton = addButton(gbc, "Back");

    configureButtons(actionListener, patient);

    setVisible(true);
  }

  private JButton addButton(GridBagConstraints gbc, String text) {
    int y = gbc.gridy + 1;
    JButton button = new JButton(text);
    UiHelper.addButton(this, gbc, y, button);
    gbc.gridy = y;
    return button;
  }

  private void configureButtons(VisitRecordActionListener actionListener, Patient patient) {
    addVisitRecordButton.addActionListener(e -> {
      if (isInputInvalid()) {
        JOptionPane.showMessageDialog(this, "Please fill out all fields.");
        return;
      }
      actionListener.addVisitRecord(patient, getVisitRecordDateTime(),
              getVisitRecordComplaint(), getVisitRecordTemperature());
      dispose();
    });

    backButton.addActionListener(e -> dispose());
  }

  private boolean isInputInvalid() {
    return visitRecordComplaint.getText().isEmpty();
  }

  private void addLabeledField(GridBagConstraints gbc, String label, int y, JComponent field) {
    gbc.gridx = 0;
    gbc.gridy = y;
    add(new JLabel(label), gbc);

    gbc.gridx = 1;
    field.setPreferredSize(new Dimension(100, 25));
    add(field, gbc);
  }

  /**
   * Gets the visit record date and time.
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
   * Gets the visit record complaint.
   *
   * @return the visit record complaint
   */
  public String getVisitRecordComplaint() {
    return visitRecordComplaint.getText();
  }

  /**
   * Gets the visit record temperature.
   *
   * @return the visit record temperature
   */
  public double getVisitRecordTemperature() {
    return (double) visitRecordTemperature.getValue();
  }

  private GridBagConstraints createGridBagConstraints() {
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    return gbc;
  }
}
