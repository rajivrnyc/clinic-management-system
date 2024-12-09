package view;

import clinic.PatientInterface;
import clinic.RoomInterface;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;


/**
 * The view for a clinic management system.
 */
public interface ClinicViewInterface {
  
  /**
   * Makes buttons to click on rooms for selection purposes visible.
   * 
   * @param onRoomSelected callback for when a room is selected by user.
   */
  void enableRoomSelection(ActionListener onRoomSelected);
  
  /**
   * Makes buttons to click on rooms for selection purposes invisible.
   */
  void disableRoomSelection();
  
  /**
   * Makes buttons to click on patients for selection purposes visible.
   * 
   * @param onPatientSelected callback for when a patient is selected by user.
   */
  void enablePatientSelection(ActionListener onPatientSelected);
  
  
  /**
   * Makes buttons to click on patients for selection purposes invisible.
   */
  void disablePatientSelection();
  
  /**
   * returns  the patient object selected by the button.
   * 
   * @param selectedButton the button selected by the user.
   * @return the Patient associated with the button.
   */
  PatientInterface getSelectedPatient(JButton selectedButton);
  
  /**
   * returns  the patient object selected by the button.
   * 
   * @param selectedButton the button selected by the user.
   * @return the Room associated with the button.
   */
  RoomInterface getSelectedRoom(JButton selectedButton);
  

}
