package commands.deprecated;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import model.clinic.Clinic;
import model.patient.Patient;
import model.room.Room;

/**
 * Represents a command to create a map of the clinic.
 */
public class CreateMapOfClinicCommand implements Command {

  private static final int SCALE_FACTOR = 30;
  private final Clinic clinic;
  private final Logger logger;

  /**
   * Creates a new CreateMapOfClinicCommand with the given clinic and logger.
   *
   * @param clinic the clinic to create the map
   * @param logger the logger to log the creation of the clinic map
   */
  public CreateMapOfClinicCommand(Clinic clinic, Logger logger) {
    this.clinic = clinic;
    this.logger = logger;
  }

  @Override
  public void execute(Scanner scanner) {
    try {
      List<Room> rooms = clinic.getRoomsList();
      BufferedImage image = createClinicMap(rooms);
      saveImage(image);
      System.out.println("Clinic map created and saved as clinic_map.png.");
      logger.info("Clinic map created and saved as clinic_map.png.");
    } catch (IllegalArgumentException e) {
      logger.severe(e.getMessage());
      System.out.println("Clinic map could not be created. Please try again.");
    }
  }

  private BufferedImage createClinicMap(List<Room> rooms) {
    int maxWidth = 0;
    int maxHeight = 0;
    for (Room room : rooms) {
      maxWidth = Math.max(maxWidth, room.getUpperRight().getX() * 50);
      maxHeight = Math.max(maxHeight, room.getUpperRight().getY() * 50);
    }

    BufferedImage image = new BufferedImage(maxWidth + 50,
            maxHeight + 50, BufferedImage.TYPE_INT_ARGB);
    Graphics g = image.getGraphics();
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, maxWidth + 50, maxHeight + 50);

    for (Room room : rooms) {
      int x1 = room.getLowerLeft().getX() * SCALE_FACTOR;
      int y1 = room.getLowerLeft().getY() * SCALE_FACTOR;
      int width = (room.getUpperRight().getX() - room.getLowerLeft().getX()) * SCALE_FACTOR;
      int height = (room.getUpperRight().getY() - room.getLowerLeft().getY()) * SCALE_FACTOR;

      switch (room.getRoomType()) {
        case WAITING -> g.setColor(Color.YELLOW);
        case EXAM -> g.setColor(Color.CYAN);
        case PROCEDURE -> g.setColor(Color.PINK);
        default -> g.setColor(Color.LIGHT_GRAY);
      }
      g.fillRect(x1, y1, width, height);
      g.setColor(Color.BLACK);
      g.drawRect(x1, y1, width, height);

      Font largeFont = new Font("Arial", Font.BOLD, 11);
      g.setFont(largeFont);

      g.drawString(room.getRoomName(), x1 + 5, y1 + 15);

      Font defaultFont = new Font("Arial", Font.PLAIN, 10);
      g.setFont(defaultFont);

      if (room.getPatients().isEmpty()) {
        g.drawString("Empty", x1 + 5, y1 + 30);
      } else {
        int offset = 0;
        for (Patient patient : room.getPatients()) {
          g.drawString(patient.getFullName(), x1 + 5, y1 + 30 + offset);
          offset += 15;
        }
      }
    }
    g.dispose();
    return image;
  }

  private void saveImage(BufferedImage image) {
    try {
      File file = new File("clinic_map.png");
      ImageIO.write(image, "png", file);
    } catch (IOException e) {
      logger.severe(e.getMessage());
    }
  }
}
