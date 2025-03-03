package view.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import model.room.Room;
import model.room.RoomType;
import view.listeners.RoomSelectionListener;

/**
 * Represents a view of the map of the clinic.
 */
public class MapView extends JPanel {
  private static final int SCALE = 21;
  private List<Room> rooms;
  private RoomSelectionListener roomSelectionListener;

  /**
   * Creates a new MapView.
   */
  public MapView() {
    setLayout(null);
    setPreferredSize(new Dimension(800, 800));
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    this.removeAll();


    if (rooms == null || rooms.isEmpty()) {
      g.setFont(new Font("Arial", Font.BOLD, 20));
      g.setColor(Color.RED);
      String message = "No rooms, load a .txt file to display rooms.";
      FontMetrics metrics = g.getFontMetrics(g.getFont());
      int x = (getWidth() - metrics.stringWidth(message)) / 2;
      int y = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
      g.drawString(message, x, y);
      return;
    }

    for (Room room : rooms) {
      JButton roomButton = getJbutton(room);
      this.add(roomButton);
    }
  }

  private JButton getJbutton(Room room) {
    int x = room.getLowerLeft().getX() * SCALE;
    int y = room.getLowerLeft().getY() * SCALE;
    int width = (room.getUpperRight().getX() - room.getLowerLeft().getX()) * SCALE;
    int height = (room.getUpperRight().getY() - room.getLowerLeft().getY()) * SCALE;

    JButton roomButton = new JButton(room.getRoomName());
    roomButton.setBounds(x, y, width, height);
    roomButton.setFont(new Font("Arial", Font.PLAIN, 10));
    roomButton.setToolTipText("<html>"
            + room.getRoomName() + "<br>"
            + "Patients: " + room.getPatients().size()
            + "</html>");
    roomButton.setBackground(getRoomColor(room.getRoomType()));
    roomButton.addActionListener(e -> {
      if (roomSelectionListener != null) {
        roomSelectionListener.onRoomSelected(room);
      }
    });
    return roomButton;
  }


  private Color getRoomColor(RoomType roomType) {
    return switch (roomType) {
      case WAITING -> Color.LIGHT_GRAY;
      case EXAM -> Color.CYAN;
      case PROCEDURE -> Color.PINK;
    };
  }

  /**
   * Updates the view.
   */
  public void update() {
    this.removeAll();
    repaint();
  }

  /**
   * Sets the rooms to display.
   *
   * @param rooms the rooms to display
   */
  public void setRooms(List<Room> rooms) {
    this.rooms = rooms;
    update();
  }

  /**
   * Sets the room selection listener.
   *
   * @param listener the room selection listener
   */
  public void setRoomSelectionListener(RoomSelectionListener listener) {
    this.roomSelectionListener = listener;
  }
}
