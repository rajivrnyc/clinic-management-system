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
   * Makes buttons to click on rooms for selection purposes visible for Assign Room.
   * 
   * @param f callback for when a room is selected by user.
   */
  void enableRoomSelectionAssignRoom(Features f);
  
  /**
   * Makes buttons to click on rooms for selection purposes invisible.
   */
  void disableRoomSelection();
  
  /**
   * Makes buttons to click on patients for selection purposes visible for Assign Room.
   * 
   * @param f callback for when a patient is selected by user.
   */
  void enablePatientSelectionAssignRoom(Features f);
  
  /**
   * Makes buttons to click on patients for selection purposes visible for Assign Staff.
   * 
   * @param f callback for when a patient is selected by user.
   */
  void enablePatientSelectionAssignStaff(Features f);
  
  /*
   * Makes buttons to click on patients for selection purposes visible for display patient.
   * 
   * @param f callback for when a patient is selected by user.
   */
  void enablePatientSelectionDisplayPatient(Features f);
  
  
  /**
   * Makes buttons to click on patients for selection purposes invisible.
   */
  void disablePatientSelection();
  

}
