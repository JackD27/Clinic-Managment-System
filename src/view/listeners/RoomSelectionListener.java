package view.listeners;

import model.room.Room;

/**
 * RoomSelectionListener is an interface for the Room selection in the GUI.
 */
public interface RoomSelectionListener {


  /**
   * onRoomSelected is called when the user selects a room from the list of rooms.
   *
   * @param room the room selected by the user
   */
  void onRoomSelected(Room room);
}
