package view;

import clinic.PatientInterface;
import clinic.RoomInterface;
import controller.AssignStaffViewInterface;
import controller.DisplayPatientInterface;
import controller.Features;
import controller.PatientToRoomView;
import controller.SendHomeViewInterface;
import controller.UnassignStaffViewInterface;
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
  void enableRoomSelectionAssignRoom(PatientToRoomView f);
  
  /**
   * Makes buttons to click on rooms for selection purposes invisible.
   */
  void disableRoomSelection();
  
  /**
   * Makes buttons to click on patients for selection purposes visible for Assign Room.
   * 
   * @param f callback for when a patient is selected by user.
   */
  void enablePatientSelectionAssignRoom(PatientToRoomView f);
  
  /**
   * Makes buttons to click on patients for selection purposes visible for Assign Staff.
   * 
   * @param f callback for when a patient is selected by user.
   */
  void enablePatientSelectionAssignStaff(AssignStaffViewInterface f);
  
  /**
   * Makes buttons to click on patients for selection purposes visible for display patient.
   * 
   * @param f callback for when a patient is selected by user.
   */
  void enablePatientSelectionDisplayPatient(DisplayPatientInterface f);
  
  /**
   * Makes buttons to click on patients for selection purposes visible for unassign staff.
   * 
   * @param f callback for when a patient is selected by user.
   */
  void enablePatientSelectionUnassignStaff(UnassignStaffViewInterface f);
  
  /**
   * Makes buttons to click on patients for selection purposes visible for send home.
   * 
   * @param f callback for when a patient is selected by a user.
   */
  void enablePatientSelectionSendHome(SendHomeViewInterface f);
  
  
  /**
   * Makes buttons to click on patients for selection purposes invisible.
   */
  void disablePatientSelection();
}
