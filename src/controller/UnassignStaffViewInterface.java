package controller;

import clinic.PatientInterface;

/**
 * Command to unassign patient from the interface.
 */
public interface UnassignStaffViewInterface {

  /**
   * retrieves a patient from a button press for unassign clinical staff.
   * 
   * @param patient the patient selected from the button click.
   */
  void processPatientUnassignStaff(PatientInterface patient);
  
  /**
   *  Execute command to unassign staff from patient.
   */
  void execute();
}
