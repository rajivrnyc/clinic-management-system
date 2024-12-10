package controller;

import clinic.PatientInterface;

/**
 * Sends a patient home view command input.
 */
public interface SendHomeViewInterface {

  /** 
 *  executes send home command in the view.
 */
  void execute();

  /**
   * retrieves a patient from a button press for unassign clinical staff.
   * 
   * @param patient the patient selected from the button click.
   */
  void processPatientSendHome(PatientInterface patient);
}
