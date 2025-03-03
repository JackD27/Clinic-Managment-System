package view.listeners;

import java.time.LocalDateTime;
import model.patient.Patient;

/**
 * VisitRecordActionListener is an interface for the actions that can be performed on a visit
 * record.
 */
public interface VisitRecordActionListener {

  /**
   * onViewAddVisitRecordForm is called when the user selects the Add Visit Record option from the
   * Visit Record menu.
   *
   * @param patient the patient to add a visit record to
   */
  void onViewAddVisitRecordForm(Patient patient);

  /**
   * addVisitRecord is called when the user confirms the visit record to add.
   *
   * @param patient the patient to add a visit record to
   * @param visitRecordTime the time of the visit record
   * @param visitRecordCompliant the compliant of the visit record
   * @param visitRecordTemperature the temperature of the visit record
   */
  void addVisitRecord(Patient patient, LocalDateTime visitRecordTime, String visitRecordCompliant,
                      double visitRecordTemperature);
}
