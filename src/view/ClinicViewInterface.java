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
   * @param f callback for when a room is selected by user.
   */
  void enableRoomSelection(Features f);
  
  /**
   * Makes buttons to click on rooms for selection purposes invisible.
   */
  void disableRoomSelection();
  
  /**
   * Makes buttons to click on patients for selection purposes visible.
   * 
   * @param f callback for when a patient is selected by user.
   */
  void enablePatientSelection(Features f);
  
  
  /**
   * Makes buttons to click on patients for selection purposes invisible.
   */
  void disablePatientSelection();
  
  /**
   * sets the patient selected by the button.
   * 
   * @param patient selected by button press.
   */
  void setPatient(PatientInterface patient);
  
  /**
   * returns the patient selected by the button.
   * 
   * @return patient selected by button press.
   */
  PatientInterface getPatient();
  

}
