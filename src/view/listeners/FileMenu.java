package view.listeners;

/**
 * FileMenu is an interface for the File menu in the GUI.
 */
public interface FileMenu {
  /**
   * systemQuit is called when the user selects the Quit option from the File menu.
   */

  void systemQuit();
  /**
   * loadClinicFile is called when the user selects the Load Clinic File option from the
   * File menu.
   */

  void loadClinicFile();

  /**
   * clearRecords is called when the user selects the clear records option from the
   * File menu. It wipes all records from the system.
   */
  void clearRecords();
}
