package view.components;

import java.awt.BorderLayout;
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
import model.room.Room;
import view.MainView;
import view.listeners.MovePatientListener;

/**
 * A dialog for moving a patient to a different room.
 */
public class MovePatientMapView extends JDialog {
  private final Patient patient;
  private final MovePatientListener movePatientListener;

  /**
   * Creates a new MovePatientMapView.
   *
   * @param owner the owner of the dialog
   * @param rooms the list of rooms to move the patient to
   * @param patient the patient to move
   * @param movePatientListener the listener for moving the patient
   */
  public MovePatientMapView(MainView owner, List<Room> rooms, Patient patient,
                            MovePatientListener movePatientListener) {
    super(owner, "Move Patient - " + patient.getFullName(), true);
    this.patient = patient;
    this.movePatientListener = movePatientListener;

    setLayout(new BorderLayout());
    setSize(400, 400);
    setLocationRelativeTo(owner);

    // Panel for room list
    JPanel roomListPanel = new JPanel();
    roomListPanel.setLayout(new BoxLayout(roomListPanel, BoxLayout.Y_AXIS));
    JScrollPane scrollPane = new JScrollPane(roomListPanel);

    for (Room room : rooms) {
      roomListPanel.add(createRoomPanel(room));
    }

    JButton closeButton = new JButton("Close");
    closeButton.addActionListener(e -> dispose());

    add(scrollPane, BorderLayout.CENTER);
    add(closeButton, BorderLayout.SOUTH);

    setVisible(true);
  }

  private JPanel createRoomPanel(Room room) {
    JPanel roomPanel = new JPanel(new BorderLayout());
    roomPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

    JLabel roomLabel = new JLabel(room.getRoomName() + " (Patients: "
            + room.getPatients().size() + ")");
    JButton moveButton = new JButton("Move Here");

    // ActionListener for moving the patient to the selected room
    moveButton.addActionListener(e -> {
      int confirm = JOptionPane.showConfirmDialog(
              this,
              "Move " + patient.getFullName() + " to " + room.getRoomName() + "?",
              "Confirm Move",
              JOptionPane.YES_NO_OPTION
      );
      if (confirm == JOptionPane.YES_OPTION) {
        movePatientListener.onMovePatientToRoom(patient, room);
        dispose();
      }
    });

    roomPanel.add(roomLabel, BorderLayout.CENTER);
    roomPanel.add(moveButton, BorderLayout.EAST);

    return roomPanel;
  }
}
