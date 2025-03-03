package commands.deprecated;

import java.util.Scanner;
import java.util.logging.Logger;
import model.clinic.Clinic;

/**
 * Represents a command to view specific room details.
 */
public class ViewSpecificRoomDetailsCommand implements Command {
  private final Clinic clinic;
  private final Logger logger;

  /**
   * Creates a new ViewSpecificRoomDetailsCommand with the given clinic and logger.
   *
   * @param clinic the clinic to view the room details
   * @param logger the logger to log the viewing of room details
   */
  public ViewSpecificRoomDetailsCommand(Clinic clinic, Logger logger) {
    this.clinic = clinic;
    this.logger = logger;
  }

  @Override
  public void execute(Scanner scanner) {
    try {
      System.out.println("Enter the room number to view details:");
      clinic.displayRoomList();
      final int roomNumber = scanner.nextInt();
      scanner.nextLine();
      System.out.println(clinic.getSpecificRoomDetails(roomNumber));
      logger.info("Room details displayed.");
    } catch (IllegalArgumentException e) {
      logger.severe(e.getMessage());
      System.out.println("Room details could not be displayed. Please try again.");
    }

  }
}
