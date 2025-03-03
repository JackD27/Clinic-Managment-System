package room;

import static org.junit.Assert.assertEquals;

import model.room.Room;
import model.room.RoomType;
import org.junit.Before;
import org.junit.Test;

/**
 * RoomTest class that tests the Room class.
 */
public class RoomTest {
  private Room roomExam;
  private Room roomWaiting;
  private Room roomProcedure;

  /**
   * Sets up the rooms for testing.
   */
  @Before
  public void setUp() {
    roomExam = new Room(2, 1, 1, 2, 2, "exam",
            "Exam Room");
    roomWaiting = new Room(1, 3, 3, 4, 4, "waiting",
            "Inside");
    roomProcedure = new Room(3, 5, 5, 6, 6, "procedure",
            "Procedure Room");
  }

  @Test
  public void getRoomType() {
    assertEquals(RoomType.EXAM, roomExam.getRoomType());
    assertEquals(RoomType.WAITING, roomWaiting.getRoomType());
    assertEquals(RoomType.PROCEDURE, roomProcedure.getRoomType());
  }

  @Test
  public void getRoomNumber() {
    assertEquals(2, roomExam.getRoomNumber());
    assertEquals(1, roomWaiting.getRoomNumber());
    assertEquals(3, roomProcedure.getRoomNumber());
  }

  @Test
  public void getLowerLeft() {
    assertEquals("(1, 1)", roomExam.getLowerLeft().toString());
    assertEquals("(3, 3)", roomWaiting.getLowerLeft().toString());
    assertEquals("(5, 5)", roomProcedure.getLowerLeft().toString());
  }

  @Test
  public void getUpperRight() {
    assertEquals("(2, 2)", roomExam.getUpperRight().toString());
    assertEquals("(4, 4)", roomWaiting.getUpperRight().toString());
    assertEquals("(6, 6)", roomProcedure.getUpperRight().toString());
  }

  @Test
  public void getRoomName() {
    assertEquals("Exam Room", roomExam.getRoomName());
    assertEquals("Inside Waiting Room", roomWaiting.getRoomName());
    assertEquals("Procedure Room", roomProcedure.getRoomName());
  }
}