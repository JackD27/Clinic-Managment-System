package view.listeners;

import model.patient.Patient;
import model.staff.ClinicalStaff;

/**
 * PatientActionListener is an interface for the actions that can be performed on a patient.
 */
public interface PatientActionListener {
  /**
   * onViewVisitRecords is called when the user selects the View Visit Records option from the
   * Patient menu.
   *
   * @param patient the patient whose visit records are to be viewed
   */
  void onViewVisitRecords(Patient patient);

  /**
   * onViewStaffHelpers is called when the user selects the View Staff Helpers option from the
   * Patient menu.
   *
   * @param patient the patient whose staff helpers are to be viewed
   */
  void onViewStaffHelpers(Patient patient);

  /**
   * onMovePatient is called when the user selects the patient to move rooms.
   *
   * @param patient the patient to move.
   */
  void onMovePatient(Patient patient);

  /**
   * onViewSendPatientHome is called when the user selects the Send Patient Home option from the
   * Patient menu.
   *
   * @param patient the patient to send home
   */
  void onViewSendPatientHome(Patient patient);

  /**
   * onSendPatientHome is called when the user confirms the patient to send home.
   *
   * @param patient the patient to send home
   * @param staffMember the staff member sending the patient home
   */
  void onSendPatientHome(Patient patient, ClinicalStaff staffMember);
}
