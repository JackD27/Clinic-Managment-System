package view.listeners;

import model.staff.Staff;

/**
 * StaffMemberActionListener is an interface for the staff member action listener in the GUI.
 */
public interface StaffMemberActionListener {

  /**
   * onSendStaffMemberHome is called when the user selects the Send Staff Member Home option
   * from the Staff Member menu.
   *
   * @param staffMember the staff member to send home
   */
  void onSendStaffMemberHome(Staff staffMember);
}
