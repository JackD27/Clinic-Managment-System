package view.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import model.patient.Patient;
import model.staff.ClinicalStaff;
import view.MainView;
import view.listeners.PatientActionListener;

/**
 * Represents a dialog for sending a patient home.
 */
public class SendPatientHomeDialog extends JDialog {

  private final Patient patient;
  private final PatientActionListener patientActionListener;

  /**
   * Creates a new SendPatientHomeDialog with the given owner, staff list, patient, and patient
   * action listener.
   *
   * @param owner the owner of the dialog
   * @param staffList the list of staff members to send the patient home
   * @param patient the patient to send home
   * @param patientActionListener the listener for patient actions
   */
  public SendPatientHomeDialog(MainView owner, List<ClinicalStaff> staffList, Patient patient,
                               PatientActionListener patientActionListener) {
    super(owner, "Move Patient - " + patient.getFullName(), true);
    this.patient = patient;
    this.patientActionListener = patientActionListener;

    setLayout(new BorderLayout());
    setSize(400, 400);
    setLocationRelativeTo(owner);

    // Panel for room list
    JPanel staffListPanel = new JPanel();
    staffListPanel.setLayout(new BoxLayout(staffListPanel, BoxLayout.Y_AXIS));
    JScrollPane scrollPane = new JScrollPane(staffListPanel);

    for (ClinicalStaff staffMember : staffList) {
      staffListPanel.add(createStaffPanel(staffMember));
    }

    JButton closeButton = new JButton("Close");
    closeButton.addActionListener(e -> dispose());

    add(scrollPane, BorderLayout.CENTER);
    add(closeButton, BorderLayout.SOUTH);

    setVisible(true);
  }

  private JPanel createStaffPanel(ClinicalStaff staff) {
    JPanel staffPanel = new JPanel(new BorderLayout());
    staffPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

    JLabel roomLabel = new JLabel("Send home by " + staff.getFullName());
    JButton moveButton = new JButton("Send Home Patient");
    moveButton.setBackground(Color.PINK);

    // ActionListener for moving the patient to the selected room
    moveButton.addActionListener(e -> {
      int confirm = JOptionPane.showConfirmDialog(
              this,
              "Are you sure you want to send " + patient.getFullName() + " home?",
              "Confirm Move",
              JOptionPane.YES_NO_OPTION
      );
      if (confirm == JOptionPane.YES_OPTION) {
        patientActionListener.onSendPatientHome(patient, staff);
        dispose();
      }
    });

    staffPanel.add(roomLabel, BorderLayout.CENTER);
    staffPanel.add(moveButton, BorderLayout.EAST);

    return staffPanel;
  }

}
