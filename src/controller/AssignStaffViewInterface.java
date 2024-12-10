package controller;

import clinic.PatientInterface;
import clinic.RoomInterface;

/**
 * The interface represents the features that AssignStaffView offers.
 */
public interface AssignStaffViewInterface {


  /**
   * Represents different commands that can be executed within the clinic.
   * Each command performs a unique operation within the clinic.
   */
  void execute();
  
  /**
   * retrieves a patient from a button press for
   * staff assignment.
   * 
   * @param patient the patient selected from the button click.
   */
  void processPatientAssignStaff(PatientInterface patient);
    
}
