package view.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Set;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.patient.Patient;
import model.patient.VisitRecord;
import view.MainView;
import view.listeners.VisitRecordActionListener;

/**
 * A dialog for viewing visit records of a patient.
 */
public class ViewVisitRecords extends JDialog {

  /**
   * Creates a new ViewVisitRecords dialog.
   *
   * @param owner the owner of the dialog
   * @param patient the patient whose visit records to view
   * @param actionListener the listener for visit record actions
   */
  public ViewVisitRecords(MainView owner, Patient patient,
                          VisitRecordActionListener actionListener) {
    super(owner, "Visit Records for " + patient.getFullName(), true);
    setLayout(new BorderLayout());
    setMinimumSize(new Dimension(650, 300));
    setSize(650, 400);

    Set<VisitRecord> visitRecords = patient.getVisitHistory();

    // Create table model and table
    DefaultTableModel tableModel = new DefaultTableModel(new String[]{
        "Registration Time",
        "Complaint",
        "Body Temperature (°C)"}, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    JTable visitRecordsTable = new JTable(tableModel);

    // Populate the table with Visit Records
    if (visitRecords == null || visitRecords.isEmpty()) {
      tableModel.addRow(new Object[]{"No Visit Records", "", "", "", "", ""});
    } else {
      for (VisitRecord visitRecord : visitRecords) {
        tableModel.addRow(new Object[]{
            visitRecord.getRegistrationTimeFormatted(),
            visitRecord.getChiefComplaint(),
            visitRecord.getBodyTemp()
        });
      }
    }
    JScrollPane scrollPane = new JScrollPane(visitRecordsTable);
    add(scrollPane, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
    add(buttonPanel, BorderLayout.EAST);
    JButton addVisitRecordButton = new JButton("Add Visit Record");
    buttonPanel.add(addVisitRecordButton);

    addVisitRecordButton.addActionListener(e -> {
      actionListener.onViewAddVisitRecordForm(patient);
      dispose();
    });

    JButton closeButton = new JButton("Close");
    closeButton.addActionListener(e -> dispose());
    add(closeButton, BorderLayout.SOUTH);

    setLocationRelativeTo(owner);
    setVisible(true);
  }
}
