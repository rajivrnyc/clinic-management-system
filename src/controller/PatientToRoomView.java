package controller;

import clinic.PatientInterface;
import clinic.RoomInterface;

/**
 * The interface represents the features that Patient to Room offers.
 */
public interface PatientToRoomView {


  /**
   * Represents different commands that can be executed within the clinic.
   * Each command performs a unique operation within the clinic.
   */
  void execute();
  
  /**
     *  retrieves a patient from a button press.
     *  
     * @param patient the patient from the button click.
     *
     */
  void processPatient(PatientInterface patient);
    
  /**
     *  retrieves a room from a button press.
     *  
     * @param room the room from the button click.
     *
     */
  void processRoom(RoomInterface room);
}
