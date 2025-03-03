package model.staff;

import model.patient.Patient;

/**
 * ClinicalStaffInterface interface that represents a clinical staff member in a clinic. A
 * clinical staff member has a job title, first name, last name, education level, and extra field.
 */
public interface ClinicalStaffInterface {

  /**
   * Add a patient to the clinical staff member's list of assigned patients from the past.
   *
   * @param patient the patient to add to the clinical staff member's list of assigned patients.
   */
  void addAssignedPatient(Patient patient);

}
