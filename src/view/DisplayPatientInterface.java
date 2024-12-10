package view;

import clinic.PatientInterface;
import clinic.RoomInterface;

/**
 * Command to display patient info in the view.
 */
public interface DisplayPatientInterface {

  /**
   * Represents different commands that can be executed within the clinic.
   * Each command performs a unique operation within the clinic.
   */
  void execute();
  
  /**
   * retrieves a patient from a button press for patient display.
   * 
   * @param patient  the patient selected from the button click.
   */
  void processPatientDisplayPatient(PatientInterface patient);   
}